/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package af.asr.accounting.service;

import af.asr.accounting.repository.AccountRepository;
import af.asr.accounting.repository.LedgerRepository;
import af.asr.accounting.domain.*;
import af.asr.accounting.mapper.*;
import af.asr.accounting.model.*;
import af.asr.accounting.specification.LedgerSpecification;
import af.asr.infrastructure.service.UserService;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class LedgerService {

  private final LedgerRepository ledgerRepository;
  private final AccountRepository accountRepository;
  private final UserService userService;

  @Autowired
  public LedgerService(final LedgerRepository ledgerRepository,
                       final AccountRepository accountRepository, UserService userService) {
    super();
    this.ledgerRepository = ledgerRepository;
    this.accountRepository = accountRepository;
    this.userService = userService;
  }

  public LedgerPage fetchLedgers(final boolean includeSubLedgers,
                                 final String term,
                                 final String type,
                                 final Pageable pageable) {
    final LedgerPage ledgerPage = new LedgerPage();

    final Page<LedgerEntity> ledgerEntities = this.ledgerRepository.findAll(
        LedgerSpecification.createSpecification(includeSubLedgers, term, type), pageable
    );

    ledgerPage.setTotalPages(ledgerEntities.getTotalPages());
    ledgerPage.setTotalElements(ledgerEntities.getTotalElements());

    ledgerPage.setLedgers(this.mapToLedger(ledgerEntities.getContent()));

    return ledgerPage;
  }

  private List<Ledger> mapToLedger(List<LedgerEntity> ledgerEntities) {
    final List<Ledger> result = new ArrayList<>(ledgerEntities.size());

    if(!ledgerEntities.isEmpty()) {
      ledgerEntities.forEach(ledgerEntity -> {
        final Ledger ledger = LedgerMapper.map(ledgerEntity);
        this.addSubLedgers(ledger, this.ledgerRepository.findByParentLedgerOrderByIdentifier(ledgerEntity));
        result.add(ledger);
      });
    }

    return result;
  }

  public Optional<Ledger> findLedger(final String identifier) {
    final LedgerEntity ledgerEntity = this.ledgerRepository.findByIdentifier(identifier);
    if (ledgerEntity != null) {
      final Ledger ledger = LedgerMapper.map(ledgerEntity);
      this.addSubLedgers(ledger, this.ledgerRepository.findByParentLedgerOrderByIdentifier(ledgerEntity));
      return Optional.of(ledger);
    } else {
      return Optional.empty();
    }
  }

  public AccountPage fetchAccounts(final String ledgerIdentifier, final Pageable pageable) {
    final LedgerEntity ledgerEntity = this.ledgerRepository.findByIdentifier(ledgerIdentifier);
    final Page<AccountEntity> accountEntities = this.accountRepository.findByLedger(ledgerEntity, pageable);

    final AccountPage accountPage = new AccountPage();
    accountPage.setTotalPages(accountEntities.getTotalPages());
    accountPage.setTotalElements(accountEntities.getTotalElements());

    if(accountEntities.getSize() > 0){
      final List<Account> accounts = new ArrayList<>(accountEntities.getSize());
      accountEntities.forEach(accountEntity -> accounts.add(AccountMapper.map(accountEntity)));
      accountPage.setAccounts(accounts);
    }

    return accountPage;
  }

  public boolean hasAccounts(final String ledgerIdentifier) {
    final LedgerEntity ledgerEntity = this.ledgerRepository.findByIdentifier(ledgerIdentifier);
    final List<AccountEntity> ledgerAccounts = this.accountRepository.findByLedger(ledgerEntity);
    return ledgerAccounts.size() > 0;
  }

  private void addSubLedgers(final Ledger parentLedger,
                             final List<LedgerEntity> subLedgerEntities) {
    if (subLedgerEntities != null) {
      final List<Ledger> subLedgers = new ArrayList<>(subLedgerEntities.size());
      subLedgerEntities.forEach(subLedgerEntity -> subLedgers.add(LedgerMapper.map(subLedgerEntity)));
      parentLedger.setSubLedgers(subLedgers);
    }
  }

  @Transactional
  public String createLedger(final Ledger ledger) {

    this.log.debug("Received create ledger command with identifier {}.", ledger.getIdentifier());

    final LedgerEntity parentLedgerEntity = new LedgerEntity();
    parentLedgerEntity.setIdentifier(ledger.getIdentifier());
    parentLedgerEntity.setType(ledger.getType());
    parentLedgerEntity.setName(ledger.getName());
    parentLedgerEntity.setDescription(ledger.getDescription());
    parentLedgerEntity.setCreatedBy(userService.getPreferredUsername());
    parentLedgerEntity.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
    parentLedgerEntity.setShowAccountsInChart(ledger.getShowAccountsInChart());
    final LedgerEntity savedParentLedger = this.ledgerRepository.save(parentLedgerEntity);
    this.addSubLedgersInternal(ledger.getSubLedgers(), savedParentLedger);

    this.log.debug("Ledger {} created.", ledger.getIdentifier());

    return ledger.getIdentifier();
  }

  @Transactional

  public String addSubLedger(final Ledger subLedger, String parentLedgerIdentifier) {
    final LedgerEntity parentLedger =
            this.ledgerRepository.findByIdentifier(parentLedgerIdentifier);
    final LedgerEntity subLedgerEntity = this.ledgerRepository.findByIdentifier(subLedger.getIdentifier());
    if (subLedgerEntity == null) {
      this.addSubLedgersInternal(Collections.singletonList(subLedger), parentLedger);
    } else {
      subLedgerEntity.setParentLedger(parentLedger);
      subLedgerEntity.setLastModifiedBy(userService.getPreferredUsername());
      subLedgerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));
      this.ledgerRepository.save(subLedgerEntity);
    }
    parentLedger.setLastModifiedBy(userService.getPreferredUsername());
    parentLedger.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));
    this.ledgerRepository.save(parentLedger);
    return subLedger.getIdentifier();
  }

  @Transactional
  public String modifyLedger(final Ledger ledger2modify) {
    final LedgerEntity ledgerEntity =
            this.ledgerRepository.findByIdentifier(ledger2modify.getIdentifier());
    ledgerEntity.setName(ledger2modify.getName());
    ledgerEntity.setDescription(ledger2modify.getDescription());
    ledgerEntity.setLastModifiedBy(userService.getPreferredUsername());
    ledgerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));
    ledgerEntity.setShowAccountsInChart(ledger2modify.getShowAccountsInChart());
    this.ledgerRepository.save(ledgerEntity);
    return ledger2modify.getIdentifier();
  }

  @Transactional
  public String deleteLedger(final String identifier) {
    this.ledgerRepository.delete(this.ledgerRepository.findByIdentifier(identifier));
    return identifier;
  }

  @Transactional
  public void addSubLedgersInternal(final List<Ledger> subLedgers, final LedgerEntity parentLedgerEntity) {
    if (subLedgers != null) {

      this.log.debug(
              "Add {} sub ledger(s) to parent ledger {}.", subLedgers.size(),
              parentLedgerEntity.getIdentifier()
      );

      for (final Ledger subLedger : subLedgers) {
        if (!subLedger.getType().equals(parentLedgerEntity.getType())) {
          this.log.error(
                  "Type of sub ledger {} must match parent ledger {}. Expected {}, was {}",
                  subLedger.getIdentifier(), parentLedgerEntity.getIdentifier(),
                  parentLedgerEntity.getType(), subLedger.getType()
          );

          throw ServiceException.badRequest(
                  "Type of sub ledger {0} must match parent ledger {1}. Expected {2}, was {3}",
                  subLedger.getIdentifier(), parentLedgerEntity.getIdentifier(),
                  parentLedgerEntity.getType(), subLedger.getType()
          );
        }
        final LedgerEntity subLedgerEntity = new LedgerEntity();
        subLedgerEntity.setIdentifier(subLedger.getIdentifier());
        subLedgerEntity.setType(subLedger.getType());
        subLedgerEntity.setName(subLedger.getName());
        subLedgerEntity.setDescription(subLedger.getDescription());
        subLedgerEntity.setCreatedBy(userService.getPreferredUsername());
        subLedgerEntity.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
        subLedgerEntity.setShowAccountsInChart(subLedger.getShowAccountsInChart());
        subLedgerEntity.setParentLedger(parentLedgerEntity);
        final LedgerEntity savedSubLedger = this.ledgerRepository.save(subLedgerEntity);
        this.addSubLedgersInternal(subLedger.getSubLedgers(), savedSubLedger);

        this.log.debug("Sub ledger {} created.", subLedger.getIdentifier());
      }
    }
  }
}

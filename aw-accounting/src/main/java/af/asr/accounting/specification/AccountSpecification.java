
package af.asr.accounting.specification;

import af.asr.accounting.domain.Account;
import af.asr.accounting.model.AccountEntity;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class AccountSpecification {

  private AccountSpecification() {
    super();
  }

  public static Specification<AccountEntity> createSpecification(
      final boolean includeClosed, final String term, final String type, final boolean includeCustomerAccounts) {

    return (root, query, cb) -> {

      final ArrayList<Predicate> predicates = new ArrayList<>();

      if (!includeClosed) {
        predicates.add(
            root.get("state").in(
                Account.State.OPEN.name(),
                Account.State.LOCKED.name()
            )
        );
      }

      if (term != null) {
        final String likeExpression = "%" + term + "%";
        predicates.add(
            cb.or(
                cb.like(root.get("identifier"), likeExpression),
                cb.like(root.get("name"), likeExpression),
                cb.like(root.get("alternativeAccountNumber"), likeExpression)
            )
        );
      }

      if (type != null) {
        predicates.add(cb.equal(root.get("type"), type));
      }

      if (!includeCustomerAccounts) {
        predicates.add(
            cb.or(
                cb.equal(root.get("holders"), ""),
                cb.isNull(root.get("holders"))
            )
        );
      }

      return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    };
  }
}

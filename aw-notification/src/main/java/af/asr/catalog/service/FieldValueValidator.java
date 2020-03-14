
package af.asr.catalog.service;

import af.asr.catalog.domain.Field;
import af.asr.catalog.domain.Value;
import af.asr.catalog.model.CatalogEntity;
import af.asr.catalog.model.FieldEntity;
import af.asr.catalog.model.OptionEntity;
import af.asr.catalog.repository.CatalogRepository;
import af.asr.catalog.repository.FieldRepository;
import af.asr.customer.util.ServiceConstants;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import af.gov.anar.lang.validation.date.DateConverter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FieldValueValidator {

  private final Logger logger;
  private final CatalogRepository catalogRepository;
  private final FieldRepository fieldRepository;

  @Autowired
  public FieldValueValidator(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
                             final CatalogRepository catalogRepository,
                             final FieldRepository fieldRepository) {
    super();
    this.logger = logger;
    this.catalogRepository = catalogRepository;
    this.fieldRepository = fieldRepository;
  }

  public void validateValues(final List<Value> values) {
    values.forEach(value -> {
      final CatalogEntity catalogEntity =
          this.catalogRepository.findByIdentifier(value.getCatalogIdentifier())
              .orElseThrow(() -> ServiceException.notFound("Catalog {0} not found.", value.getCatalogIdentifier()));

      final FieldEntity fieldEntity =
          this.fieldRepository.findByCatalogAndIdentifier(catalogEntity, value.getFieldIdentifier())
              .orElseThrow(() -> ServiceException.notFound("Field {0} not found.", value.getFieldIdentifier()));

      final Field.DataType dataType = Field.DataType.valueOf(fieldEntity.getDataType());

      switch (dataType) {
        case TEXT:
          this.checkLength(value, fieldEntity);
          break;
        case NUMBER:
          this.checkNumber(value, fieldEntity);
          break;
        case DATE:
          this.checkDate(value, fieldEntity);
          break;
        case SINGLE_SELECTION:
          this.checkOptions(value, fieldEntity, fieldEntity.getOptions(), true);
          break;
        case MULTI_SELECTION:
          this.checkOptions(value, fieldEntity, fieldEntity.getOptions(), false);
          break;
        default:
          throw ServiceException.badRequest("Unsupported data type {0} of field {1}.", dataType.name(), fieldEntity.getLabel());
      }
    });
  }

  private void checkLength(final Value value, final FieldEntity fieldEntity) {
    if (fieldEntity.getLength() != null
        && value.getValue().length() > fieldEntity.getLength()) {
      throw ServiceException.badRequest("Value for field {0} must be smaller than or equals {1}.",
          fieldEntity.getLabel(), fieldEntity.getLength());
    }
  }

  private void checkNumber(final Value value, final FieldEntity fieldEntity) {
    try {
      final Double valueAsDouble = Double.valueOf(value.getValue());

      if (fieldEntity.getMinValue() != null) {
        if (valueAsDouble.compareTo(fieldEntity.getMinValue()) < 0) {
          throw ServiceException.badRequest("Value for field {0} must be greater than or equals {1}.",
              fieldEntity.getIdentifier(), fieldEntity.getMinValue());
        }
      }

      if (fieldEntity.getMaxValue() != null) {
        if (valueAsDouble.compareTo(fieldEntity.getMaxValue()) > 0) {
          throw ServiceException.badRequest("Value for field {0} must be lesser than or equals {1}.",
              fieldEntity.getIdentifier(), fieldEntity.getMaxValue());
        }
      }
    } catch (final Throwable th) {
      throw ServiceException.badRequest("Value for field {0} is not a number.", fieldEntity.getLabel());
    }

    final String[] split = StringUtils.split(value.getValue(), ".");
    if (fieldEntity.getLength() != null)  {
      if (split.length == 2) {
        if ((split[0].length() + split[1].length()) > fieldEntity.getLength()) {
          throw ServiceException.badRequest("Value for field {0} must be smaller than or equals {1}.",
              fieldEntity.getLabel(), fieldEntity.getLength());
        }

        if (fieldEntity.getPrecision() != null)  {
          if (split[1].length() > fieldEntity.getPrecision()) {
            throw ServiceException.badRequest("Precision for field {0} must be smaller than or equals {1}.",
                fieldEntity.getLabel(), fieldEntity.getPrecision());
          }
        }
      } else {
        this.checkLength(value, fieldEntity);
      }
    }
  }

  private void checkDate(final Value value, final FieldEntity fieldEntity) {
    try {
      DateConverter.fromIsoString(value.getValue());
    } catch (final Throwable th) {
      throw ServiceException.badRequest("Value for field {0} must be a valid ISO value.",fieldEntity.getLabel() );
    }
  }

  private void checkOptions(final Value value, final FieldEntity fieldEntity, final List<OptionEntity> optionEntities, boolean singleSelection) {
    final Set<String> valuesAsSet = StringUtils.commaDelimitedListToSet(value.getValue());

    if (singleSelection && valuesAsSet.size() > 1) {
      throw ServiceException.badRequest("Field {0} only supports single selection.", fieldEntity.getLabel());
    }

    final HashSet<String> optionsAsSet = new HashSet<>(optionEntities.size());
    optionEntities.forEach(optionEntity -> optionsAsSet.add(optionEntity.getValue().toString()));
    if (!optionsAsSet.containsAll(valuesAsSet)) {
      throw ServiceException.badRequest("Unsupported option {0} for field {1}.", value.getValue(), fieldEntity.getLabel());
    }
  }
}

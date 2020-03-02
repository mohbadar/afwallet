package af.gov.anar.ebreshna.configuration.metering.converter;

import af.gov.anar.ebreshna.configuration.billing.enumeration.TariffChargeMaximumType;
import af.gov.anar.ebreshna.infrastructure.datatype.AbstractGenericJsonAttributeConverter;
import af.gov.anar.ebreshna.infrastructure.datatype.EnumConverter;

import java.beans.PropertyEditorSupport;

public class TariffChargeMaximumTypeConverter {

    public TariffChargeMaximumType convert(String text) throws IllegalAccessException, InstantiationException {
        EnumConverter<TariffChargeMaximumType> converter = new EnumConverter<>(TariffChargeMaximumType.class);
        TariffChargeMaximumType item = converter.convert(text).getDeclaringClass().newInstance();
        return item;
    }

}
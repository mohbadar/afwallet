
package af.asr.customer.mapper;

import af.asr.customer.domain.*;
import af.asr.customer.model.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public final class AddressMapper {

  private AddressMapper() {
    super();
  }

  public static AddressEntity map(final Address address) {
    final AddressEntity addressEntity = new AddressEntity();
    addressEntity.setStreet(address.getStreet());
    addressEntity.setCity(address.getCity());
    addressEntity.setPostalCode(address.getPostalCode());
    addressEntity.setRegion(address.getRegion());
    addressEntity.setCountryCode(address.getCountryCode());
    addressEntity.setCountry(address.getCountry());
    return addressEntity;
  }

  public static Address map(final AddressEntity addressEntity) {
    final Address address = new Address();
    address.setStreet(addressEntity.getStreet());
    address.setCity(addressEntity.getCity());
    address.setPostalCode(addressEntity.getPostalCode());
    address.setRegion(addressEntity.getRegion());
    address.setCountryCode(addressEntity.getCountryCode());
    address.setCountry(addressEntity.getCountry());
    return address;
  }
}

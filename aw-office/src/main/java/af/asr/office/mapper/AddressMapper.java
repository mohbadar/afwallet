
package af.asr.office.mapper;


import af.asr.office.domain.Address;
import af.asr.office.model.AddressEntity;

public final class AddressMapper {

  private AddressMapper() {
    super();
  }

  public static AddressEntity map(final Address address) {
    final AddressEntity addressEntity = new AddressEntity();
    addressEntity.setStreet(address.getStreet());
    addressEntity.setCity(address.getCity());
    addressEntity.setRegion(address.getRegion());
    addressEntity.setPostalCode(address.getPostalCode());
    addressEntity.setCountry(address.getCountry());
    addressEntity.setCountryCode(address.getCountryCode());
    return addressEntity;
  }

  public static Address map(final AddressEntity addressEntity) {
    final Address address = new Address();
    address.setStreet(addressEntity.getStreet());
    address.setCity(addressEntity.getCity());
    address.setRegion(addressEntity.getRegion());
    address.setPostalCode(addressEntity.getPostalCode());
    address.setCountry(addressEntity.getCountry());
    address.setCountryCode(addressEntity.getCountryCode());
    return address;
  }
}

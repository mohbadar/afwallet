package af.gov.anar.ebreshna.configuration.payment.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  TransactionType {

    OneToOne("MOBILE_PAYMENT"),
    OneToMany("MACHINE_PAYMENT");

    private String content;
}

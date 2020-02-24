package af.gov.anar.ebreshna.configuration.network.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SubstationCapacity {


    SUB_400_200_KV("400/200KV"),
    SUB_200_132_KV("200/132KV"),
    SUB_132_33_KV("142/33KV"),
    SUB_220_132_33_KV("220/132/33KV"),
    SUB_33_11_KV("33/11KV");


    private String content;


}

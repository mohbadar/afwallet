package af.gov.anar.hooks.infrastructure.installation.tpm.asymmetric;

import tss.Tpm;
import tss.tpm.TPMS_NULL_ASYM_SCHEME;

/**
 * Class for encrypting the data using asymmetric cryto-alogirthm in TPM
 */
public class AsymmetricEncryptionService {

    private AsymmetricKeyCreationService asymmetricKeyCreationService = new AsymmetricKeyCreationService();

    /**
     * Encrypts the input data using the TPM
     *
     * @param tpm
     *            the instance of {@link Tpm}
     * @param dataToEncrypt
     *            the data to be encrypted
     * @return returns the TPM encrypted data
     */
    public byte[] encryptUsingTPM(Tpm tpm, byte[] dataToEncrypt) {
        return tpm.RSA_Encrypt(asymmetricKeyCreationService.createPersistentKey(tpm), dataToEncrypt,
                new TPMS_NULL_ASYM_SCHEME(), new byte[0]);
    }

}
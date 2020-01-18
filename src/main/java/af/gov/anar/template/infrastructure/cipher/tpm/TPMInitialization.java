package af.gov.anar.template.infrastructure.cipher.tpm;

import java.io.IOException;

import tss.Tpm;
import tss.TpmFactory;

/**
 * The class to initialize the {@link Tpm}
 */
public class TPMInitialization {

    private static Tpm tpm;

    private TPMInitialization() {
    }

    /**
     * Gets the instance of the platform TPM
     *
     * @return the instance of {@link Tpm}
     */
    public static Tpm getTPMInstance() {
        if (tpm == null) {
            tpm = TpmFactory.platformTpm();
        }

        return tpm;
    }

    /**
     * Closes the {@link Tpm} instance
     *
     * @throws IOException
     *             exception while closing the {@link Tpm}
     */
    public static void closeTPMInstance() throws IOException {
        if (tpm != null) {
            tpm.close();
        }
    }

}

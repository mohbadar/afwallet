package af.asr.corona.infrastructure.constant;

import lombok.Getter;

import static af.asr.corona.infrastructure.constant.ApplicationGenericConstants.PACKET_CREATION_EXP_CODE;

/**
 * Exception enum for Registration Services Sub-Module
 *
 */
public enum ExceptionConstants {

    REG_IO_EXCEPTION(PACKET_CREATION_EXP_CODE + "ZCM-001", "IO exception"),

    // PacketHandlerService
    REG_PACKET_CREATION_ERROR_CODE(PACKET_CREATION_EXP_CODE + "PHS-001", "The packet zip file is either null or empty"),

    // PacketCreationService
    REG_JSON_PROCESSING_EXCEPTION(PACKET_CREATION_EXP_CODE + "PCS-001", "Exception while parsing object to JSON"),
    REG_PACKET_CREATION_EXCEPTION(PACKET_CREATION_EXP_CODE + "PCS-002", "Unable to create packet zip from RegistrationDTO"),
    REG_PACKET_BIO_CBEFF_GENERATION_ERROR_CODE(PACKET_CREATION_EXP_CODE + "PCS-002", "Exception while creating CBEFF file for biometrics"),

    // AuditDAO
    REG_GET_AUDITS_EXCEPTION(PACKET_CREATION_EXP_CODE + "ADA-001", "Unable to fetch Audit Logs based on provided dates");



    /**
     * The constructor
     */
    private ExceptionConstants(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Getter
    private final String errorCode;
    @Getter
    private final String errorMessage;

}

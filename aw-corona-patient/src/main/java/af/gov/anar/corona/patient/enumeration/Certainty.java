package af.gov.anar.corona.patient.enumeration;
/**
 * The ConditionVerificationStatus is what we've historically called "diagnosis certainty" and what FHIR refers to as
 * ConditionVerificationStatus. This is the verification status to support or decline the clinical status of the condition or
 * diagnosis. The following subset of FHIR statuses are currently defined:
 * <li>{@link #PROVISIONAL}</li>
 * <li>{@link #CONFIRMED}</li>
 *
 * @since 2.2
 */
public enum Certainty {

    /**
     * This is a tentative diagnosis - still a candidate that is under consideration. This was called "Presumed" in the
     * original EMRAPI module implementation.
     */
    PROVISIONAL,

    /**
     * There is sufficient diagnostic and/or clinical evidence to treat this as a confirmed condition.
     */
    CONFIRMED

}

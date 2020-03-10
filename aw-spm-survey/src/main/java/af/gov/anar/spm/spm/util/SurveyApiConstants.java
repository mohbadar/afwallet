
package af.gov.anar.spm.spm.util;


public class SurveyApiConstants {
    
    public static final String SURVEY_RESOURCE_NAME = "survey";
    public static final String keyParamName = "key";
    public static final String nameParamName = "name";
    public static final String countryCodeParamName = "countrycode";
    public static final String descriptionParamName = "description";
    public static final String sequenceNumberParamName = "sequenceNo";
    public static final String valueParamName = "value";
    public static final String questionParamName = "question";
    public static final String optionsParamName = "options";
    public static final String textParamName = "text";
    public static final String lengthParamName = "length";
    
    //to validate length/max value  
    public static final Integer maxCountryCodeLength = 2;
    public static final Integer maxTextLength = 255;
    public static final Integer maxNameLength = 255;
    public static final Integer maxKeyLength = 32;
    public static final Integer maxOptionsValue = 9999;
    public static final Integer maxDescriptionLength = 4000;
    
}

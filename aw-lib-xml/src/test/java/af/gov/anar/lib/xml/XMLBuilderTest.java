package af.gov.anar.lib.xml;

import af.gov.anar.lib.xml.builder.BaseXMLBuilder;
import af.gov.anar.lib.xml.builder.XMLBuilder;

public class XMLBuilderTest extends BaseXMLBuilderTests {

    @Override
    public Class<? extends BaseXMLBuilder> XMLBuilderToTest() throws Exception {
        return XMLBuilder.class;
    }

    @Override
    protected boolean isRuntimeExceptionsOnly() {
        return false;
    }

}
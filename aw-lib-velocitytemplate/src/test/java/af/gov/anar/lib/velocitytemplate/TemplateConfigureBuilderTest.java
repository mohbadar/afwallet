package af.gov.anar.lib.velocitytemplate;


import af.gov.anar.lib.velocitytemplate.builder.TemplateManagerBuilder;
import af.gov.anar.lib.velocitytemplate.builder.TemplateManagerBuilderImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TemplateManagerBuilderImpl.class })
public class TemplateConfigureBuilderTest {

	@Autowired
	private TemplateManagerBuilder templateManagerBuilder;

	@Test
	public void buildDefaultConfiguration() {
		TemplateManagerBuilderImpl builder = (TemplateManagerBuilderImpl) templateManagerBuilder;
		builder.build();
		assertEquals("UTF-8", builder.getDefaultEncoding());
		assertEquals("classpath", builder.getResourceLoader());
		assertEquals(".", builder.getTemplatePath());
		// assertTrue(builder.isCache());

	}

	@Test
	public void buildWithCustomConfiguration() {
		TemplateManagerBuilderImpl builder = (TemplateManagerBuilderImpl) new TemplateManagerBuilderImpl()
				.encodingType("UTF-16").enableCache(false).resourcePath("/templates").resourceLoader("classpath");
		builder.build();
		assertEquals("UTF-16", builder.getDefaultEncoding());
		assertEquals("classpath", builder.getResourceLoader());
		assertEquals("/templates", builder.getTemplatePath());
		assertFalse(builder.isCache());
		builder.build();
	}
}


package af.gov.anar.hooks.template.data;

import af.gov.anar.hooks.template.domain.Template;
import af.gov.anar.hooks.template.domain.TemplateEntity;
import af.gov.anar.hooks.template.domain.TemplateType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TemplateData {

    @SuppressWarnings("unused")
    private final List<Map<String, Object>> entities;
    @SuppressWarnings("unused")
    private final List<Map<String, Object>> types;
    @SuppressWarnings("unused")
    private final Template template;

    private TemplateData(final Template template) {
        this.template = template;
        this.entities = getEntites();
        this.types = getTypes();
    }

    public static TemplateData template(final Template template) {
        return new TemplateData(template);
    }

    public static TemplateData template() {
        return new TemplateData(null);
    }

    private List<Map<String, Object>> getEntites() {
        final List<Map<String, Object>> l = new ArrayList<>();
        for (final TemplateEntity e : TemplateEntity.values()) {
            final Map<String, Object> m = new HashMap<>();
            m.put("id", e.getId());
            m.put("name", e.getName());
            l.add(m);
        }
        return l;
    }

    private List<Map<String, Object>> getTypes() {
        final List<Map<String, Object>> l = new ArrayList<>();
        for (final TemplateType e : TemplateType.values()) {
            final Map<String, Object> m = new HashMap<>();
            m.put("id", e.getId());
            m.put("name", e.getName());
            l.add(m);
        }
        return l;
    }
}

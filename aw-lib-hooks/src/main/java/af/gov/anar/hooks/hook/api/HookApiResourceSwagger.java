
package af.gov.anar.hooks.hook.api;

import af.gov.anar.hooks.hook.data.Event;
import af.gov.anar.hooks.hook.data.Field;
import af.gov.anar.hooks.hook.data.Grouping;
import af.gov.anar.hooks.hook.data.HookTemplateData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.LocalDate;

import java.util.List;


final class HookApiResourceSwagger {
    private HookApiResourceSwagger() {

    }

    @ApiModel(value = "PostHookRequest")
    public static final class PostHookRequest {
        private PostHookRequest () {

        }

        @ApiModelProperty(example = "Web")
        public String name;
        @ApiModelProperty(example = "true")
        public Boolean isActive;
        @ApiModelProperty(example = "Kremlin")
        public String displayName;
        @ApiModelProperty(example = "1")
        public Long templateId;
        public List<Event> events;
        public List<Field> config;
    }

    @ApiModel(value = "PostHookResponse")
    public static final class PostHookResponse {
        private PostHookResponse() {

        }
        @ApiModelProperty(example = "4")
        public Long resourceId;
    }

    @ApiModel(value = "GetHookResponse")
    public static final class GetHookResponse {
        private GetHookResponse() {

        }

        @ApiModelProperty(example = "1")
        public Long id;
        @ApiModelProperty(example = "Web")
        public String name;
        @ApiModelProperty(example = "Kremlin")
        public String displayName;
        @ApiModelProperty(example = "true")
        public Boolean isActive;
        @ApiModelProperty(example = "[2014, 9, 16]")
        public LocalDate createdAt;
        @ApiModelProperty(example = "[2014, 9, 16]")
        public LocalDate updatedAt;
        @ApiModelProperty(example = "1")
        public Long templateId;
        @ApiModelProperty(example = "My UGD")
        public String templateName;
        public List<Event> events;
        public List<Field> config;
    }

    @ApiModel(value = "GetHookTemplateResponse")
    public static final class GetHookTemplateResponse {
        private GetHookTemplateResponse() {

        }

        public List<HookTemplateData> templates;
        public List<Grouping> groupings;
    }

    @ApiModel(value = "DeleteHookResponse")
    public static final class DeleteHookResponse {
        private DeleteHookResponse() {

        }
        @ApiModelProperty(example = "4")
        public Long resourceId;
    }

    @ApiModel(value = "PutHookRequest")
    public static final class PutHookRequest {
        private PutHookRequest () {

        }

        @ApiModelProperty(example = "Web")
        public String name;
        @ApiModelProperty(example = "true")
        public Boolean isActive;
        @ApiModelProperty(example = "Kremlin")
        public String displayName;
        @ApiModelProperty(example = "1")
        public Long templateId;
        public List<Event> events;
        public List<Field> config;
    }

    @ApiModel(value = "PutHookResponse")
    public static final class PutHookResponse {
        private PutHookResponse () {

        }
        final class PutHookResponseChangesSwagger {
            private PutHookResponseChangesSwagger() {}
                @ApiModelProperty(example = "Kremlin")
                public String displayName;
                @ApiModelProperty(example = "1")
                public List<Event> events;
                public List<Field> config;
        }
        @ApiModelProperty(example = "4")
        public Long resourceId;
        public PutHookResponseChangesSwagger changes;
    }
}

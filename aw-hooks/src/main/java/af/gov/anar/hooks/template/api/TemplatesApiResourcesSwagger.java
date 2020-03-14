
package af.gov.anar.hooks.template.api;

import af.gov.anar.hooks.template.domain.TemplateMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

final class TemplatesApiResourcesSwagger {
    private TemplatesApiResourcesSwagger() {

    }

    @ApiModel(value = "GetTemplatesResponse")
    public static final class GetTemplatesResponse {
        private GetTemplatesResponse() {

        }
        @ApiModelProperty(example = "1")
        public Long id;
        @ApiModelProperty(example = "Text")
        public String name;
        @ApiModelProperty(example = "1")
        public Long entity;
        @ApiModelProperty(example = "0")
        public Long type;
        @ApiModelProperty(example = "This is a loan for {{loan.clientName}}")
        public String text;
        public List<TemplateMapper> mappers;

    }

    @ApiModel(value = "GetTemplatesTemplateResponse")
    public static final class GetTemplatesTemplateResponse {
        private GetTemplatesTemplateResponse() {

        }
        @ApiModelProperty(example = "1")
        public Long id;
        @ApiModelProperty(example = "Text")
        public String name;
        @ApiModelProperty(example = "1")
        public Long entity;
        @ApiModelProperty(example = "0")
        public Long type;
        @ApiModelProperty(example = "This is a loan for {{loan.clientName}}")
        public String text;
        public List<TemplateMapper> mappers;

    }

    @ApiModel(value = "PostTemplatesRequest")
    public static final class PostTemplatesRequest {
        private PostTemplatesRequest() {

        }
        @ApiModelProperty(example = "1")
        public Long id;
        @ApiModelProperty(example = "Text")
        public String name;
        @ApiModelProperty(example = "1")
        public Long entity;
        @ApiModelProperty(example = "0")
        public Long type;
        @ApiModelProperty(example = "This is a loan for {{loan.clientName}}")
        public String text;
        public List<TemplateMapper> mappers;

    }

    @ApiModel(value = "PostTemplatesResponse")
    public static final class PostTemplatesResponse {
        private PostTemplatesResponse() {

        }
        @ApiModelProperty(example = "1")
        public Long resourceId;

    }

    @ApiModel(value = "GetTemplatesTemplateIdResponse")
    public static final class GetTemplatesTemplateIdResponse {
        private GetTemplatesTemplateIdResponse() {

        }
        @ApiModelProperty(example = "1")
        public Long id;
        @ApiModelProperty(example = "Text")
        public String name;
        @ApiModelProperty(example = "1")
        public Long entity;
        @ApiModelProperty(example = "0")
        public Long type;
        @ApiModelProperty(example = "This is a loan for {{loan.clientName}}")
        public String text;
        public List<TemplateMapper> mappers;

    }

    @ApiModel(value = "PutTemplatesTemplateIdRequest")
    public static final class PutTemplatesTemplateIdRequest {
        private PutTemplatesTemplateIdRequest() {

        }
        @ApiModelProperty(example = "1")
        public Long id;
        @ApiModelProperty(example = "Text")
        public String name;
        @ApiModelProperty(example = "1")
        public Long entity;
        @ApiModelProperty(example = "0")
        public Long type;
        @ApiModelProperty(example = "This is a loan for {{loan.clientName}}")
        public String text;
        public List<TemplateMapper> mappers;

    }

    @ApiModel(value = "PutTemplatesTemplateIdResponse")
    public static final class PutTemplatesTemplateIdResponse {
        private PutTemplatesTemplateIdResponse() {

        }
        @ApiModelProperty(example = "1")
        public Long resourceId;

    }

    @ApiModel(value = "DeleteTemplatesTemplateIdResponse")
    public static final class DeleteTemplatesTemplateIdResponse {
        private DeleteTemplatesTemplateIdResponse() {

        }
        @ApiModelProperty(example = "1")
        public Long resourceId;

    }
}

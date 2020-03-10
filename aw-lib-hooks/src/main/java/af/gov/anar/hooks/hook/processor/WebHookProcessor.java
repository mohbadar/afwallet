
package af.gov.anar.hooks.hook.processor;

import static af.gov.anar.hooks.hook.api.HookApiConstants.contentTypeName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.payloadURLName;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import af.gov.anar.hooks.hook.domain.Hook;
import af.gov.anar.hooks.hook.domain.HookConfiguration;
import org.springframework.stereotype.Service;

import retrofit.Callback;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class WebHookProcessor implements HookProcessor {

	@Override
	public void process(final Hook hook,
			@SuppressWarnings("unused")
			final String payload, final String entityName,
			final String actionName, final String tenantIdentifier,
			final String authToken) {

		final Set<HookConfiguration> config = hook.getHookConfig();

		String url = "";
		String contentType = "";

		for (final HookConfiguration conf : config) {
			final String fieldName = conf.getFieldName();
			if (fieldName.equals(payloadURLName)) {
				url = conf.getFieldValue();
			}
			if (fieldName.equals(contentTypeName)) {
				contentType = conf.getFieldValue();
			}
		}

		sendRequest(url, contentType, payload, entityName, actionName,
				tenantIdentifier, authToken);

	}

	@SuppressWarnings("unchecked")
	private void sendRequest(final String url, final String contentType,
			final String payload, final String entityName,
			final String actionName, final String tenantIdentifier,
			@SuppressWarnings("unused") final String authToken) {

		final String fineractEndpointUrl = System.getProperty("baseUrl");
		final WebHookService service = ProcessorHelper
				.createWebHookService(url);

		@SuppressWarnings("rawtypes")
		final Callback callback = ProcessorHelper.createCallback(url);

		if (contentType.equalsIgnoreCase("json")
				|| contentType.contains("json")) {
			final JsonObject json = new JsonParser().parse(payload)
					.getAsJsonObject();
			service.sendJsonRequest(entityName, actionName, tenantIdentifier,
					fineractEndpointUrl, json, callback);
		} else {
			Map<String, String> map = new HashMap<>();
			map = new Gson().fromJson(payload, map.getClass());
			service.sendFormRequest(entityName, actionName, tenantIdentifier,
					fineractEndpointUrl, map, callback);
		}

	}

}

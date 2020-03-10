
package af.gov.anar.hooks.hook.processor;

import java.util.Map;

import af.gov.anar.hooks.hook.processor.data.SmsProviderData;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

import com.google.gson.JsonObject;

public interface WebHookService {

	final static String ENTITY_HEADER = "X-Anar-Entity";
	final static String ACTION_HEADER = "X-Anar-Action";
	final static String TENANT_HEADER = "Anar-Platform-TenantId";
	final static String ENDPOINT_HEADER = "X-Anar-Endpoint";
	final static String API_KEY_HEADER = "X-Anar-API-Key";

	// Ping
	@GET("/")
	Response sendEmptyRequest();

	// Template - Web
	@POST("/")
	void sendJsonRequest(@Header(ENTITY_HEADER) String entityHeader,
                         @Header(ACTION_HEADER) String actionHeader,
                         @Header(TENANT_HEADER) String tenantHeader,
                         @Header(ENDPOINT_HEADER) String endpointHeader,
                         @Body JsonObject result, Callback<Response> callBack);

	@FormUrlEncoded
	@POST("/")
	void sendFormRequest(@Header(ENTITY_HEADER) String entityHeader,
                         @Header(ACTION_HEADER) String actionHeader,
                         @Header(TENANT_HEADER) String tenantHeader,
                         @Header(ENDPOINT_HEADER) String endpointHeader,
                         @FieldMap Map<String, String> params, Callback<Response> callBack);

	// Template - SMS Bridge
	@POST("/")
	void sendSmsBridgeRequest(@Header(ENTITY_HEADER) String entityHeader,
                              @Header(ACTION_HEADER) String actionHeader,
                              @Header(TENANT_HEADER) String tenantHeader,
                              @Header(API_KEY_HEADER) String apiKeyHeader,
                              @Body JsonObject result, Callback<Response> callBack);

	@POST("/configuration")
	String sendSmsBridgeConfigRequest(@Body SmsProviderData config);

}

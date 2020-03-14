
package af.gov.anar.hooks.hook.processor.data;

import static af.gov.anar.hooks.hook.api.HookApiConstants.payloadURLName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.phoneNumberName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.smsProviderAccountIdName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.smsProviderName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.smsProviderTokenIdName;

import java.util.Set;

import af.gov.anar.hooks.hook.domain.HookConfiguration;

public class SmsProviderData {

	private String url;
	private String phoneNo;
	private String smsProvider;
	private String smsProviderAccountId;
	private String smsProviderToken;
	
	private String tenantId;
	private String mifosToken;
	private String endpoint;
	
	public SmsProviderData(final Set<HookConfiguration> config) {
		
		for (final HookConfiguration conf : config) {
			final String fieldName = conf.getFieldName();
			if (fieldName.equals(payloadURLName)) {
				this.url = conf.getFieldValue();
			}
			if (fieldName.equals(smsProviderName)) {
				this.smsProvider = conf.getFieldValue();
			}
			if (fieldName.equals(smsProviderAccountIdName)) {
				this.smsProviderAccountId = conf.getFieldValue();
			}
			if (fieldName.equals(smsProviderTokenIdName)) {
				this.smsProviderToken = conf.getFieldValue();
			}
			if (fieldName.equals(phoneNumberName)) {
				this.phoneNo = conf.getFieldValue();
			}
		}
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhoneNumber() {
		return phoneNo;
	}

	public String getSmsProvider() {
		return smsProvider;
	}

	public String getSmsProviderAccountId() {
		return smsProviderAccountId;
	}

	public String getSmsProviderTokenId() {
		return smsProviderToken;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getMifosToken() {
		return mifosToken;
	}

	public void setMifosToken(String mifosToken) {
		this.mifosToken = mifosToken;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
}

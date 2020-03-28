
package af.gov.anar.corona.notification.domain;

import java.util.List;
import java.util.Objects;

public class SMSPage {
	
	private List<SMSConfiguration> smsConfigurations;
	private Integer totalPages;
	private Long totalElements;
	
	public SMSPage() {
		super();
	}
	
	public List<SMSConfiguration> getSmsConfigurations() {
		return this.smsConfigurations;
	}
	
	public void setSmsConfigurations(final List<SMSConfiguration> smsConfigurations) {
		this.smsConfigurations = smsConfigurations;
	}
	
	public Integer getTotalPages() {
		return this.totalPages;
	}
	
	public void setTotalPages(final Integer totalPages) {
		this.totalPages = totalPages;
	}
	
	public Long getTotalElements() {
		return this.totalElements;
	}
	
	public void setTotalElements(final Long totalElements) {
		this.totalElements = totalElements;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SMSPage smsPage = (SMSPage) o;
		return Objects.equals(smsConfigurations, smsPage.smsConfigurations) &&
				Objects.equals(totalPages, smsPage.totalPages) &&
				Objects.equals(totalElements, smsPage.totalElements);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(smsConfigurations, totalPages, totalElements);
	}
	
	@Override
	public String toString() {
		return "SMSPage{" +
				"smsConfigurations=" + smsConfigurations +
				", totalPages=" + totalPages +
				", totalElements=" + totalElements +
				'}';
	}
}

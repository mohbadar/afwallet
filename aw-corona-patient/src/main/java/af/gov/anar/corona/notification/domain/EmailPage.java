
package af.gov.anar.corona.notification.domain;

import java.util.List;
import java.util.Objects;

public class EmailPage {
	
	private List<EmailConfiguration> emailConfigurations;
	private Integer totalPages;
	private Long totalElements;
	
	public EmailPage() {
		super();
	}
	
	public List<EmailConfiguration> getEmailConfiguration() {
		return this.emailConfigurations;
	}
	
	public void setEmailConfiguration(final List<EmailConfiguration> emailConfiguration) {
		this.emailConfigurations = emailConfigurations;
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
		EmailPage emailPage = (EmailPage) o;
		return Objects.equals(emailConfigurations, emailPage.emailConfigurations) &&
				Objects.equals(totalPages, emailPage.totalPages) &&
				Objects.equals(totalElements, emailPage.totalElements);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(emailConfigurations, totalPages, totalElements);
	}
	
	@Override
	public String toString() {
		return "EmailPage{" +
				"emailConfigurations=" + emailConfigurations +
				", totalPages=" + totalPages +
				", totalElements=" + totalElements +
				'}';
	}
}

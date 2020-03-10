
package af.gov.anar.query.adhocquery.data;

import af.gov.anar.query.adhocquery.domain.ReportRunFrequency;
import af.gov.anar.lang.data.EnumOptionData;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Immutable data object represent note or case information AdHocData
 * 
 */
public class AdHocData {



	@SuppressWarnings("unused")
    private final Long id;
    @SuppressWarnings("unused")
    private final String name;
	@SuppressWarnings("unused")
    private final String query;
	@SuppressWarnings("unused")
    private final String tableName;
	@SuppressWarnings("unused")
    private final String tableFields;
	@SuppressWarnings("unused")
	private final String email;
	@SuppressWarnings("unused")
    private final boolean isActive;
	@SuppressWarnings("unused")
    private final DateTime createdOn;
	@SuppressWarnings("unused")
	private final Long createdById;
	@SuppressWarnings("unused")
    private final Long updatedById;   
	@SuppressWarnings("unused")
    private final DateTime updatedOn;
	@SuppressWarnings("unused")
	private final String createdBy;
	
    private final List<EnumOptionData> reportRunFrequencies;

    private final Long reportRunFrequency;

    private final Long reportRunEvery;

    private final DateTime lastRun;

    public AdHocData(final Long id, final String name, final String query, final String tableName, final String tableFields,
					 final boolean isActive, final DateTime createdOn, final Long createdById, final Long updatedById,
					 final DateTime updatedOn, final String createdBy, final String email,
					 final List<EnumOptionData> reportRunFrequencies, final Long reportRunFrequency, final Long reportRunEvery,
					 final DateTime lastRun) {
        this.id = id;
        this.name=name;
        this.query=query;
        this.tableName = tableName;
        this.tableFields = tableFields;
        this.isActive = isActive;
        this.createdOn = createdOn;
        this.createdById = createdById;
        this.updatedById=updatedById;
        this.updatedOn=updatedOn;
        this.createdBy=createdBy;
        this.email=email;
	    this.reportRunFrequencies = reportRunFrequencies;
	    this.reportRunFrequency = reportRunFrequency;
	    this.reportRunEvery = reportRunEvery;
	    this.lastRun = lastRun;
    }
    public static AdHocData template() {
	    List<EnumOptionData> reportRunFrequencies = Arrays.stream(ReportRunFrequency.values()).map(rrf -> new EnumOptionData(
		    (long) rrf.getValue(), rrf.getCode(), rrf.getCode()
	    )).collect(Collectors.toList());

	    AdHocData adHocData = new AdHocData(null,null,null,null,null,false,null,null,null,null,null,null, reportRunFrequencies, null, null, null);
		return adHocData;
    }
    public Long getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getQuery() {
		return this.query;
	}
	public String getTableName() {
		return this.tableName;
	}
	public String getTableFields() {
		return this.tableFields;
	}
	public String getEmail() {
		return this.email;
	}
	public boolean isActive() {
		return this.isActive;
	}
	public DateTime getCreatedOn() {
		return this.createdOn;
	}
	public Long getCreatedById() {
		return this.createdById;
	}
	public Long getUpdatedById() {
		return this.updatedById;
	}
	public DateTime getUpdatedOn() {
		return this.updatedOn;
	}
	public String getCreatedBy() {
		return this.createdBy;
	}
	public List<EnumOptionData> getReportRunFrequencies() {
		return this.reportRunFrequencies;
	}
	public Long getReportRunFrequency() {
		return this.reportRunFrequency;
	}
	public Long getReportRunEvery() {
		return this.reportRunEvery;
	}
	public DateTime getLastRun() {
		return this.lastRun;
	}
}
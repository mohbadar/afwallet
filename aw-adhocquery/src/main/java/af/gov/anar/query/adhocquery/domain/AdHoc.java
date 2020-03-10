
package af.gov.anar.query.adhocquery.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import af.gov.anar.query.adhocquery.api.AdHocJsonInputParams;
import af.gov.anar.query.adhocquery.utils.SQLInjectionValidator;
import af.gov.anar.query.infrastructure.common.command.JsonCommand;
import af.gov.anar.lang.data.AbstractPersistableCustom;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;


@Entity
@Table(name = "m_adhoc")
public class AdHoc extends AbstractPersistableCustom<Long> {

  	@Column(name = "name", length = 100)
    private  String name;
    
    @Column(name = "query", length = 2000)
    private  String query;
   	
    @Column(name = "table_name", length = 100)
    private  String tableName;
    
    
    @Column(name = "table_fields", length = 2000)
    private  String tableFields;
   	
    @Column(name = "email", length = 500)
    private  String email;

    @Column(name = "report_run_frequency_code")
    private Long reportRunFrequency;

    @Column(name = "report_run_every")
    private Long reportRunEvery;

	@Column(name = "IsActive", nullable = false)
    private boolean isActive = false;
   	
    private AdHoc(final String name, final String query,final String tableName,final String tableFields ,final String email, final Long reportRunFrequency, final Long reportRunEvery, final boolean isActive) {
        this.name = StringUtils.defaultIfEmpty(name, null);
        this.query=StringUtils.defaultIfEmpty(query,null);
        this.tableName=StringUtils.defaultIfEmpty(tableName,null);
        this.tableFields=StringUtils.defaultIfEmpty(tableFields,null);
        this.email=StringUtils.defaultIfEmpty(email,null);
        this.reportRunFrequency = reportRunFrequency;
        this.reportRunEvery = reportRunEvery;
        this.isActive = BooleanUtils.toBooleanDefaultIfNull(isActive, false);
       
    }
    public static AdHoc fromJson(final JsonCommand command) {
        final String name = command.stringValueOfParameterNamed(AdHocJsonInputParams.NAME.getValue());
        
        String commandQuery=command.stringValueOfParameterNamed(AdHocJsonInputParams.QUERY.getValue());
        
        SQLInjectionValidator.validateAdhocQuery(commandQuery);
        final String query = commandQuery;
        final String tableName = command.stringValueOfParameterNamed(AdHocJsonInputParams.TABLENAME.getValue());
        final String tableFields = command.stringValueOfParameterNamed(AdHocJsonInputParams.TABLEFIELDS.getValue());
        final String email = command.stringValueOfParameterNamed(AdHocJsonInputParams.EMAIL.getValue());
        final Long reportRunFrequency = command.longValueOfParameterNamed(AdHocJsonInputParams.REPORT_RUN_FREQUENCY.getValue());
        final Long reportRunEvery = command.longValueOfParameterNamed(AdHocJsonInputParams.REPORT_RUN_EVERY.getValue());
        final boolean isActive = command.booleanPrimitiveValueOfParameterNamed(AdHocJsonInputParams.ISACTIVE.getValue());
        return new AdHoc(name,query,tableName,tableFields, email, reportRunFrequency, reportRunEvery, isActive);
    }
    
    public Map<String, Object> update(final JsonCommand command) {

        final Map<String, Object> actualChanges = new LinkedHashMap<>(7);

        final String nameParamName = "name";
        if (command.isChangeInStringParameterNamed(nameParamName, this.name)) {
            final String newValue = command.stringValueOfParameterNamed(nameParamName);
            actualChanges.put(nameParamName, newValue);
            this.name = newValue;
        }

        final String descriptionParamName = "query";
        if (command.isChangeInStringParameterNamed(descriptionParamName, this.query)) {
            final String newValue = command.stringValueOfParameterNamed(descriptionParamName);
            actualChanges.put(descriptionParamName, newValue);
            this.query = newValue;
        }
        final String tableName = "tableName";
        if (command.isChangeInStringParameterNamed(tableName, this.tableName)) {
            final String newValue = command.stringValueOfParameterNamed(tableName);
            actualChanges.put(tableName, newValue);
            this.tableName = newValue;
        }
        final String tableField = "tableFields";
        if (command.isChangeInStringParameterNamed(tableField, this.tableFields)) {
            final String newValue = command.stringValueOfParameterNamed(tableField);
            actualChanges.put(tableField, newValue);
            this.tableFields = newValue;
        }
        final String email = "email";
        if (command.isChangeInStringParameterNamed(email, this.email)) {
            final String newValue = command.stringValueOfParameterNamed(email);
            actualChanges.put(email, newValue);
            this.email = newValue;
        }
        final String reportRunFrequency = "reportRunFrequency";
        if (command.isChangeInLongParameterNamed(reportRunFrequency, this.getReportRunFrequency())) {
            final Long newValue = command.longValueOfParameterNamed(reportRunFrequency);
            actualChanges.put(reportRunFrequency, newValue);
            this.reportRunFrequency = newValue;
        }
        final String reportRunEvery = "reportRunEvery";
        if (command.isChangeInLongParameterNamed(reportRunEvery, this.getReportRunEvery())) {
            final Long newValue = command.longValueOfParameterNamed(reportRunEvery);
            actualChanges.put(reportRunEvery, newValue);
            this.reportRunEvery = newValue;
        }
        final String paramisActive = "isActive";
        if (command.isChangeInBooleanParameterNamed(paramisActive, this.isActive)) {
        	final Boolean newValue = command.booleanObjectValueOfParameterNamed(paramisActive);
            actualChanges.put(paramisActive, newValue);
            this.isActive = newValue;
        }
        return actualChanges;
    }
    
    public String getName() {
		return name;
	}
	public String getQuery() {
		return query;
	}
	public String getTableName() {
		return tableName;
	}
	public String getTableFields() {
		return tableFields;
	}
    public boolean isActive() {
        return this.isActive;
    }
	public String getEmail() {
		return email;
	}
    public void disableActive() {
        this.isActive = true;
    }
    public void enableActive() {
    	this.isActive = false;
    }
    public Long getReportRunFrequency() {
        return this.reportRunFrequency;
    }
    public Long getReportRunEvery() {
        return this.reportRunEvery;
    }
}

package af.gov.anar.query.adhocquery.service;


import af.gov.anar.query.adhocquery.data.AdHocData;
import af.gov.anar.query.adhocquery.domain.ReportRunFrequency;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

@Service(value = "adHocScheduledJobRunnerService")
public class AdHocScheduledJobRunnerServiceImpl implements AdHocScheduledJobRunnerService {

    private final static Logger logger = LoggerFactory.getLogger(AdHocScheduledJobRunnerServiceImpl.class);
    private final AdHocReadPlatformService adHocReadPlatformService;
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public AdHocScheduledJobRunnerServiceImpl(final JdbcTemplate jdbcTemplate,
    		final AdHocReadPlatformService adHocReadPlatformService
            ) {
    	this.jdbcTemplate = jdbcTemplate;
        this.adHocReadPlatformService = adHocReadPlatformService;
       
    }

    @Transactional
    @Override
//    @CronTarget(jobName = JobName.GENERATE_ADHOCCLIENT_SCEHDULE)
    public void generateClientSchedule() {
    	final Collection<AdHocData> adhocs = this.adHocReadPlatformService.retrieveAllActiveAdHocQuery();
        if(adhocs.size()>0){
        	adhocs.forEach(adhoc->{
        	    boolean run = true;
                LocalDate next = null;
        	    if (adhoc.getReportRunFrequency() != null) {
                    if (adhoc.getLastRun() != null) {
                        LocalDate start = adhoc.getLastRun().toLocalDate();
                        LocalDate end = new DateTime().toLocalDate();
                        switch (ReportRunFrequency.fromId(adhoc.getReportRunFrequency())) {
                            case DAILY:
                                next = start.plusDays(1);
                                run = Days.daysBetween(start, end).getDays() >= 1;
                                break;
                            case WEEKLY:
                                next = start.plusDays(7);
                                run = Days.daysBetween(start, end).getDays() >= 7;
                                break;
                            case MONTHLY:
                                next = start.plusMonths(1);
                                run = Months.monthsBetween(start, end).getMonths() >= 1;
                                break;
                            case YEARLY:
                                next = start.plusYears(1);
                                run = Years.yearsBetween(start, end).getYears() >= 1;
                                break;
                            case CUSTOM:
                                next = start.plusDays((int) (long) adhoc.getReportRunEvery());
                                run = Days.daysBetween(start, end).getDays() >= adhoc.getReportRunEvery();
                                break;
                            default:
                                throw new IllegalStateException();
                        }

                    }
                }

                if (run) {
                    //jdbcTemplate.execute("truncate table "+adhoc.getTableName());
                    final StringBuilder insertSqlBuilder = new StringBuilder(900);
                    insertSqlBuilder
                            .append("INSERT INTO ")
                            .append(adhoc.getTableName()+"(")
                            .append(adhoc.getTableFields()+") ")
                            .append(adhoc.getQuery());
                    if (insertSqlBuilder.length() > 0) {
                        final int result = this.jdbcTemplate.update(insertSqlBuilder.toString());
                        logger.info(": Results affected by inserted: " + result);

                        this.jdbcTemplate.update("UPDATE m_adhoc SET last_run=? WHERE id=?", new Date(), adhoc.getId());
                    }
                } else {
                    logger.info( ": Skipping execution of " + adhoc.getName() + ", scheduled for execution on " + next);
                }
            });	
        }else{
        	logger.info( "Nothing to update ");
        }
        
        
   
    }

}

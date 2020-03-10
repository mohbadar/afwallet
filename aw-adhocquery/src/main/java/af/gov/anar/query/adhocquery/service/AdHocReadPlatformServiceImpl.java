
package af.gov.anar.query.adhocquery.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import af.gov.anar.query.adhocquery.data.AdHocData;
import af.gov.anar.query.adhocquery.exception.AdHocNotFoundException;
import af.gov.anar.query.infrastructure.common.command.JdbcSupport;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class AdHocReadPlatformServiceImpl implements AdHocReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final AdHocMapper adHocRowMapper;

    @Autowired
    public AdHocReadPlatformServiceImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.adHocRowMapper = new AdHocMapper();
    }

    @Override
    public Collection<AdHocData> retrieveAllAdHocQuery() {
        final String sql = "select " + this.adHocRowMapper.schema() + " order by r.id";

        return this.jdbcTemplate.query(sql, this.adHocRowMapper);
    }

    @Override
    public Collection<AdHocData> retrieveAllActiveAdHocQuery() {
        final String sql = "select " + this.adHocRowMapper.schema() + " where r.IsActive = 1 order by r.id";

        return this.jdbcTemplate.query(sql, this.adHocRowMapper);
    }

    @Override
    public AdHocData retrieveOne(final Long id) {

        try {
            final String sql = "select " + this.adHocRowMapper.schema() + " where r.id=?";

            return this.jdbcTemplate.queryForObject(sql, this.adHocRowMapper, new Object[] { id });
        } catch (final EmptyResultDataAccessException e) {
            throw new AdHocNotFoundException(id);
        }
    }

    protected static final class AdHocMapper implements RowMapper<AdHocData> {

        @Override
        public AdHocData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long id = JdbcSupport.getLong(rs, "id");
            final String name = rs.getString("name");
            final String query = rs.getString("query");
            final String tableName=rs.getString("tableName");
            final String tableFields=rs.getString("tableField");
            final Boolean isActive = rs.getBoolean("isActive");
            final DateTime createdDate = JdbcSupport.getDateTime(rs, "createdDate");
            final Long createdById = JdbcSupport.getLong(rs, "createdById");
            final Long updatedById=JdbcSupport.getLong(rs, "updatedById");
            final DateTime updatedOn=JdbcSupport.getDateTime(rs, "updatedOn");
            final String createdByUsername=rs.getString("createdBy");
            final String email=rs.getString("email");
            final Long reportRunFrequency=JdbcSupport.getLong(rs, "report_run_frequency_code");
            final Long reportRunEvery=JdbcSupport.getLong(rs, "report_run_every");
            final DateTime lastRun = JdbcSupport.getDateTime(rs, "last_run");

            return new AdHocData(id,name,query, tableName,tableFields,isActive,createdDate,createdById,updatedById,updatedOn,createdByUsername,email, AdHocData.template().getReportRunFrequencies(), reportRunFrequency, reportRunEvery, lastRun);
        }

        public String schema() {
            return " r.id as id, r.name as name, r.query as query, r.table_name as tableName,r.table_fields as tableField ,r.IsActive as isActive ,r.email as email ,"
                    + " r.report_run_frequency_code, r.report_run_every, r.last_run, "
            		+ " r.created_date as createdDate, r.createdby_id as createdById,cb.username as createdBy,r.lastmodifiedby_id as updatedById ,r.lastmodified_date as updatedOn "
                    + " from m_adhoc r left join m_appuser cb on cb.id=r.createdby_id left join m_appuser mb on mb.id=r.lastmodifiedby_id";
            		        
        }
    }

	@Override
	public AdHocData retrieveNewAdHocDetails() {
		 return AdHocData.template();
	}

   
}
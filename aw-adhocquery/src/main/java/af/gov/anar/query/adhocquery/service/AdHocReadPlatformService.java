
package af.gov.anar.query.adhocquery.service;

import af.gov.anar.query.adhocquery.data.AdHocData;

import java.util.Collection;


public interface AdHocReadPlatformService {

    Collection<AdHocData> retrieveAllAdHocQuery();

    Collection<AdHocData> retrieveAllActiveAdHocQuery();

    AdHocData retrieveOne(Long adHocId);
    AdHocData retrieveNewAdHocDetails();
    
}
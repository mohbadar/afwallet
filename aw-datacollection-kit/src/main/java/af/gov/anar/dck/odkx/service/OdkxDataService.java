package af.gov.anar.dck.odkx.service;
import javax.xml.parsers.ParserConfigurationException;

import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.useradministration.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;

public interface OdkxDataService {
	public InputStream executeCommand(String command) throws IOException;
	public String syncFormData(Form form, User loggedInUser, String syncType, String rowsJson) throws ParserConfigurationException, InvalidObjectException;
	public String dbSyncOdkx(Form form, User loggedInUser, String rowsJson) throws ParserConfigurationException, InvalidObjectException, IOException;
	public String syncFormAttachments(Form form, User loggedInUser, String syncType, String rowsStr) throws ParserConfigurationException, IOException;
}

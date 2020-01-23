package af.gov.anar.template.infrastructure.installation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import af.gov.anar.lang.infrastructure.exception.common.ExceptionUtils;
import af.gov.anar.lib.file.FileUtility;
import af.gov.anar.lib.hmac.HMACUtility;
import af.gov.anar.lib.logger.Logger;
import af.gov.anar.template.infrastructure.constant.ApplicationGenericConstants;
import af.gov.anar.template.infrastructure.util.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Update the Application
 *
 */
//@Component
public class SoftwareInstallationHandler {

    public SoftwareInstallationHandler() throws IOException {
        try (InputStream keyStream = SoftwareInstallationHandler.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties properties = new Properties();
            properties.load(keyStream);

            serverRegClientURL = properties.getProperty("anar.reg.client.url");

            latestVersion = properties.getProperty("anar.reg.version");

            LOGGER.info(ApplicationGenericConstants.SOFTWARE_INSTALLATION_HANDLER, ApplicationGenericConstants.APPLICATION_NAME,
                    ApplicationGenericConstants.APPLICATION_ID, "Loading anar-application.properties completed");

            getLocalManifest();

            deleteUnNecessaryJars();
        }

    }

    private static String SLASH = "/";

    private static String manifestFile = "MANIFEST.MF";

    private  String serverRegClientURL;

    private static String libFolder = "lib/";
    private String binFolder = "bin/";

    private String currentVersion;

    private String latestVersion;

    private Manifest localManifest;

    private Manifest serverManifest;

    private String anar = "anar";

    private static final Logger LOGGER = LoggerFactory.getLogger(SoftwareInstallationHandler.class);

    private String getLatestVersion() {
        LOGGER.info(ApplicationGenericConstants.SOFTWARE_INSTALLATION_HANDLER, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Getting latest version : " + latestVersion);

        return latestVersion;
    }

    public String getCurrentVersion() throws IOException {

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Getting current version started");

        // Get Local manifest file
        getLocalManifest();
        if (localManifest != null) {
            setCurrentVersion((String) localManifest.getMainAttributes().get(Attributes.Name.MANIFEST_VERSION));
        }

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Getting current version completed");

        return currentVersion;
    }

    public void installJars() throws IOException, af.gov.anar.lang.infrastructure.exception.common.IOException, af.gov.anar.lang.infrastructure.exception.common.IOException {

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Started installing jars");

        // Get Latest Version
        getLatestVersion();

        // Get Server Manifest
        getServerManifest();

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Started downloading server manifest and replacing");

        // replace local manifest with Server manifest
        serverManifest.write(new FileOutputStream(new File(manifestFile)));

        Map<String, Attributes> serverAttributes = serverManifest.getEntries();
        List<String> downloadJars = new LinkedList<>();
        List<String> deletableJars = new LinkedList<>();
        List<String> checkableJars = new LinkedList<>();

        if (localManifest != null) {

            Map<String, Attributes> localAttributes = localManifest.getEntries();

            // Compare local and server Manifest
            for (Entry<String, Attributes> jar : localAttributes.entrySet()) {
                checkableJars.add(jar.getKey());
                if (!serverAttributes.containsKey(jar.getKey())) {

                    /* unnecessary jar after update */
                    deletableJars.add(jar.getKey());

                } else {
                    Attributes localAttribute = jar.getValue();
                    Attributes serverAttribute = serverAttributes.get(jar.getKey());
                    if (!localAttribute.getValue(Attributes.Name.CONTENT_TYPE)
                            .equals(serverAttribute.getValue(Attributes.Name.CONTENT_TYPE))) {

                        /* Jar to be downloaded */
                        downloadJars.add(jar.getKey());

                    }
                    serverManifest.getEntries().remove(jar.getKey());

                }
            }

        }
        for (Entry<String, Attributes> jar : serverAttributes.entrySet()) {
            downloadJars.add(jar.getKey());
        }

        getServerManifest();

        deleteJars(deletableJars);

        checkableJars.removeAll(deletableJars);
        checkableJars.removeAll(downloadJars);

        // Download latest jars if not in local
        checkJars(latestVersion, downloadJars);

        // Un-Modified jars exist or not
        checkJars(latestVersion, checkableJars);

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Completed installing jars");

    }

    private void checkJars(String version, List<String> checkableJars) {

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Checking jars : " + checkableJars.toString());

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (String jarFile : checkableJars) {

            executorService.execute(new Runnable() {
                public void run() {

                    try {

                        String folder = jarFile.contains(anar) ? binFolder : libFolder;

                        File jarInFolder = new File(folder + jarFile);
                        if (!jarInFolder.exists() || (!isCheckSumValid(jarInFolder,
                                (currentVersion.equals(version)) ? localManifest : serverManifest)
                                && FileUtility.deleteQuietly(jarInFolder))) {

                            // Download Jar
                            Files.copy(getInputStreamOfJar(version, jarFile), jarInFolder.toPath());

                        }

                    } catch (IOException ioException) {
                        LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                                ApplicationGenericConstants.APPLICATION_ID,
                                ioException.getMessage() + ExceptionUtils.getStackTrace(ioException));

                        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                                ApplicationGenericConstants.APPLICATION_ID, "Terminating application");

                        // TODO Need to terminate from here.
                        System.exit(0);
                    }
                }
            });

        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(500, TimeUnit.SECONDS);
        } catch (Exception exception) {
            executorService.shutdown();
            LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                    ApplicationGenericConstants.APPLICATION_ID, exception.getMessage() + ExceptionUtils.getStackTrace(exception));

        }
    }

    private InputStream getInputStreamOfJar(String version, String jarName) throws IOException {
        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Started Downloading of : " + jarName);

        return getInputStreamOf(serverRegClientURL + version + SLASH + libFolder + jarName);

    }

    private void deleteJars(List<String> deletableJars) throws IOException, af.gov.anar.lang.infrastructure.exception.common.IOException {

        for (String jarName : deletableJars) {
            deleteJar(jarName);
        }

    }

    private void deleteJar(String jarName) throws IOException, af.gov.anar.lang.infrastructure.exception.common.IOException {

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Started Deleting : " + jarName);

        File deleteFile = null;

        String deleteFolder = jarName.contains(anar) ? binFolder : libFolder;

        deleteFile = new File(deleteFolder + jarName);

        if (deleteFile.exists()) {
            // Delete Jar
            FileUtility.forceDelete(deleteFile);

        }

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Completed Deleting : " + jarName);

    }

    private Manifest getLocalManifest() throws IOException {

        File localManifestFile = new File(manifestFile);

        if (localManifestFile.exists()) {

            // Set Local Manifest
            setLocalManifest(new Manifest(new FileInputStream(localManifestFile)));

        }
        return localManifest;
    }

    private Manifest getServerManifest() throws IOException {

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Started Downloading manifest of version :" + getLatestVersion());

        // Get latest Manifest from server

        // Get latest Manifest from server
        setServerManifest(
                new Manifest(getInputStreamOf(serverRegClientURL + getLatestVersion() + SLASH + manifestFile)));
        setLatestVersion(serverManifest.getMainAttributes().getValue(Attributes.Name.MANIFEST_VERSION));

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Completed Downloading manifest of version :" + getLatestVersion());

        return serverManifest;

    }

    private void setLocalManifest(Manifest localManifest) {
        this.localManifest = localManifest;
    }

    private void setServerManifest(Manifest serverManifest) {
        this.serverManifest = serverManifest;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public boolean hasRequiredJars() {

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Checking required jars of local started");

        Map<String, Attributes> localAttributes = localManifest.getEntries();

        List<String> checkableJars = new LinkedList<>();
        for (Entry<String, Attributes> jar : localAttributes.entrySet()) {
            checkableJars.add(jar.getKey());
        }

        // check all the jars in the manifest were available in zip extracted folder
        if (!checkableJars.isEmpty()) {
            return checkLocalJars(checkableJars);
        }

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Completed required jars of local started");

        return true;
    }

    private boolean checkLocalJars(List<String> jarList) {
        for (String jarFile : jarList) {

            File jar = jarFile.contains(anar) ? new File(binFolder + SLASH + jarFile)
                    : new File(libFolder + SLASH + jarFile);

            if (!(jar.exists()) || !isCheckSumValid(jar, localManifest)) {
                return false;
            }

        }

        return true;
    }

    private boolean isCheckSumValid(File jarFile, Manifest manifest) {

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Checking check sum for : " + jarFile.getName());

        String checkSum;
        try {
            checkSum = HMACUtility.digestAsPlainText(HMACUtility.generateHash(Files.readAllBytes(jarFile.toPath())));
            String manifestCheckSum = (String) manifest.getEntries().get(jarFile.getName())
                    .get(Attributes.Name.CONTENT_TYPE);

            return manifestCheckSum.equals(checkSum);

        } catch (IOException ioException) {

            LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                    ApplicationGenericConstants.APPLICATION_ID,
                    ioException.getMessage() + ExceptionUtils.getStackTrace(ioException));

            try {
                LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                        ApplicationGenericConstants.APPLICATION_ID, "Deleting : " + jarFile.getName());

                FileUtility.forceDelete(jarFile);
            } catch (af.gov.anar.lang.infrastructure.exception.common.IOException exception) {

                LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                        ApplicationGenericConstants.APPLICATION_ID,
                        exception.getMessage() + ExceptionUtils.getStackTrace(exception));

                return false;
            }
            return false;
        }

    }

    private boolean hasSpace(int bytes) {

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "space check for : " + bytes +" in bytes");

        return bytes < new File("/").getFreeSpace();
    }

    private InputStream getInputStreamOf(String url) throws IOException {
        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Getting Inputstream for url : "+url);

        InputStream inputStream = null;
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setConnectTimeout(50000);
            // Space Check
            if (hasSpace(connection.getContentLength())) {
                inputStream = connection.getInputStream();
            } else {
                throw new IOException("No Disk Space");
            }
        } catch (NoRouteToHostException noRouteToHostException) {
            LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                    ApplicationGenericConstants.APPLICATION_ID,
                    noRouteToHostException.getMessage() + ExceptionUtils.getStackTrace(noRouteToHostException));

            throw noRouteToHostException;
        }
        return inputStream;
    }

    private void deleteUnNecessaryJars() {

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Deletion of un-necessary jars started");

        // Bin Folder
        File bin = new File(binFolder);

        // Lib Folder
        File lib = new File(libFolder);

        // Manifest's Attributes
        Map<String, Attributes> localManifestAttributes = null;
        if (localManifest != null) {
            localManifestAttributes = localManifest.getEntries();
        }

        List<File> deletableJars = new LinkedList<>();

        if (bin.listFiles().length != 0) {

            addDeletableJars(bin.listFiles(), deletableJars, localManifestAttributes, binFolder);
        }
        if (lib.listFiles().length != 0) {
            addDeletableJars(lib.listFiles(), deletableJars, localManifestAttributes, libFolder);
        }

        if (!deletableJars.isEmpty()) {
            try {
                deleteFiles(deletableJars);
            } catch (Exception ioException) {
                LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                        ApplicationGenericConstants.APPLICATION_ID,
                        ioException.getMessage() + ExceptionUtils.getStackTrace(ioException));

            }
        }

        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Deletion of un-necessary jars completed");
    }

    private void deleteFiles(List<File> deletableJars) throws af.gov.anar.lang.infrastructure.exception.common.IOException, af.gov.anar.lang.infrastructure.exception.common.IOException {
        for (File jar : deletableJars) {


            LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
                    ApplicationGenericConstants.APPLICATION_ID, "Deleting jar : "+jar.getName());
            // Delete Jar
            FileUtility.forceDelete(jar);
        }

    }

    private void addDeletableJars(File[] jarFiles, List<File> deletableJars,
                                  Map<String, Attributes> localManifestAttributes, String folder) {
        for (File jar : jarFiles) {

            if (!(jar.getName().contains("run") && folder.equals(binFolder))
                    && ((jar.getName().contains(anar) && folder.equals(libFolder))
                    || (!jar.getName().contains(anar)) && folder.equals(binFolder)
                    || localManifestAttributes == null
                    || !localManifestAttributes.containsKey(jar.getName()))) {
                if(!jar.getName().contains("identy"))
                    deletableJars.add(jar);

            }
        }
    }

}

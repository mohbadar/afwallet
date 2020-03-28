package af.asr.corona.infrastructure.installation.cipher;//package af.gov.anar.ebreshna.infrastructure.installation.cipher;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.InvocationTargetException;
//import java.security.InvalidAlgorithmParameterException;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Objects;
//import java.util.Properties;
//import java.util.UUID;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.GCMParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.parsers.ParserConfigurationException;
//
//import af.gov.anar.lang.infrastructure.exception.common.ExceptionUtils;
//import af.gov.anar.lib.cryptojce.exception.InvalidDataException;
//import af.gov.anar.lib.cryptojce.exception.InvalidKeyException;import af.gov.anar.lib.cryptojce.util.CryptoUtil;
//import af.gov.anar.lib.cryptojce.util.CryptoUtils;
//import af.gov.anar.lib.cryptojce.util.SecurityExceptionCodeConstant;
//import af.gov.anar.lib.logger.Logger;
//import af.gov.anar.ebreshna.infrastructure.installation.tpm.asymmetric.AsymmetricDecryptionService;
//import af.gov.anar.ebreshna.infrastructure.installation.tpm.asymmetric.AsymmetricEncryptionService;
//import af.gov.anar.ebreshna.infrastructure.installation.tpm.initailize.TPMInitialization;
//import af.gov.anar.ebreshna.infrastructure.constant.ApplicationGenericConstants;
//import af.gov.anar.ebreshna.infrastructure.installation.SoftwareInstallationHandler;
//import af.gov.anar.ebreshna.infrastructure.util.LoggerFactory;
//import org.apache.commons.io.FileUtils;
//import org.xml.sax.SAXException;
//import javafx.application.SigtasApplication;
//import javafx.application.Platform;
//import javafx.concurrent.Task;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.Label;
//import javafx.scene.control.ProgressIndicator;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
///**
// * Decryption the Client Jar with Symmetric Key
// */
//public class ClientJarDecryption extends SigtasApplication {
//
//    private static final String SLASH = "/";
//    private static final String AES_ALGORITHM = "AES";
//    private static final String ANAR_CLIENT = "ANAR-client.jar";
//    private static final String ANAR_SERVICES = "ANAR-services.jar";
//    private static String libFolder = "lib/";
//    private static String binFolder = "bin/";
//    private static final String ANAR_REGISTRATION_DB_KEY = "ANAR.reg.db.key";
//    private static final String ANAR_REGISTRATION_HC_URL = "ANAR.reg.healthcheck.url";
//    private static final String ANAR_REGISTRATION_APP_KEY = "ANAR.reg.app.key";
//    private static final String ENCRYPTED_KEY = "ANAR.registration.key.encrypted";
//    private static final String IS_KEY_ENCRYPTED = "Y";
//    private static final String ANAR_CLIENT_TPM_AVAILABILITY = "ANAR.reg.client.tpm.availability";
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ClientJarDecryption.class);
//
//    private ProgressIndicator progressIndicator = new ProgressIndicator();
//    private Stage primaryStage = new Stage();
//
//    static String tempPath;
//    private AsymmetricEncryptionService asymmetricEncryptionService = new AsymmetricEncryptionService();
//    private AsymmetricDecryptionService asymmetricDecryptionService = new AsymmetricDecryptionService();
//
//    private String IS_TPM_AVAILABLE = "Checking TPM Avaialbility";
//    private String ENCRYPT_PROPERTIES = "Encrypting Properties";
//    private String DB_CHECK = "Checking for DB Availability";
//    private String CHECKING_FOR_JARS = "Checking for jars";
//    private String FAILED_TO_LAUNCH = "Failed To Launch";
//    private String LAUNCHING_CLIENT = "Launching ANAR-Client";
//    private String RE_CHECKING_FOR_JARS = "Re-Checking Jars";
//    private String INSTALLING_JARS = "Installing Jars";
//    private String TERMINATING_APPLICATION = "Terminating SigtasApplication";
//    private String DB_NOT_FOUND = "DB Not Found";
//
//    private Label downloadLabel;
//    private String ANAR_SCREEN_LOADED = "ANAR client Screen loaded";
//
//    private String EXCEPTION_ENCOUNTERED = "Exception encountered during context initialization";
//
//    private String UNABLE_TO_DOWNLOAD_JARS = "Unable to Download Jars Due To Slow or No Internet";
//
//    /**
//     * Decrypt the bytes
//     *
//     * @param data
//     *            bytes
//     * @throws UnsupportedEncodingException
//     */
//    public byte[] decrypt(byte[] data, byte[] encodedString) {
//
//        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                ApplicationGenericConstants.APPLICATION_ID, "Decryption Started");
//
//        // Generate AES Session Key
//        SecretKey symmetricKey = new SecretKeySpec(encodedString, AES_ALGORITHM);
//
//        return symmetricDecrypt(symmetricKey, data,null);
//    }
//
//    /**
//     * Decrypt and save the file in temp directory
//     *
//     * @param args
//     * @throws SecurityException
//     * @throws NoSuchMethodException
//     * @throws InvocationTargetException
//     * @throws IllegalArgumentException
//     * @throws IllegalAccessException
//     * @throws InterruptedException
//     * @throws IOException
//     * @throws SAXException
//     * @throws ParserConfigurationException
//     */
//    public static void main(String[] args) {
//
//        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                ApplicationGenericConstants.APPLICATION_ID, "Started run.jar");
//
//        // Launch Reg-Client and perform necessary actions
//        launch(args);
//
//    }
//
//    @Override
//    public void start(Stage stage) {
//
//        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                ApplicationGenericConstants.APPLICATION_ID, "Started JavaFx start");
//
//        showDialog();
//
//        executeVerificationTask();
//    }
//
//    private void executeVerificationTask() {
//        ClientJarDecryption aesDecrypt = new ClientJarDecryption();
//
//        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                ApplicationGenericConstants.APPLICATION_ID, "Started loading properties of ANAR-application.properties");
//
//        try (InputStream keyStream = ClientJarDecryption.class.getClassLoader()
//                .getResourceAsStream("props/ANAR-application.properties")) {
//            Properties properties = new Properties();
//            properties.load(keyStream);
//            try {
//
//                // TODO Check Internet Connectivity
//
//                Task<Boolean> verificationTask = new Task<Boolean>() {
//
//                    /*
//                     * (non-Javadoc)
//                     *
//                     * @see javafx.concurrent.Task#call()
//                     */
//                    @Override
//                    protected Boolean call() throws IOException, InterruptedException {
//
//                        String dbpath = new File(System.getProperty("user.dir")) + SLASH
//                                + properties.getProperty("ANAR.reg.dbpath");
//
//                        updateMessage(DB_CHECK);
//
//                        boolean isDbAvailable = dbCheck(dbpath);
//
//                        if (isDbAvailable) {
//
//                            updateMessage(IS_TPM_AVAILABLE);
//
//                            // // Encrypt the Keys
//                            // boolean isTPMAvailable = isTPMAvailable(properties);
//                            //
//                            // if (isTPMAvailable) {
//                            // updateMessage(ENCRYPT_PROPERTIES);
//                            //
//                            // encryptRequiredProperties(properties, propsFilePath);
//                            // }
//
//                            try {
//                                LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                                        ApplicationGenericConstants.APPLICATION_ID, "Started check for jars task");
//
//                                LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                                        ApplicationGenericConstants.APPLICATION_ID, "Checking for jars started");
//
//                                SoftwareInstallationHandler registrationUpdate = new SoftwareInstallationHandler();
//
//                                boolean hasJars = false;
//
//                                updateMessage(CHECKING_FOR_JARS);
//
//                                if (registrationUpdate.getCurrentVersion() != null
//                                        && registrationUpdate.hasRequiredJars()) {
//
//                                    hasJars = true;
//
//                                } else {
//                                    LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                                            ApplicationGenericConstants.APPLICATION_ID, "Installing of jars started");
//
//                                    updateMessage(INSTALLING_JARS);
//
//                                    registrationUpdate.installJars();
//
//                                    updateMessage(RE_CHECKING_FOR_JARS);
//
//                                    hasJars = (registrationUpdate.getCurrentVersion() != null
//                                            && registrationUpdate.hasRequiredJars());
//                                }
//
//                                LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                                        ApplicationGenericConstants.APPLICATION_ID, "Checking for jars Completed");
//
//                                if (hasJars) {
//
//                                    LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                                            ApplicationGenericConstants.APPLICATION_ID, "Found all the required jars");
//
//                                    updateMessage(LAUNCHING_CLIENT);
//                                    File encryptedClientJar = new File(binFolder + ANAR_CLIENT);
//
//                                    File encryptedServicesJar = new File(binFolder + ANAR_SERVICES);
//
//                                    tempPath = FileUtils.getTempDirectoryPath();
//                                    tempPath = tempPath + UUID.randomUUID();
//
//                                    byte[] decryptedRegFileBytes;
//                                    try {
//
//                                        byte[] decryptedKey = aesDecrypt.getValue(ANAR_REGISTRATION_APP_KEY,
//                                                properties, isTPMAvailable(properties));
//
//                                        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                "Decrypting ANAR-client");
//
//                                        decryptedRegFileBytes = aesDecrypt.decrypt(
//                                                FileUtils.readFileToByteArray(encryptedClientJar), decryptedKey);
//
//                                        String clientJar = tempPath + SLASH + UUID.randomUUID();
//
//                                        // Decrypt Client Jar
//                                        FileUtils.writeByteArrayToFile(new File(clientJar + ".jar"),
//                                                decryptedRegFileBytes);
//
//                                        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                "Decrypting ANAR-services");
//
//                                        byte[] decryptedRegServiceBytes = aesDecrypt.decrypt(
//                                                FileUtils.readFileToByteArray(encryptedServicesJar), decryptedKey);
//
//                                        // Decrypt Services ka
//                                        FileUtils.writeByteArrayToFile(
//                                                new File(tempPath + SLASH + UUID.randomUUID() + ".jar"),
//                                                decryptedRegServiceBytes);
//
//                                    } catch (RuntimeException | IOException runtimeException) {
//
//                                        updateMessage(FAILED_TO_LAUNCH);
//
//                                        LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                runtimeException.getMessage()
//                                                        + ExceptionUtils.getStackTrace(runtimeException));
//
//                                        try {
//
//                                            LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                    ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                    "Deleting manifest file, and jars and decrypted files");
//
//                                            FileUtils.deleteDirectory(new File(tempPath));
//                                            FileUtils.forceDelete(new File("MANIFEST.MF"));
//                                            new SoftwareInstallationHandler();
//                                        } catch (IOException ioException) {
//
//                                            LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                    ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                    runtimeException.getMessage()
//                                                            + ExceptionUtils.getStackTrace(runtimeException));
//
//                                        }
//
//                                        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                "Terminating the application");
//
//                                        updateMessage(TERMINATING_APPLICATION);
//
//                                        generateAlertAndTerminate(FAILED_TO_LAUNCH);
//                                    }
//
//                                    try {
//
//                                        updateMessage(LAUNCHING_CLIENT);
//
//                                        String libPath = "\"" + new File("lib").getAbsolutePath() + "\"";
//
//                                        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                "Preparing command to launch the reg-client");
//
//                                        String cmd = "java" + " -Dfile.encoding=UTF-8" + " -cp " + tempPath + "/*;"
//                                                + libPath + "/* io.ANAR.registration.controller.Initialization";
//
//                                        Process process = Runtime.getRuntime().exec(cmd);
//
//                                        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                "Proccess Initiated");
//
//                                        try (BufferedReader inputStreamReader = new BufferedReader(
//                                                new InputStreamReader(process.getInputStream()))) {
//
//                                            String info;
//                                            while ((info = inputStreamReader.readLine()) != null) {
//
//                                                LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                        ApplicationGenericConstants.APPLICATION_NAME,
//                                                        ApplicationGenericConstants.APPLICATION_ID, info);
//
//                                                if (info.contains(ANAR_SCREEN_LOADED)) {
//
//                                                    closeStage();
//                                                    break;
//                                                }
//
//                                                if (info.contains(EXCEPTION_ENCOUNTERED)) {
//
//                                                    updateMessage(TERMINATING_APPLICATION);
//
//                                                    process.destroyForcibly();
//
//                                                    generateAlertAndTerminate(FAILED_TO_LAUNCH);
//                                                }
//
//                                            }
//                                        }
//
//                                        process.getInputStream().close();
//                                        process.getOutputStream().close();
//                                        process.getErrorStream().close();
//
//                                        if (0 == process.waitFor()) {
//
//                                            LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                    ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                    "Started Destroying proccess of reg-client and force deleting the decrypted jars");
//
//                                            process.destroyForcibly();
//
//                                            FileUtils.forceDelete(new File(tempPath));
//
//                                            LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                    ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                    "Completed Destroying proccess of reg-client and force deleting the decrypted jars");
//
//                                            exit();
//                                        }
//                                    } catch (RuntimeException | InterruptedException | IOException runtimeException) {
//                                        LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION,
//                                                ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
//                                                runtimeException.getMessage()
//                                                        + ExceptionUtils.getStackTrace(runtimeException));
//
//                                        updateMessage(FAILED_TO_LAUNCH);
//
//                                        generateAlertAndTerminate(FAILED_TO_LAUNCH);
//
//                                    }
//                                } else {
//
//                                    LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                                            ApplicationGenericConstants.APPLICATION_ID,
//                                            "Not installed Fully, closing ANAR run.jar screen");
//
//                                    updateMessage(FAILED_TO_LAUNCH);
//                                    updateMessage(TERMINATING_APPLICATION);
//
//                                    generateAlertAndTerminate(UNABLE_TO_DOWNLOAD_JARS);
//
//                                }
//
//                            } catch (IOException | af.gov.anar.lang.infrastructure.exception.common.IOException exception) {
//                                LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                                        ApplicationGenericConstants.APPLICATION_ID,
//                                        exception.getMessage() + ExceptionUtils.getStackTrace(exception));
//
//                                LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                                        ApplicationGenericConstants.APPLICATION_ID,
//                                        "Not installed Fully, closing ANAR run.jar screen");
//
//                                updateMessage(FAILED_TO_LAUNCH);
//                                updateMessage(TERMINATING_APPLICATION);
//
//                                generateAlertAndTerminate(UNABLE_TO_DOWNLOAD_JARS);
//
//                            }
//
//                        } else {
//
//                            LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                                    ApplicationGenericConstants.APPLICATION_ID, "DB Not Found, Terminating SigtasApplication");
//
//                            updateMessage(DB_NOT_FOUND);
//
//                            updateMessage(TERMINATING_APPLICATION);
//
//                            generateAlertAndTerminate(DB_NOT_FOUND);
//
//                        }
//
//                        return false;
//
//                    }
//                };
//                verificationTask.messageProperty()
//                        .addListener((obs, oldMessage, newMessage) -> downloadLabel.setText(newMessage));
//
//                new Thread(verificationTask).start();
//
//            } catch (RuntimeException runtimeException) {
//                LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                        ApplicationGenericConstants.APPLICATION_ID,
//                        runtimeException.getMessage() + ExceptionUtils.getStackTrace(runtimeException));
//                closeStage();
//
//                exit();
//
//            }
//        } catch (IOException ioException) {
//            LOGGER.error(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                    ApplicationGenericConstants.APPLICATION_ID,
//                    ioException.getMessage() + ExceptionUtils.getStackTrace(ioException));
//            closeStage();
//
//            exit();
//
//        }
//    }
//
//    private boolean isTPMAvailable(Properties properties) {
//        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                ApplicationGenericConstants.APPLICATION_ID, "Started tpm availability check");
//
//        return properties.containsKey(ANAR_CLIENT_TPM_AVAILABILITY)
//                && String.valueOf(properties.get(ANAR_CLIENT_TPM_AVAILABILITY)).equalsIgnoreCase(IS_KEY_ENCRYPTED);
//    }
//
//    private void showDialog() {
//
//        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                ApplicationGenericConstants.APPLICATION_ID, "Started Loading ANAR run.jar screen");
//
//        StackPane stackPane = new StackPane();
//        VBox vBox = new VBox();
//        HBox hBox = new HBox();
//        InputStream ins = this.getClass().getResourceAsStream("/img/logo-final.png");
//        ImageView imageView = new ImageView(new Image(ins));
//        imageView.setFitHeight(50);
//        imageView.setFitWidth(50);
//        hBox.setMinSize(200, 400);
//        hBox.getChildren().add(imageView);
//        downloadLabel = new Label();
//        vBox.setAlignment(Pos.CENTER_LEFT);
//        vBox.getChildren().add(progressIndicator);
//        vBox.getChildren().add(downloadLabel);
//
//        hBox.getChildren().add(vBox);
//        hBox.setAlignment(Pos.CENTER_LEFT);
//
//        stackPane.getChildren().add(hBox);
//        Scene scene = new Scene(stackPane, 200, 150);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setScene(scene);
//
//        primaryStage.show();
//
//        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                ApplicationGenericConstants.APPLICATION_ID, "Completed Loading ANAR run.jar screen");
//
//    }
//
//    private byte[] getValue(String key, Properties properties, boolean isTPMAvailable) {
//        byte[] value = CryptoUtil.decodeBase64(properties.getProperty(key));
//        if (isTPMAvailable) {
//            value = asymmetricDecryptionService.decryptUsingTPM(TPMInitialization.getTPMInstance(), value);
//        }
//        return value;
//    }
//
//    // private void encryptRequiredProperties(Properties properties, String
//    // propertiesFilePath) throws IOException {
//    // if (!(properties.containsKey(ENCRYPTED_KEY)
//    // && properties.getProperty(ENCRYPTED_KEY).equals(IS_KEY_ENCRYPTED))) {
//    // try (OutputStream propertiesFile = new FileOutputStream(propertiesFilePath))
//    // {
//    // properties.put(ENCRYPTED_KEY, IS_KEY_ENCRYPTED);
//    // properties.put(ANAR_REGISTRATION_APP_KEY, getEncryptedValue(properties,
//    // ANAR_REGISTRATION_APP_KEY));
//    // properties.put(ANAR_REGISTRATION_DB_KEY, getEncryptedValue(properties,
//    // ANAR_REGISTRATION_DB_KEY));
//    // properties.store(propertiesFile, "Updated");
//    // propertiesFile.flush();
//    // }
//    // }
//    // }
//
//    private String getEncryptedValue(Properties properties, String key) {
//        return CryptoUtil.encodeBase64String(asymmetricEncryptionService.encryptUsingTPM(
//                TPMInitialization.getTPMInstance(), Base64.getDecoder().decode(properties.getProperty(key))));
//    }
//
//    private void closeStage() {
//
//        Platform.runLater(() -> primaryStage.close());
//
//    }
//
//    private void exit() {
//        System.exit(0);
//    }
//
//    private boolean dbCheck(String dbPath) {
//
//        LOGGER.info(ApplicationGenericConstants.CLIENT_JAR_DECRYPTION, ApplicationGenericConstants.APPLICATION_NAME,
//                ApplicationGenericConstants.APPLICATION_ID, "Started DB availability check at path : " + dbPath);
//
//        return new File(dbPath).exists();
//
//    }
//
//    private void generateAlertAndTerminate(String message) {
//        Platform.runLater(() -> {
//            Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
//            alert.showAndWait();
//
//            closeStage();
//
//            exit();
//        });
//    }
//
//
//
//    private static byte[] symmetricDecrypt(SecretKey key, byte[] data, byte[] aad) {
//        Objects.requireNonNull(key, SecurityExceptionCodeConstant.ANAR_INVALID_KEY_EXCEPTION.getErrorMessage());
//        CryptoUtils.verifyData(data);
//        byte[] output = null;
//        try {
//            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//
//            byte[] randomIV = Arrays.copyOfRange(data, data.length - cipher.getBlockSize(), data.length);
//            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), AES_ALGORITHM);
//            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, randomIV);
//            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
//            if (aad != null && aad.length != 0) {
//                cipher.updateAAD(aad);
//            }
//            output = doFinal(Arrays.copyOf(data, data.length - cipher.getBlockSize()), cipher);
//        } catch (java.security.InvalidKeyException e) {
//            throw new InvalidKeyException(SecurityExceptionCodeConstant.ANAR_INVALID_KEY_EXCEPTION.getErrorCode(),
//                    SecurityExceptionCodeConstant.ANAR_INVALID_KEY_EXCEPTION.getErrorMessage(), e);
//        } catch (InvalidAlgorithmParameterException e) {
//            throw new InvalidKeyException(
//                    SecurityExceptionCodeConstant.ANAR_INVALID_PARAM_SPEC_EXCEPTION.getErrorCode(),
//                    SecurityExceptionCodeConstant.ANAR_INVALID_PARAM_SPEC_EXCEPTION.getErrorMessage(), e);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            throw new InvalidDataException(
//                    SecurityExceptionCodeConstant.ANAR_INVALID_DATA_LENGTH_EXCEPTION.getErrorCode(),
//                    SecurityExceptionCodeConstant.ANAR_INVALID_DATA_LENGTH_EXCEPTION.getErrorMessage(), e);
//        } catch (java.security.NoSuchAlgorithmException noSuchAlgorithmException) {
//            throw new InvalidKeyException(
//                    SecurityExceptionCodeConstant.ANAR_NO_SUCH_ALGORITHM_EXCEPTION.getErrorCode(),
//                    SecurityExceptionCodeConstant.ANAR_NO_SUCH_ALGORITHM_EXCEPTION.getErrorMessage(),
//                    noSuchAlgorithmException);
//        } catch (NoSuchPaddingException noSuchPaddingException) {
//            throw new InvalidKeyException("No Such Padding Exception", "No Such Padding Exception",
//                    noSuchPaddingException);
//
//        }
//        return output;
//    }
//
//
//
//    private static byte[] doFinal(byte[] data, Cipher cipher) {
//        try {
//            return cipher.doFinal(data);
//        } catch (IllegalBlockSizeException e) {
//            throw new InvalidDataException(
//                    SecurityExceptionCodeConstant.ANAR_INVALID_DATA_SIZE_EXCEPTION.getErrorCode(), e.getMessage(), e);
//        } catch (BadPaddingException e) {
//            throw new InvalidDataException(
//                    SecurityExceptionCodeConstant.ANAR_INVALID_ENCRYPTED_DATA_CORRUPT_EXCEPTION.getErrorCode(),
//                    e.getMessage(), e);
//        }
//    }
//}
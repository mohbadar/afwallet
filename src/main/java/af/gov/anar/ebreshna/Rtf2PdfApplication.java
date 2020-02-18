package af.gov.anar.ebreshna;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Rtf2PdfApplication {

    public static void start() throws IOException, ExecutionException, InterruptedException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        InputStream in = new BufferedInputStream(new FileInputStream("/home/nsia/Desktop/NSIA/sigtas/scripts/data/example7.rtf"));

        IConverter converter = LocalConverter.builder()
//                .baseFolder(new File(System.getProperty("user.dir") + File.separator +"test"))
                .baseFolder(new File("/home/nsia/Desktop/NSIA/sigtas/scripts/data/test"))
                .workerPool(20, 25, 2, TimeUnit.SECONDS)
                .processTimeout(5, TimeUnit.SECONDS)
                .build();

        Future<Boolean> conversion = converter
                .convert(in).as(DocumentType.RTF)
                .to(bo).as(DocumentType.PDF)
                .prioritizeWith(1000) // optional
                .schedule();
        conversion.get();
        try (OutputStream outputStream = new FileOutputStream("/home/nsia/Desktop/NSIA/sigtas/scripts/data/example7.pdf")) {
            bo.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();

        }
        in.close();
        bo.close();
        System.out.print("you did it congratulations....finally");
    }
}

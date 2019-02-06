package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/",
        plugin = {"pretty",
                "html:target/TestingReport"},
        tags = {"@task, @posts"},
        glue = {"stepDefinitions"}
)
public class CucumberRunner {
    @AfterClass
    public static void saveTestingReport() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        Date date = Calendar.getInstance().getTime();
        String reportDate = dateFormat.format(date);

        String reportPathFrom = System.getProperty("user.dir") + "\\target\\TestingReport";
        String reportPathTo = System.getProperty("user.dir") + "\\target\\" + reportDate + "_TestingReport";

        File dirFrom = new File(reportPathFrom);
        File dirTo = new File(reportPathTo);

        FileUtils.copyDirectory(dirFrom, dirTo);

        File file = new File(System.getProperty("user.dir") + "\\target\\" + reportDate + "_TestingReport" + "\\report.js");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.append("});");
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

//        if (!dirTo.exists()) {
//            dirTo.mkdir();
//        }
//
//        try {
//            FileUtils.copyDirectory(dirFrom, dirTo);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

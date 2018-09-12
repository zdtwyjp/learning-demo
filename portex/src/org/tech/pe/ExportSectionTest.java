package org.tech.pe;

import com.github.katjahahn.tools.ReportCreator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ExportSectionTest {

    File file = new File("D:/soft/Wireshark-win64-2.4.3.exe");

    @Test
    public void test() throws IOException {
        String report = ReportCreator.newInstance(file).exportsReport();
        System.out.println(report);
    }
}

package org.tech.pe;

import com.github.katjahahn.tools.ReportCreator;
import org.junit.Test;

import java.io.File;

public class DebugSectionTest {
    File file = new File("D:/soft/Wireshark-win64-2.4.3.exe");

    @Test
    public void test() {
        String report = ReportCreator.newInstance(file).debugReport();
        System.out.println(report);
    }
}

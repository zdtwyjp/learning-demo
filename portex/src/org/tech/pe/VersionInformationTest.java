package org.tech.pe;

import com.github.katjahahn.parser.sections.rsrc.version.VersionInfo;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class VersionInformationTest {
    File file = new File("D:/soft/Wireshark-win64-2.4.3.exe");

    @Test
    public void test() throws IOException {
        List<VersionInfo> info = VersionInfo.parseFromResources(file);
        for(VersionInfo i : info) {
            Map<String, String> strings = i.getVersionStrings();
            for (Map.Entry<String,String> entry : strings.entrySet()) {
                System.out.print(entry.getKey() + ": ");
                System.out.println(entry.getValue());
            }
        }
    }


}

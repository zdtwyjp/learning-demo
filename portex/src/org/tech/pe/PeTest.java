package org.tech.pe;

import com.github.katjahahn.parser.PEData;
import com.github.katjahahn.parser.PELoader;
import com.github.katjahahn.parser.sections.SectionLoader;
import com.github.katjahahn.parser.sections.rsrc.Resource;
import com.github.katjahahn.parser.sections.rsrc.ResourceSection;
import com.github.katjahahn.tools.anomalies.Anomaly;
import com.github.katjahahn.tools.anomalies.PEAnomalyScanner;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PeTest {
    File file = new File("D:/soft/Wireshark-win64-2.4.3.exe");

    @Test
    public void test() throws IOException {
        PEData data = PELoader.loadPE(file);
        ResourceSection rsrc = new SectionLoader(data).loadResourceSection();
        List<Resource> resources = rsrc.getResources();
        for (Resource r : resources) {
            System.out.println(r);
        }
    }

    @Test
    public void test2() {
        PEAnomalyScanner scanner = PEAnomalyScanner.newInstance(file);
//        System.out.println(scanner.scanReport());
        List<Anomaly> anomalies = scanner.getAnomalies();
        for(Anomaly anomaly: anomalies) {
            System.out.println("Type: " + anomaly.getType());
            System.out.println("Subtype: " + anomaly.subtype());
            System.out.println("Field or structure with anomaly: " + anomaly.key());
            System.out.println(anomaly.description());
        }
    }
}

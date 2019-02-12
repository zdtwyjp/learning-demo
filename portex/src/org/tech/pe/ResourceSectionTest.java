package org.tech.pe;

import com.github.katjahahn.parser.PEData;
import com.github.katjahahn.parser.PELoader;
import com.github.katjahahn.parser.sections.SectionLoader;
import com.github.katjahahn.parser.sections.rsrc.Resource;
import com.github.katjahahn.parser.sections.rsrc.ResourceSection;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ResourceSectionTest {
    File file = new File("D:/soft/Wireshark-win64-2.4.3.exe");

    @Test
    public void test() throws IOException {
        PEData data = PELoader.loadPE(file);
        ResourceSection rsrc = new SectionLoader(data).loadResourceSection();
        List<Resource> resources = rsrc.getResources();
        for (Resource r : resources) {
            System.out.println(r.getType());
            /*Location loc = r.rawBytesLocation();
            long offset = loc.from();
            // this example only works for small resources
            assert loc.size() == (int) loc.size();
            int size = (int) loc.size();
            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                byte[] bytes = IOUtil.loadBytes(offset, size, raf);
                // print as hex string
//                System.out.println(ByteArrayUtil.byteToHex(bytes));
                // print as string (e.g. for ASCII resources)
                System.out.println(new String(bytes));
            }*/
        }
    }
}

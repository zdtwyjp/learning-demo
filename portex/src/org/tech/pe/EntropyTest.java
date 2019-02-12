package org.tech.pe;

import com.github.katjahahn.parser.PEData;
import com.github.katjahahn.parser.PELoader;
import com.github.katjahahn.tools.ShannonEntropy;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class EntropyTest {
    File file = new File("D:/soft/Wireshark-win64-2.4.3.exe");

    @Test
    public void test() throws IOException {
        PEData data = PELoader.loadPE(file);
        int nrOfSections = data.getCOFFFileHeader().getNumberOfSections();
        ShannonEntropy entropy = new ShannonEntropy(data);
        for(int i = 1; i < nrOfSections; i++) {
            double sectionEntropy = entropy.forSection(i);
            System.out.println("Entropy for Section " + i + ": " + sectionEntropy);
        }
    }
}

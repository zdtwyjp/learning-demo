package org.tech.pe;

import com.github.katjahahn.parser.PEData;
import com.github.katjahahn.parser.PELoader;
import com.github.katjahahn.parser.coffheader.COFFFileHeader;
import com.github.katjahahn.parser.coffheader.FileCharacteristic;
import com.github.katjahahn.parser.coffheader.MachineType;
import com.github.katjahahn.tools.ReportCreator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PeHeaderTest {
    File file = new File("D:/soft/Wireshark-win64-2.4.3.exe");

    @Test
    public void test() throws IOException {
        // load the PE file data
        PEData data = PELoader.loadPE(file);

        // Print report
        ReportCreator reporter = new ReportCreator(data);
        reporter.printReport();

        // Get report parts
        String anomalyReport = reporter.anomalyReport();
        String importsReport = reporter.importsReport();
    }

    @Test
    public void test2() throws IOException {
        // load the PE file data
        PEData data = PELoader.loadPE(file);

        // get various data from coff file header and print it
        COFFFileHeader coff = data.getCOFFFileHeader();
        MachineType machine = coff.getMachineType();
        Date date = coff.getTimeDate();
        int numberOfSections = coff.getNumberOfSections();
        int optionalHeaderSize = coff.getSizeOfOptionalHeader();

        System.out.println("machine type: " + machine.getDescription());
        System.out.println("number of sections: " + numberOfSections);
        System.out.println("size of optional header: " + optionalHeaderSize);
        System.out.println("time date stamp: " + date);

        List<FileCharacteristic> characteristics = coff.getCharacteristics();
        System.out.println("characteristics: ");
        for(FileCharacteristic characteristic : characteristics) {
            System.out.println("\t* " + characteristic.getDescription());
        }
    }

}

package org.tech.pe;

import com.github.katjahahn.parser.PEData;
import com.github.katjahahn.parser.PELoader;
import com.github.katjahahn.parser.sections.SectionLoader;
import com.github.katjahahn.parser.sections.idata.ImportDLL;
import com.github.katjahahn.parser.sections.idata.ImportSection;
import com.github.katjahahn.parser.sections.idata.NameImport;
import com.github.katjahahn.parser.sections.idata.OrdinalImport;
import com.github.katjahahn.tools.ReportCreator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImportSectionTest {
    File file = new File("D:/soft/Wireshark-win64-2.4.3.exe");

    @Test
    public void test() throws IOException {
        PEData data = PELoader.loadPE(file);
        ReportCreator reporter = new ReportCreator(data);
//        System.out.println(reporter.importsReport());

        SectionLoader loader = new SectionLoader(data);
        ImportSection idata = loader.loadImportSection();
        List<ImportDLL> imports = idata.getImports();
        for(ImportDLL dll : imports) {
            System.out.println("Imports from " + dll.getName());
            for(NameImport nameImport : dll.getNameImports()) {
                System.out.print("Name: " + nameImport.getName());
                System.out.print(" Hint: " + nameImport.getHint());
                System.out.println(" RVA: " + nameImport.getRVA());
            }
            for(OrdinalImport ordImport : dll.getOrdinalImports()) {
                System.out.println("Ordinal: " + ordImport.getOrdinal());
            }
        }
    }
}

package org.tech.pe;

import com.github.katjahahn.tools.sigscanner.Jar2ExeScanner;
import com.github.katjahahn.tools.sigscanner.MatchedSignature;
import com.github.katjahahn.tools.sigscanner.SignatureScanner;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SignatureScanningTest {

    File file = new File("D:/soft/wrar550.exe");

    @Test
    public void test() throws IOException {
        SignatureScanner scanner = SignatureScanner.newInstance();
        boolean epOnly = false;
        List<String> results = scanner.scanAll(file, epOnly);
        for(String signature : results) {
            System.out.println(signature);
        }
    }

    @Test
    public void test2() {
        Jar2ExeScanner scanner = new Jar2ExeScanner(file);
        System.out.println(scanner.createReport());
        List<Long> addresses = scanner.getZipAddresses();
        int i = 0;
        for (Long address : addresses) {
            i++;
            scanner.dumpAt(address, new File("dumped" + i + ".jar"));
        }
        Jar2ExeScanner j2eScanner = new Jar2ExeScanner(file);
        List<MatchedSignature> result = j2eScanner.scan();
        for (MatchedSignature sig : result) {
            System.out.println("name: " + sig.getName());
            System.out.println("address: " + sig.getAddress());
            System.out.println("epOnly: " + sig.isEpOnly());
            System.out.println("signature: " + sig.getSignature());
            System.out.println();
        }

    }
}

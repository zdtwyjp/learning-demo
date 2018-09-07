package org.tech.java.process;

import org.junit.Test;

import java.io.IOException;

public class ProcessTest {
    @Test
    public void test() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        String np = "Not Present";
        Process p = pb.start();
        ProcessHandle.Info info = p.info();
        System.out.println("Process ID:"+p.pid());
        System.out.println("Command name:"+info.command().orElse(np));
        System.out.println("Command line:"+info.commandLine().orElse(np));
    }

}

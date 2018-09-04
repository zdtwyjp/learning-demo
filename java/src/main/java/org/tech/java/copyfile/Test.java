package org.tech.java.copyfile;

import java.io.File;
import java.io.IOException;

public class Test {
    private static File srcFile = new File("D:/soft/Google Chrome.zip");
    private static File dstFile = new File("D:/soft/Google Chrome_1.zip");

    public void delete() {
        if (dstFile.exists()) {
            dstFile.delete();
        }
    }

    @org.junit.Test
    public void test1() {
        try {
            delete();
            FileCopyUtil.copyFileByFileChannelNonDirectBuffer(srcFile, dstFile);
            delete();
            FileCopyUtil.copyFileByFileChannelDirectBuffer(srcFile, dstFile);
            delete();
            FileCopyUtil.copyFileByFileChannelTransferTo(srcFile, dstFile);
            delete();
            FileCopyUtil.copyFileByFileChannelTransferFrom(srcFile, dstFile);
            delete();
            FileCopyUtil.copyFileByFileChannelMap(srcFile, dstFile);
            delete();
            FileCopyUtil.copyFileByBufferedStream(srcFile, dstFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test2() {
        try {
            delete();
            FileCopyUtil.copyFileByFileChannelMap(srcFile, dstFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

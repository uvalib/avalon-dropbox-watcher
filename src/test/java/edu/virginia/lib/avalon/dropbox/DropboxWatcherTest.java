package edu.virginia.lib.avalon.dropbox;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

public class DropboxWatcherTest {

    @Ignore // This test can't involve waiting for a minute
    @Test
    public void testFileCheck() throws IOException, InterruptedException {
        File watch = new File(File.createTempFile("test", "test").getParent(), "src");
        File pres = new File(watch.getParent(), "dest");
        File temp = new File(watch.getParent(), "tmp");
        watch.mkdirs();
        pres.mkdirs();
        temp.mkdirs();
        
        DropboxWatcher dw = new DropboxWatcher(watch, pres, temp);
        Thread.sleep(1000);
        final String testData = "test data";
        FileUtils.write(new File(watch, "file.mp4"), testData);
        FileUtils.write(new File(watch, "file.mp4.md5"), DigestUtils.md5Hex(testData));
        Thread.sleep(60000);
        dw.shutdown();
    }
    
}

package io.github.mxudong.scanner.asmu;

import io.github.mxudong.scanner.Path;
import io.github.mxudong.scanner.fs.DefaultCommonDirectory;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void testFile1() throws URISyntaxException {
        Path path = new Path(this.getClass().getResource("/").toURI().getPath());
        DefaultCommonDirectory defaultCommonDirectory = new DefaultCommonDirectory(path);

        defaultCommonDirectory.doDeepIterator(null,
                System.out::println);

        System.out.println(defaultCommonDirectory.checkAllFiles((af)->true));
    }

}
package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class DefaultCommonDirectoryTest {

    @Test
    public void test1() throws URISyntaxException {
        Path path = new Path(DefaultCommonDirectoryTest.class.getResource("/").toURI().getPath());
        DefaultCommonDirectory defaultCommonDirectory = new DefaultCommonDirectory(path);
        System.out.println(defaultCommonDirectory);
    }

}
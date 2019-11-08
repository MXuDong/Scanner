package io.github.mxudong.scanner;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class PathTest {

    @Test
    public void test1() throws URISyntaxException {
        Path path = new Path(PathTest.class.getResource(".").toURI().getPath());
        File file = path.generatorFile();
        System.out.println(file.exists());
    }
}
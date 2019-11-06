package io.github.mxudong.scanner.parser;

import io.github.mxudong.scanner.Path;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ClassPathParserTest {

    @Test
    public void testToPathWithNull() {
        ClassPathParser classPathParser = new ClassPathParser(null);
        assert classPathParser.toPath() == null;
    }

    @Test
    public void testToPathWithNormalClass() {
        ClassPathParser classPathParser = new ClassPathParser(ClassPathParserTest.class);
        Path path = classPathParser.toPath();
        assert path != null;
        System.out.println(path);
    }

    @Test
    public void testVirtualPath() {
        System.out.println(Path.virtualPath("f://test#1", "test"));
        System.out.println(Path.virtualPath("f://test##1"));
        System.out.println(Path.virtualPath(null));
        System.out.println(Path.virtualPath(""));
    }

    @Test
    public void testrecoverPath() {
        System.out.println(Arrays.toString(Path.recoverPath(Path.virtualPath("f://test#1", "test"))));
        System.out.println(Arrays.toString(Path.recoverPath(Path.virtualPath("f://test##1"))));
        System.out.println(Arrays.toString(Path.recoverPath(Path.virtualPath(null))));
        System.out.println(Arrays.toString(Path.recoverPath(Path.virtualPath(""))));

    }
}
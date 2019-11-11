package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;
import io.github.mxudong.scanner.asmu.Utils;
import org.junit.Test;

public class DefaultJarFileTest {

    /**
     * filter target jar file, and get check annotation path
     */
    @Test
    public void test1() {
        Path path = new Path("F:\\Graduation Project\\codes\\Scanner\\out\\artifacts\\scanner_jar\\scanner.jar");
        DefaultJarFile defaultJarFile = new DefaultJarFile(path);

        defaultJarFile.doFileDirectoryIterator((fi) -> {

            if (Utils.checkHasAnnotation(FunctionalInterface.class, fi.getCurrentFilePath())) {
                System.out.println(fi.getCurrentFilePath());
            }
        });
    }

}
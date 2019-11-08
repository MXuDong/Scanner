package io.github.mxudong.scanner.parser;

import io.github.mxudong.scanner.Path;

import java.net.URISyntaxException;

/**
 * Receive type of class, and get class path in system. If target class
 * is in the jar file, it will set the path to jar file and inner path
 * of target class.
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */

public class ClassPathParser extends AbstractPathParser {

    private Class targetClass;

    /**
     * construction of ClassPathParser, receive the Class, and get the
     * path info from class.
     *
     * @param klass target class
     */
    public ClassPathParser(Class klass) {
        this.targetClass = klass;
    }

    @Override
    public Path toPath() {

        if (targetClass != null) {
            String basePath = targetClass.getResource(".").getPath();
            Path res;
            if (basePath == null) {

                //in the jar file
                try {
                    String filePath = targetClass.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

                    res = new Path(filePath, targetClass.getName());
                } catch (URISyntaxException e) {
                    res = Path.generatorNullPath();
                }
            } else {

                //in the file system
                try {
                    String filePath = targetClass.getResource("/").toURI().getPath();
                    String className = targetClass.getName().replaceAll("\\.", "/");

                    res = new Path(filePath + className + ".class");
                } catch (URISyntaxException e) {
                    res = Path.generatorNullPath();
                }
            }

            return res;
        }
        return Path.generatorNullPath();
    }
}

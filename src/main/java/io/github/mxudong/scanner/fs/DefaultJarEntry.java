package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;

/**
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */

public class DefaultJarEntry extends AbstractFile {
    public DefaultJarEntry(Path path) {
        super(path);
    }

    public DefaultJarEntry(String jarFilePath, String className) {
        this(new Path(jarFilePath, className));
    }
}

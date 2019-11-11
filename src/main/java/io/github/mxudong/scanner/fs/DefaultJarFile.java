package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * All the file in the jar file, will use as JarEntry.( if target file is
 * directory will use as JarEntry too).
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */

public class DefaultJarFile extends AbstractDirectory {
    /**
     * The construction.
     *
     * @param targetPath target path
     */
    public DefaultJarFile(Path targetPath) {
        super(targetPath);
    }

    @Override
    void build() {
        try {
            JarFile jarFile = new JarFile(super.currentFilePath.generatorFile());
            String jarFilePath = currentFilePath.generatorFile().getPath();
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String className = jarEntry.getName();

                sonFiles.add(new DefaultJarEntry(jarFilePath, className));
            }
        } catch (IOException e) {
            sonDirectories.clear();
            sonFiles.clear();
        }
    }
}

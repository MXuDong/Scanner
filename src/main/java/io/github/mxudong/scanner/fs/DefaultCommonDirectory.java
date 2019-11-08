package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;

import java.io.File;

/**
 * The default directory of file system.
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */

public class DefaultCommonDirectory extends AbstractDirectory {
    /**
     * The construction.
     *
     * @param targetPath target path
     */
    public DefaultCommonDirectory(Path targetPath) {
        super(targetPath);
    }

    /**
     * The file if is directory will use DefaultCommonDirectory.
     * The file if is class file will use DefaultClassFile.
     * The file if is Jar file will use DefaultJarFile.
     * Other file will discard
     */
    @Override
    void build() {
        File targetFile = currentFilePath.generatorFile();
        if (targetFile.exists() && targetFile.isDirectory()) {
            File[] sonFiles = targetFile.listFiles();
            if (sonFiles == null) {
                return;
            }
            for (File f : sonFiles) {
                Path filePath = Path.generatorFIlePath(f);
                if (f.isDirectory()) {
                    sonDirectories.add(new DefaultCommonDirectory(filePath));
                } else if (f.isFile()) {
                    if (f.getName().endsWith(Path.CLASS_FILE_POSTFIX)) {
                        super.sonFiles.add(new DefaultClassFile(filePath));
                    }else if(f.getName().endsWith(Path.JAR_FILE_POSTFIX)){
                        super.sonDirectories.add(new DefaultJarFile(filePath));
                    }
                }
            }
        }
    }
}

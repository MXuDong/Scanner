package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;

import java.io.File;

/**
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */

public class DefaultCommonDirectory extends AbstractDirectory {
    /**
     * The construction, although it is public, it should by Factory,
     *
     * @param targetPath target path
     */
    public DefaultCommonDirectory(Path targetPath) {
        super(targetPath);
    }

    @Override
    void build() {
        File targetFile = currentFilePath.generatorFile();
        if(targetFile.exists() && targetFile.isDirectory()){
            File[] sonFiles = targetFile.listFiles();
            for(File f : sonFiles){
                if(f.isDirectory()){
                    sonDirectories.add(new DefaultCommonDirectory(Path.generatorFIlePath(f)));
                }
            }
        }
    }
}

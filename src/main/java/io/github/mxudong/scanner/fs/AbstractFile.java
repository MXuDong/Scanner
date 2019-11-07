package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;

import java.io.File;

/**The supper class of FileObject(not directory). All the implements can
 * read info for invoker.
 *
 * This class packing the file path for {@code Path(io.github.mxudong.scanner.Path}.
 *
 * @author Dong
 * @since 1.0.0
 * @version 1.0.0
 */
public abstract class AbstractFile {

    File currentFile;

    Path currentFilePath;

    public AbstractFile(Path path){
        this.currentFilePath = path;
        currentFile = currentFilePath.generatorFile();
    }
}

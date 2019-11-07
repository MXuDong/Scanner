package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;

import java.io.File;

/**
 * The supper class of FileObject(not directory). All the implements can
 * read info for invoker.
 * <p>
 * This class packing the file path for {@code Path(io.github.mxudong.scanner.Path}.
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractFile {

    private File currentFile;

    private Path currentFilePath;

    public AbstractFile(Path path) {
        this.currentFilePath = path;
        currentFile = currentFilePath.generatorFile();
    }

    /**
     * getter of currentFile
     *
     * @return file
     */
    public File getCurrentFile() {
        return currentFile;
    }

    /**
     * getter of currentFilePath
     *
     * @return path of file
     */
    public Path getCurrentFilePath() {
        return currentFilePath;
    }
}

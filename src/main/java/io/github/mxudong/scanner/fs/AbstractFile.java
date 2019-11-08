package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;

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

    private Path currentFilePath;

    public AbstractFile(Path path) {
        this.currentFilePath = path;
    }

    /**
     * getter of currentFilePath
     *
     * @return path of file
     */
    public Path getCurrentFilePath() {
        return currentFilePath;
    }

    @Override
    public String toString() {
        return "AbstractFile{" +
                "currentFilePath=" + currentFilePath +
                '}';
    }
}

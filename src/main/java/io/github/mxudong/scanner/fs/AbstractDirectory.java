package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;

import java.util.List;

/**
 * The supper class of DirectoryObject, this class cover many AbstractFile
 * and has other AbstractDirectory. The relationship between AbstractDirectory
 * and AbstractFil are not like directory and file. Just like JarFile, it is a file
 * and also has inner file and the inner file can be read.
 * <p>
 * All the file path is virtual of {@code Path(io.github.mxudong.scanner.Path}.
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractDirectory {

    List<AbstractDirectory> sonDirectories;
    List<AbstractFile> sonFiles;

    Path currentFilePath;

    /**
     * The construction, although it is public, it should by Factory,
     * @param targetPath
     */
    public AbstractDirectory(Path targetPath) {
        this.currentFilePath = targetPath;
    }

    /**
     * refresh current file info about son directories and files. For some operation
     * may change info about that. Like user create new file operation will change,
     * but the program can not get any info about change.
     * <p>
     * For different type of directory, the refresh may different. For every sub class
     * must implement of this method.
     */
    abstract void reFresh();

    /**
     * Do iterator of directory in this AbstractDirectory
     *
     * @param diFun the function of director iterator
     */
    public void doDirectoryIterator(DirectoryIteratorFun diFun) {
        if (sonDirectories != null) {
            for (AbstractDirectory ad : sonDirectories) {
                diFun.iterator(ad);
            }
        }
    }

    /**
     * Do iterator of file in this AbstractDirectory
     *
     * @param fiFun the function of file iterator
     */
    public void doFileDirectoryIterator(FileItoratorFun fiFun) {
        if (sonFiles != null) {
            for (AbstractFile af : sonFiles) {
                fiFun.iterator(af);
            }
        }
    }

    /**
     * get the target file path
     *
     * @return target file path
     */
    public Path getPath() {
        return currentFilePath;
    }

    /**
     * get of son directories
     *
     * @return sonDirectories
     */
    public List<AbstractDirectory> getSonDirectories() {
        return sonDirectories;
    }

    /**
     * get of son sonFiles
     *
     * @return sonFiles
     */
    public List<AbstractFile> getSonFiles() {
        return sonFiles;
    }
}
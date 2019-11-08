package io.github.mxudong.scanner.fs;

import io.github.mxudong.scanner.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * The supper class of DirectoryObject, this class cover many AbstractFile
 * and has other AbstractDirectory. The relationship between AbstractDirectory
 * and AbstractFil are not like directory and file. Just like DefaultJarFile, it is a file
 * and also has inner file and the inner file can be read.
 * <p>
 * All the file path is virtual of {@code Path(io.github.mxudong.scanner.Path}.
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractDirectory {

    protected ArrayList<AbstractDirectory> sonDirectories;
    protected ArrayList<AbstractFile> sonFiles;

    protected Path currentFilePath;

    /**
     * The construction.
     *
     * @param targetPath target path
     */
    public AbstractDirectory(Path targetPath) {
        this.currentFilePath = targetPath;
        sonDirectories = new ArrayList<>();
        sonFiles = new ArrayList<>();
        build();
    }

    /**
     * refresh current file info about son directories and files. For some operation
     * may change info about that. Like user create new file operation will change,
     * but the program can not get any info about change.
     * <p>
     * This method cost many resource, the sub class of this should override this
     * method.
     * <p>
     * Default method will rebuild by method {@code build()}, and set the value empty.
     */
    void reFresh() {
        //reset list of values
        sonDirectories = new ArrayList<>();
        sonFiles = new ArrayList<>();
        build();
    }

    /**
     * build the values from target path, for different directory has different behavior.
     * So the sub class must implement this method.
     */
    abstract void build();

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
     * Do iterator in all file and directory in this directory
     *
     * @param diFun the function of directory iterator
     * @param fiFun the function of file iterator
     */
    public void doDeepIterator(DirectoryIteratorFun diFun, FileItoratorFun fiFun) {
        doFileDirectoryIterator(fiFun);

        doDirectoryIterator((ad) -> {
            ad.doFileDirectoryIterator(fiFun);
            ad.doDirectoryIterator(diFun);
        });
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

    @Override
    public String toString() {
        return "AbstractDirectory{" +
                "sonDirectories=" + sonDirectories +
                ", sonFiles=" + sonFiles +
                ", currentFilePath=" + currentFilePath +
                '}';
    }
}
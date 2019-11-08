package io.github.mxudong.scanner.fs;

/**
 * @author Dong
 * @version 1.0.0
 * @see AbstractDirectory#doFileDirectoryIterator(FileItoratorFun)
 * @since 1.0.0
 */
@FunctionalInterface
public interface FileItoratorFun {

    void iterator(AbstractFile abstractFile);
}

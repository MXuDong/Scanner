package io.github.mxudong.scanner.fs;

/**
 * @author Dong
 * @since 1.0.0
 * @version 1.0.0
 * @see AbstractDirectory#doFileDirectoryIterator(FileItoratorFun)
 */
@FunctionalInterface
public interface FileItoratorFun {

    void iterator(AbstractFile abstractFile);
}

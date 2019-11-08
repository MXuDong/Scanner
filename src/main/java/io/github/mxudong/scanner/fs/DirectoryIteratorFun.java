package io.github.mxudong.scanner.fs;

/**
 * Do iterator of AbstractDirectory.
 *
 * @author Dong
 * @see AbstractDirectory#doDirectoryIterator(DirectoryIteratorFun)
 * @since 1.0.0
 */
@FunctionalInterface
public interface DirectoryIteratorFun {
    void iterator(AbstractDirectory abstractDirectory);
}

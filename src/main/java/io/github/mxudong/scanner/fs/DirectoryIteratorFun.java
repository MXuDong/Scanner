package io.github.mxudong.scanner.fs;

/**
 * Do iterator of AbstractDirectory.
 *
 * @author Dong
 * @since 1.0.0
 * @see AbstractDirectory#doDirectoryIterator(DirectoryIteratorFun)
 */
@FunctionalInterface
public interface DirectoryIteratorFun {
    void iterator(AbstractDirectory abstractDirectory);
}

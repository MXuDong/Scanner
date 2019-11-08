package io.github.mxudong.scanner.fs;

/**
 * This fun is a filter whether the target AbstractFile can be
 * pass, if can access should return true, else return false,.
 *
 * @author Dong
 * @version 1.0.0
 * @see AbstractDirectory#checkAllFiles(CheckFileFilter) ()
 * @since 1.0.0
 */

@FunctionalInterface
public interface CheckFileFilter {


    /**
     * whether the target AbstractFile can be access.
     *
     * @param af be judged file.
     * @return true when can be access, else return false.
     */
    boolean canAccess(AbstractFile af);
}

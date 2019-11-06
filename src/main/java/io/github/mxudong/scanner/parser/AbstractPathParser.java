package io.github.mxudong.scanner.parser;

import io.github.mxudong.scanner.Path;

/**
 * Parser the path of target object. For different path parser can receive
 * different object and parser the path of target object.
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractPathParser {

    /**
     * The sub class from AbstractPathParser must implement this method, for parse
     * object path.
     *
     * @return The Path Object which packing the path info
     */
    abstract Path toPath();

    @Override
    public String toString() {
        return toPath().toString();
    }
}

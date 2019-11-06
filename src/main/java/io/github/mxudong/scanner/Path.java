package io.github.mxudong.scanner;

/**
 * Packging the target file path.
 * <p>
 * If target file is class file, it will set {@code filePath} to target file
 * path, and {@code innerPath} will be set null.
 * <p>
 * If target file is in the jar file, the {@code filePath} will be set to path
 * of jar file, and the {@code innerPath} will be set to TargetClass' package
 * name.
 * <p>
 * For different type of path, this class provide some method to convert other
 * type of path. The path type is contain common Class file and Class in JarFile class.
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */

public class Path {

    private String filePath;

    private String innerPath;

    public Path(String filePath) {

    }

    public Path(String jarFile, String packageName) {

    }

    /**
     * Determine whether the file is in a Jar File.
     *
     * @return If target file in the jar, it will return true. Else return false.
     */
    public boolean isInJar() {
        return filePath != null && (filePath.endsWith(".jar") && innerPath != null);
    }

    /**
     * Determin whether the file is a jar file.
     *
     * @return if target file is jar, it will return true.
     */
    public boolean isJar() {
        return filePath != null && (filePath.endsWith(".jar") && innerPath == null);
    }

    /**
     * Determine whether the file is class file.
     * <p>
     * Note that if target file in the jar file, it will return false.
     *
     * @return true when file is class file.
     */
    public boolean isClassFile() {
        return filePath != null && filePath.endsWith(".class") && innerPath == null;
    }


}

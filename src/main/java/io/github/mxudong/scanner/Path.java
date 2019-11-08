package io.github.mxudong.scanner;

import java.io.File;

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
 * type of path. The path type is contain common Class file and Class in DefaultJarFile class.
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */

public class Path {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private String filePath;

    private String innerPath;

    /**
     * the construction for file.
     *
     * @param filePath target file location path
     */
    public Path(String filePath) {
        this(filePath, "");
    }


    /**
     * the construction for class in jar file
     *
     * @param jarFile     target jar file
     * @param packageName class' package name
     */
    public Path(String jarFile, String packageName) {
        this.filePath = jarFile;
        this.innerPath = packageName;
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
     * Determine whether the file is a jar file.
     *
     * @return if target file is jar, it will return true.
     */
    public boolean isJar() {
        return filePath != null && (filePath.endsWith(".jar") && innerPath == null);
    }

    /**
     * determine whether the path strong no info.
     *
     * @return true or flase
     */
    public boolean isNull() {
        return (filePath == null || filePath.length() == 0);
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

    /**
     * convert the Path's file path and inner path to virtual path.
     *
     * @return virtual path
     */
    public String convertVirtualPath() {
        return virtualPath(filePath, innerPath);
    }

    /**
     * virtual the path, replace all the {@code #} to {@code #1}.
     * <p>
     * And will use {@code #2} connect all path.
     *
     * @param paths target path
     * @return virtual path
     */
    public static String virtualPath(String... paths) {
        if (paths == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        for (String path : paths) {
            res.append(path != null ? path.replaceAll("#", "#1") : "");
            res.append("#2");
        }
        return res.substring(0, res.length() - 2);
    }


    /**
     * recover the target virtual path to actual path.
     *
     * @param virtualPath from the Path.VirtualPath
     * @return string[]
     */
    public static String[] recoverPath(String virtualPath) {
        if (virtualPath == null) {
            return new String[0];
        }

        String[] res = virtualPath.split("#2");
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i].replaceAll("#1", "#");
        }
        return res;
    }

    /**
     * create new path from virtual path. If recover paths from virtual path has no
     * path, will return null path(it mean that the {@code path.isNull == true}.
     *
     * @param virtualPath from Path.convertVirtualPath()
     * @return new Path
     */
    public static Path generatorFromVirtualPath(String virtualPath) {
        String[] paths = recoverPath(virtualPath);
        if (paths.length == 0) {
            return new Path("");
        } else if (paths.length == 1) {
            return new Path(paths[0]);
        } else {
            return new Path(paths[0], paths[1]);
        }
    }

    /**
     * generator the null path object.
     *
     * @return null path object.
     */
    public static Path generatorNullPath() {
        return new Path("");
    }

    /**
     * generator path from file.
     *
     * @param file target file, if file not exits, return null path.
     * @return file path
     */
    public static Path generatorFIlePath(File file) {
        if (!file.exists()) {
            return generatorNullPath();
        }
        String path = file.getPath();
        return new Path(path);
    }

    /**
     * generator file from file path without inner path
     *
     * @return file
     */
    public File generatorFile() {
        if(this.isNull()){
            return new File("");
        }
        return new File(filePath);
    }

    /**
     * will return virtual path
     *
     * @return virtual path
     */
    @Override
    public String toString() {
        return convertVirtualPath();
    }

    public static final String CLASS_FILE_POSTFIX = ".class";
    public static final String JAR_FILE_POSTFIX = ".jar";
}
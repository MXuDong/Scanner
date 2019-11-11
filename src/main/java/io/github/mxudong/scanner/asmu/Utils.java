package io.github.mxudong.scanner.asmu;

import io.github.mxudong.scanner.Path;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.AnnotationNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

import java.io.*;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * For ASM FrameWork, provide some method for use ASM and InputStream.
 *
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */

public class Utils {

    /**
     * is target path have the target annotation.
     *
     * @param annotation be checked annotation
     * @param targetPath be checked path
     * @return is the target path file have the target annotation.
     */
    public static boolean checkHasAnnotation(Class annotation, Path targetPath) {
        if (targetPath.isNull()) {
            return false;
        }

        InputStreamUtil isu;

        if (targetPath.isClassFile()) {
            isu = new FileInputStreamUtil(targetPath);
        } else if (targetPath.isInJarClass()) {
            isu = new JarInputStreamUtil(targetPath);
        } else {
            return false;
        }


        if (!isu.canBeRead()) {
            return false;
        }
        try {
            //获取ASM.ClassReader.
            ClassReader classReader = new ClassReader(isu.getInputStream());
            ClassNode classNode = new ClassNode();
            classReader.accept(classNode, 0);
            List<AnnotationNode> visibleAnnotations = classNode.visibleAnnotations;
            if (visibleAnnotations != null) {
                for (AnnotationNode annotationNode : visibleAnnotations) {
                    String name = annotationNode.desc.replace("/", ".");
                    name = name.substring(1, name.length() - 1);
//                    =====================处理注解相同的情况
                    if (name.equals(annotation.getName())) {
                        isu.closeInputStream();
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        isu.closeInputStream();
        return false;
    }

    /**
     * The input stream util interface, provide some method to ctrl the input stream.
     */
    public interface InputStreamUtil {

        /**
         * Whether the file can be read.
         *
         * @return if can be read return true.
         */
        boolean canBeRead();

        /**
         * close input stream in safe operation.
         */
        void closeInputStream();

        /**
         * get inner file input stream, if not exits, it will open stream
         *
         * @return input file stream
         */
        InputStream getInputStream();

        /**
         * open file input stream, if is opened, will close it first.
         *
         * @return input stream
         */
        InputStream openInputStream();
    }

    /**
     * Jar file util, about some method like convert jar file path to InputStream.
     */
    public static class JarInputStreamUtil implements InputStreamUtil {
        private JarEntry innerEntry;
        private InputStream inputStream;
        private Path targetPath;

        public JarInputStreamUtil(Path path) {
            this.targetPath = path;
            pathBuild();
        }

        private void pathBuild() {
            if (targetPath.isNull() || !targetPath.isInJar()) {
                return;
            }
            try {
                JarFile jarFile = new JarFile(targetPath.getFilePath());
                this.inputStream = jarFile.getInputStream(jarFile.getEntry(targetPath.getInnerPath()));
            } catch (IOException e) {
                inputStream = null;
            }

        }

        @Override
        public boolean canBeRead() {
            if (inputStream == null) {
                return openInputStream() != null;
            }
            return true;
        }

        @Override
        public void closeInputStream() {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignore) {
                }
                inputStream = null;
            }
        }

        @Override
        public InputStream getInputStream() {
            if (canBeRead()) {
                return inputStream;
            }
            return inputStream;
        }

        @Override
        public InputStream openInputStream() {
            pathBuild();
            return inputStream;
        }
    }

    /**
     * packing the file input stream
     */
    public static class FileInputStreamUtil implements InputStreamUtil {
        private File file;
        private InputStream fileInputStream;

        /**
         * The construction, received type : file
         *
         * @param file target file
         */
        public FileInputStreamUtil(File file) {
            if (file.exists() && file.isFile()) {
                this.file = file;
                openInputStream();
            } else {
                this.file = null;
                this.fileInputStream = null;
            }
        }

        /**
         * The construction, receive type : path
         *
         * @param path target file path
         */
        public FileInputStreamUtil(Path path) {
            this(path.generatorFile());
        }


        @Override
        public boolean canBeRead() {
            if (fileInputStream == null) {
                openInputStream();
            }

            return fileInputStream != null;
        }

        @Override
        public void closeInputStream() {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ignore) {
                } finally {
                    fileInputStream = null;
                }
            }
        }

        @Override
        public InputStream getInputStream() {
            if (fileInputStream == null) {
                return openInputStream();
            }

            return fileInputStream;
        }

        @Override
        public InputStream openInputStream() {
            closeInputStream();

            try {
                this.fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException ignore) {
                this.fileInputStream = null;
            }

            return fileInputStream;
        }

    }
}

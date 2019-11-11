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
 * ASM框架的支持。 包含很多ASM常用方法的封装。 用于检查目标类是否具有某种信息。
 * 同时还封装了一个内部的文件操作， 保证文件操作的安全性。
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

        //==================================分别处理jar文件和普通class文件
//        InputStream is = null;
//        if(!targetPath.isInJar()){
//        }

        FileInputStreamUtil fileInputStreamUtil = new FileInputStreamUtil(targetPath);
        if (!fileInputStreamUtil.canBeRead()) {
            return false;
        }
        try {
            //获取ASM.ClassReader.`
            ClassReader classReader = new ClassReader(fileInputStreamUtil.getInputStream());
            ClassNode classNode = new ClassNode();
            classReader.accept(classNode, 0);
            List<AnnotationNode> visibleAnnotations = classNode.visibleAnnotations;
            if (visibleAnnotations != null) {
                for (AnnotationNode annotationNode : visibleAnnotations) {
                    String name = annotationNode.desc.replace("/", ".");
                    name = name.substring(1, name.length() - 1);
//                    =====================处理注解相同的情况
                    if (name.equals(annotation.getName())) {
                        fileInputStreamUtil.closeInputStream();
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInputStreamUtil.closeInputStream();
        return false;
    }

    public interface InputStreamUtil {

    }

    /**
     * Jar文件输出流工具， 用来处理Jar中的Class文件。
     */
    public static class JarInputStreamUtil implements InputStreamUtil {
        private JarEntry innerEntry;
        private InputStream inputStream;

        public JarInputStreamUtil(JarFile jarFile, JarEntry jarEntry) {
            //
        }

        public JarInputStreamUtil(Path path) {
            //
        }

        private void pathBuild(Path path) {
            if (path.isNull() || !path.isInJar()) {
                return;
            }
            try {
                JarFile jarFile = new JarFile(path.getFilePath());
            } catch (IOException ignore) {
            }
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

        /**
         * Whether the file can be read.
         *
         * @return if can be read return true.
         */
        public boolean canBeRead() {
            if (fileInputStream == null) {
                openInputStream();
            }

            return fileInputStream != null;
        }

        /**
         * close inner file input stream, if is closed, will do nothing
         */
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

        /**
         * get inner file input stream, if not exits, it will open stream
         *
         * @return input file stream
         */
        public InputStream getInputStream() {
            if (fileInputStream == null) {
                return openInputStream();
            }

            return fileInputStream;
        }

        /**
         * open file input stream, if is opened, will close it first.
         *
         * @return input stream
         */
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

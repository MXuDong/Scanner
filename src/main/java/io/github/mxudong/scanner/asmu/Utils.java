package io.github.mxudong.scanner.asmu;

import io.github.mxudong.scanner.Path;

import java.io.*;

/**
 * @author Dong
 * @version 1.0.0
 * @since 1.0.0
 */

public class Utils {

    /**
     * packing the file input stream
     */
    public class FileInputStreamUtil {
        private File file;
        private FileInputStream fileInputStream;

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
            if(fileInputStream == null){
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

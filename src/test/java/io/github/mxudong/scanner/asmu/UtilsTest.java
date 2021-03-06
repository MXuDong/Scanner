package io.github.mxudong.scanner.asmu;

import io.github.mxudong.scanner.Path;
import io.github.mxudong.scanner.fs.AbstractFile;
import io.github.mxudong.scanner.fs.DefaultCommonDirectory;
import io.github.mxudong.scanner.parser.ClassPathParser;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class UtilsTest {

    @Test
    public void testFile1() throws URISyntaxException {
        Path path = new Path(this.getClass().getResource("/").toURI().getPath());
        DefaultCommonDirectory defaultCommonDirectory = new DefaultCommonDirectory(path);

        defaultCommonDirectory.doDeepIterator(null,
                System.out::println);

        AbstractFile abstractFile = defaultCommonDirectory.checkAllFiles((af) -> true).get(0);


        Utils.FileInputStreamUtil ufis = new Utils.FileInputStreamUtil(abstractFile.getCurrentFilePath());
        InputStream is = ufis.getInputStream();
        byte[] bytes = new byte[1024];
        while (true) {
            try {
                if ((is.read(bytes) == -1)) break;
                System.out.println(Arrays.toString(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ufis.closeInputStream();
    }

    @Test
    public void test2() throws URISyntaxException {
        Path path = (new ClassPathParser(UtilsTest.class)).toPath();
        DefaultCommonDirectory defaultCommonDirectory = new DefaultCommonDirectory(path);
        List<AbstractFile> abstractFiles = defaultCommonDirectory.checkAllFiles((af) -> true);

        for(AbstractFile af : abstractFiles){
            if(Utils.checkHasAnnotation(Deprecated.class, af.getCurrentFilePath())){
                System.out.println(af);
            }
        }

    }

}
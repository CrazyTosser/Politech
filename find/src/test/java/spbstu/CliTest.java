package spbstu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class CliTest {
    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();
    private String path = null;

    @Before
    public void setUp() throws IOException {
        File fl = tmp.newFile("test");
        tmp.newFile("test2");
        tmp.newFolder("exmp");
        tmp.newFile("exmp" + File.separator + "test");
        tmp.newFile("exmp" + File.separator + "test2");
        path = fl.getAbsolutePath();
        path = path.substring(0, path.lastIndexOf(File.separator));
        System.setProperty("gen", path);
    }

    @Test
    public void term() {
        Assert.assertEquals(Arrays.asList("." + File.separator + "test", "." + File.separator + "test2"),
                new Cli(Arrays.asList("-d", path + File.separator + "exmp", "")).getRes());
    }

    @Test
    public void termRec() {
        Assert.assertEquals(Arrays.asList("." + File.separator + "test", "." + File.separator + "exmp", "." + File.separator + "exmp" + File.separator + "test",
                "." + File.separator + "exmp" + File.separator + "test2", "." + File.separator + "test2"),
                new Cli(Arrays.asList("-d", path, "-r", "")).getRes());
    }

    @Test
    public void termDef() {
        Assert.assertEquals(Arrays.asList("." + File.separator + "test", "." + File.separator + "exmp",
                "." + File.separator + "test2"),
                new Cli(Collections.singletonList("")).getRes());
    }
}

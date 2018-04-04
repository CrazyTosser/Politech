package spbstu;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class ProgramTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();
    private String path;

    @Before
    public void setUp() throws IOException {
        File fl = tmp.newFile("test");
        tmp.newFile("test2");
        tmp.newFolder("exmp");
        tmp.newFile("exmp" + File.separator + "test");
        tmp.newFile("exmp" + File.separator + "test2");
        path = fl.getAbsolutePath();
        path = path.substring(0, path.lastIndexOf(File.separator));
        System.setOut(new PrintStream(output, true));
        System.setProperty("gen", path);
    }

    @Test
    public void term() {
        ProgKt.main(new String[]{"-d", path + File.separator + "exmp", ""});
        Assert.assertArrayEquals(new String[]{"./test", "./test2"},
                output.toString().split("\n"));
    }

    @Test
    public void termRec() {
        ProgKt.main(new String[]{"-d", path, "-r", "test"});
        Assert.assertArrayEquals(new String[]{"./test", "./exmp/test", "./exmp/test2", "./test2"},
                output.toString().split("\n"));
    }

    @Test
    public void termDef() {
        ProgKt.main(new String[]{""});
        Assert.assertArrayEquals(new String[]{"./test", "./exmp", "./test2"},
                output.toString().split("\n"));
    }

    @After
    public void cleanUp() {
        System.setOut(stdout);
    }
}

package spbstu

import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.io.IOException
import java.util.*

class CliTest {
    @Rule
    @JvmField
    var tmp = TemporaryFolder()
    private var path: String? = null

    @Before
    @Throws(IOException::class)
    fun setUp() {
        val fl = tmp.newFile("test")
        tmp.newFile("test2")
        tmp.newFolder("exmp")
        tmp.newFile("exmp" + File.separator + "test")
        tmp.newFile("exmp" + File.separator + "test2")
        path = fl.absolutePath
        path = path!!.substring(0, path!!.lastIndexOf(File.separator))
        System.setProperty("gen", path!!)
    }

    @Test
    fun term() {
        Assert.assertEquals(setOf("." + File.separator + "test", "." + File.separator + "test2"),
                Cli(Arrays.asList("-d", path + File.separator + "exmp", "")).res.toSet())
    }

    @Test
    fun termRec() {
        val expected = setOf("." + File.separator + "test", "." + File.separator + "exmp" + File.separator + "test",
                "." + File.separator + "exmp" + File.separator + "test2", "." + File.separator + "test2")
        Assert.assertEquals(expected,
                Cli(Arrays.asList<String>("-d", path, "-r", "t")).res.toSet())
    }

    @Test
    fun termDef() {
        Assert.assertEquals(setOf("." + File.separator + "test", "." + File.separator + "exmp",
                "." + File.separator + "test2"),
                Cli(listOf("")).res.toSet())
    }
}

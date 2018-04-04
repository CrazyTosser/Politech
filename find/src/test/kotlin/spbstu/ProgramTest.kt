package spbstu

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class ProgramTest {
    @Rule
    val tmp = TemporaryFolder()

    var path = ""

    @Before
    fun bfr(){
        tmp.newFile("gr")
        path = tmp.newFile("test").absolutePath.substringBeforeLast("/")
    }

    @Test
    fun exec(){
        Cli(arrayOf("-d",path,"").toList())
    }
}
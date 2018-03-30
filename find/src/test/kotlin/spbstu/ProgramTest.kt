package spbstu

import org.junit.Assert.assertEquals
import org.junit.Test

class ProgramTest {
    @Test
    fun exec() {
        //println(System.getProperty("user.dir"))
        val prg = Program(false, "tst", "txt")
        println(System.getProperty("user.dir"))
        assertEquals(prg.exec(), listOf("./test.txt"))
    }

    @Test
    fun execRec() {
        val prg = Program(true, "tst", "bin")
        assertEquals(prg.exec(), listOf("./rec/test.bin", "./rec2/test.bin"))
    }

    @Test
    fun cli() {
        val cli = Cli(mutableListOf("-d", "tst", ""))
        assertEquals(cli.parse(), listOf("./test.txt"))
    }
}
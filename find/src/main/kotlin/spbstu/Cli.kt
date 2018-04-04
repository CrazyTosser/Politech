package spbstu

import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import java.io.File

class Cli(args: Collection<String>) {
    @Option(name = "-d", usage = "Working directory")
    private var dir = System.getenv("gen")

    @Option(name = "-r", usage = "Recursive search")
    private var rec = false

    @Argument
    private var work = ""

    init {
        val parser = CmdLineParser(this)
        try{
            parser.parseArgument(args)
            this.run()
        }catch (ex: CmdLineException){
            System.err.print(ex.message)
            parser.printUsage(System.out)
        }
    }

    private fun run() {
        fun getPath(wr:String) {
            if(wr.substringAfterLast("/").contains(work))
                println("./${wr.removePrefix(dir)}".replace("//", "/"))
        }
        if(rec)
            File(dir).walkTopDown().forEach { getPath(it.absolutePath)}
        else
            File(dir).list().forEach { getPath(it) }
    }
}

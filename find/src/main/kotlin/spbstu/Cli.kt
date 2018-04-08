package spbstu

import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import java.io.File

class Cli(args: Collection<String>) {
    @Option(name = "-d", usage = "Working directory")
    private var dir = System.getProperty("gen") ?: System.getProperty("user.dir")

    @Option(name = "-r", usage = "Recursive search")
    private var rec = false

    @Argument
    private var work = ""

    var res: List<String> = emptyList()

    init {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(args)
            this.run()
        } catch (ex: CmdLineException) {
            System.err.print(ex.message)
            parser.printUsage(System.out)
        }
    }

    private fun run(): List<String> {
        this.res = mutableListOf()
        val treeWalk = File(dir).walk().maxDepth(if (rec) Int.MAX_VALUE else 1)
        treeWalk.asSequence().forEach {
            val tmp = it.relativeTo(File(dir)).toString()
            if (tmp.isNotEmpty())
                (res as MutableList<String>).add("." + File.separator + tmp)
        }
        return res
    }
}

package spbstu

import org.apache.commons.cli.*
import java.io.File

class Cli(private var args: MutableList<String>) {
    private var options = Options()
    private lateinit var program: Program
    fun parse(): List<String> {
        val prs: CommandLineParser = DefaultParser()
        try {
            val cmd: CommandLine = prs.parse(options, args.toTypedArray())
            val rec = if (cmd.hasOption("r")) {
                args.removeIf { it == "-r" }
                true
            } else {
                false
            }

            val path =
                    if (cmd.hasOption("d")) {
                        cmd.getOptionValue("d")
                        val ind = args.indexOf("-d")
                        args.removeAt(ind)
                        args.removeAt(ind)
                    } else System.getProperty("user.dir")
            val targ = args.joinToString().trim()
            val fl = File(path)
            if (!fl.isDirectory)
                throw IllegalStateException()
            program = Program(rec, path, targ)
        } catch (ex: IllegalStateException) {
            println("Недопустимый для директории поиска")
            val frm = HelpFormatter()
            frm.printHelp("Main", options)
            System.exit(0)
        }
        return program.exec()
    }

    init {
        options.addOption(Option("r", "recursive", false, "recursive search"))
        options.addOption(Option("d", "directory", true, "directory for search"))
    }

}

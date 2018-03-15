package spbstu

fun main(args: Array<String>) {
    val p: Prog
    if (args.indexOf("-d") != -1)
        p = Prog(args[args.indexOf("-d") + 1], false)
    else
        p = Prog(System.getProperty("user.dir"), false)
    p.rec = args.indexOf("-r") != -1
    p.target = args.last()
    p.work()
}

class Prog(val path: String, var rec: Boolean) {
    lateinit var target: String
    fun work() {
        println(this.path)
        println(this.rec.toString())
        println(this.target)
    }
}
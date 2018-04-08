package spbstu

fun main(args: Array<String>) {
    Cli(args.toList()).res.forEach {
        println(it)
    }
}
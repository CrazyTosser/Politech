package spbstu

fun main(args: Array<String>) {
    Cli(args.toMutableList()).parse().forEach {
        println(it)
    }
}
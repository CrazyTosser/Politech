package spbstu

import java.io.File

class Program(private val rec: Boolean, private val path: String, private val name: String) {
    fun exec(): List<String> = recFind(File(path))

    private fun recFind(fl: File): List<String> {
        val res = mutableListOf<String>()
        fl.listFiles().forEach {
            if (it.isDirectory && rec)
                res += recFind(it)
            else
                if (it.name.contains(name) && !it.isDirectory) {
                    val tmp = it.absolutePath.indexOf(path) + 1 + path.length
                    res.add(("./" + it.absolutePath.subSequence(tmp until it.absolutePath.length))
                            .replace("//", "/"))
                }
        }
        return res
    }
}
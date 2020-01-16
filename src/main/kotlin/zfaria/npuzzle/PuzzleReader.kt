package zfaria.npuzzle

import zfaria.npuzzle.heuristics.AtomicScorer
import java.io.File
import java.util.*

data class Puzzle(val size: Int, val start: Node, val end: Node)

fun getPuzzle(path: String): Puzzle? {
    val file = File(path)

    if (!file.exists()) {
        println("No such file")
        return null
    }

    val scanner = Scanner(file)

    if (!scanner.hasNext()) {
        println("File is empty.")
        return null
    }

    val size = readSize(scanner)

    if (size == null) {
        println("Bad size value")
        return null
    }

    val puzz = readPuzzle(scanner)

    if (puzz == null) {
        println("Puzzle is invalid")
        return null
    }

    var endPuzz = puzz.sortedBy { i -> i }.toMutableList()

    endPuzz.removeAt(0)
    endPuzz.add(0)

    return Puzzle(size, Node(size, puzz.filterNotNull()), Node(size, endPuzz.filterNotNull()))
}

fun readPuzzle(scanner: Scanner): MutableList<Int?>? {
    var arr: MutableList<Int?>? = mutableListOf()

    while (scanner.hasNext()) {
        var spl = trimComment(scanner.nextLine()).split(" ")
        spl = spl.filter { s -> s.isNotBlank() }
        var intvals = spl.map { s -> s.trim().toIntOrNull() }
        val isValid = intvals.foldRight(true, { i, a -> if (i == null) false else a })
        if (!isValid)
            return null
        arr!!.addAll(intvals)
    }
    scanner.close()
    return arr
}

fun readSize(scanner: Scanner): Int? {
    while (scanner.hasNext()) {
        var line = trimComment((scanner.nextLine()))

        if (line.isNotBlank()) {
            return line.toIntOrNull()
        }
    }
    return null
}

private fun trimComment(str: String): String {
    if (str.contains("#"))
        return str.substring(0, str.indexOf("#"))
    return str
}
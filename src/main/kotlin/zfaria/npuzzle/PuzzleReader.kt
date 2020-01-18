package zfaria.npuzzle

import com.google.common.math.IntMath.sqrt
import java.io.File
import java.math.RoundingMode
import java.util.*

data class Puzzle(val size: Int, val start: Node, val end: Node)

fun getPuzzle(path: String, goal: String? = null): Puzzle? {
    val puzz = readPuzzle(path)

    if (puzz == null) {
        println("Puzzle is invalid")
        return null
    }

    val size = sqrt(puzz.size, RoundingMode.DOWN)

    var endPuzz: MutableList<Int?>?

    if (goal == null) {
        endPuzz = (1 until (size * size)).toMutableList()
        endPuzz.add(0)
    } else {
        endPuzz = readPuzzle(goal)
        if (endPuzz == null) {
            println("Goal puzzle is invalid.")
            return null
        } else if (endPuzz.size != puzz.size) {
            println("Goal puzzle is of different length!")
            return null
        }
    }

    return Puzzle(size, Node(size, puzz.filterNotNull()), Node(size, endPuzz.filterNotNull()))
}

fun readPuzzle(path: String): MutableList<Int?>? {
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

/**
 * Uses a displacement count to determine if the puzzle can be solved.
 * Doesn't work with custom goals.
 */
fun canSolvePuzzle(puzzle: Puzzle): Boolean {


    return true
}

private fun countDisplacement(puzzle: Puzzle): Int {
    return 0
}
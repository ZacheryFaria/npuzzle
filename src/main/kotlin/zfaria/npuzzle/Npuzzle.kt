package zfaria.npuzzle

import kotlin.system.measureTimeMillis

fun main() {
    val puzzle = getPuzzle("puzzle.txt")

    var l = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)

    var m = mutableSetOf<Int>()

    val execution = measureTimeMillis {
        for (i in 1..181440) {

            l.shuffle()
            m.add(l.hashCode())
        }
    }

    println(execution)


    println(m.size)
}
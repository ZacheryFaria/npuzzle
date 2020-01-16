package zfaria.npuzzle

import zfaria.npuzzle.heuristics.AtomicScorer
import zfaria.npuzzle.heuristics.ManhattanScorer
import java.util.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No file given.")
        return
    }

    var puzzle = getPuzzle(args[0]) ?: return


    val scorer = ManhattanScorer(puzzle.size)

    solve(puzzle, scorer)
}
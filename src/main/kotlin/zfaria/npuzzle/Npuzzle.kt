package zfaria.npuzzle

import zfaria.npuzzle.heuristics.Manhattan

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No file given.")
        return
    }

    var puzzle = getPuzzle(args[0]) ?: return

    val scorer = Scorer.AStarScorer(Manhattan(puzzle.size))

    solve(puzzle, scorer)
}
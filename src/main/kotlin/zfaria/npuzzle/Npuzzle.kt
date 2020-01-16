package zfaria.npuzzle

import zfaria.npuzzle.heuristics.ManhattanScorer

fun main() {
    val puzzle = getPuzzle("puzzle.txt") ?: return

    val scorer = ManhattanScorer()

    solve(puzzle, scorer)
}
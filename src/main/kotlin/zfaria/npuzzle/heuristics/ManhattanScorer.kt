package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node
import kotlin.math.abs

class ManhattanScorer(puzzleSize: Int): HeuristicScorer(puzzleSize) {

    override fun score(current: Node): Int {
        var score = 0
        for (i in current.value.indices) {
            val goalX = ideal.getX(ideal.value.indexOf(i))
            val goalY = ideal.getY(ideal.value.indexOf(i))

            val actualX = current.getX(i)
            val actualY = current.getY(i)
            score += abs(goalX - actualX) + abs(goalY - actualY)
        }
        return score + current.steps
    }
}
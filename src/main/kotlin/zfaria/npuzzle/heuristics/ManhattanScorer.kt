package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node
import kotlin.math.abs

class ManhattanScorer: HeuristicScorer {
    override fun score(current: Node, ideal: Node): Int {
        var score = 0
        for (i in current.value) {
            val goalX = ideal.getX(ideal.value.indexOf(i))
            val goalY = ideal.getY(ideal.value.indexOf(i))

            val actualX = current.getX(current.value.indexOf(i))
            val actualY = current.getY(current.value.indexOf(i))

            score += abs(goalX - actualX) + abs(goalY - actualY)
        }
        return score
    }
}
package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node
import kotlin.math.abs

class Manhattan(puzzleSize: Int): Heuristic(puzzleSize) {

    override fun score(current: Node): Int {
        var score = 0
        for (i in current.value.indices) {
            if (current.value[i] != 0) {
                var n = ideal.value.indexOf(current.value[i])

                val goalX = getX(n)
                val goalY = getY(n)

                val actualX = getX(i)
                val actualY = getY(i)

                score += abs(goalX - actualX) + abs(goalY - actualY)
            }
        }
        return score
    }
}
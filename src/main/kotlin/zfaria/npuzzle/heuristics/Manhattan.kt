package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node
import kotlin.math.abs

class Manhattan(var puzzleSize: Int): Heuristic(puzzleSize) {

    override fun score(current: Node): Int {
        var score = 0
        for (i in current.value.indices) {
            val value = current.value[i]
            if (value != 0) {
                var n = ideal.value.indexOf(value)

                val goalX = n % puzzleSize
                val goalY = n / puzzleSize

                val actualX = i % puzzleSize
                val actualY = i / puzzleSize

                score += abs(goalX - actualX) + abs(goalY - actualY)
            }
        }
        return score
    }
}
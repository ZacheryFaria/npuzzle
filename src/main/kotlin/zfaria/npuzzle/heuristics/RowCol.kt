package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node

class RowCol(var puzzleSize: Int): Heuristic(puzzleSize) {
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

                if (goalX != actualX)
                    score++

                if (goalY != actualY)
                    score++
            }
        }
        return score
    }
}
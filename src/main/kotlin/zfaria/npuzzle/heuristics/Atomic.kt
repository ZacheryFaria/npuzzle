package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node

class Atomic(puzzleSize: Int): Heuristic(puzzleSize) {
    override fun score(current: Node): Int {
        var score = 0
        for (i in current.value.indices) {
            if (current.value[i] != 0) {
                if (current.value[i] != ideal.value[i])
                    score++
            }
        }
        return score
    }
}
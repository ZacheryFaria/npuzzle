package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node

class AtomicScorer(puzzleSize: Int): HeuristicScorer(puzzleSize) {
    override fun score(current: Node): Int {
        var score = 0
        for (i in ideal.value.indices) {
            if (current.value[i] != ideal.value[i])
                score++
        }
        return score + current.steps
    }
}
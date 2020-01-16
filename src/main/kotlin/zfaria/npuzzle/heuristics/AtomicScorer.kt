package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node

class AtomicScorer: HeuristicScorer {
    override fun score(current: Node, ideal: Node): Int {
        var score = 0
        for (i in ideal.value.indices) {
            if (current.value[i] != ideal.value[i])
                score++
        }
        return score + current.steps
    }
}
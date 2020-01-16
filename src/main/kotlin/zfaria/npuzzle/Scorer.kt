package zfaria.npuzzle

import zfaria.npuzzle.heuristics.Heuristic

sealed class Scorer(val heuristic: Heuristic? = null, val useSteps: Boolean = false) {
    operator fun invoke(node: Node): Int {
        var score = 0

        if (heuristic != null) {
            score += heuristic.score(node)
        }

        if (useSteps) {
            score += node.steps
        }
        return score
    }

    class AStarScorer(heuristic: Heuristic): Scorer(heuristic, true)

    class GreedyScorer(heuristic: Heuristic): Scorer(heuristic, false)

    class LinearScorer: Scorer(null, true)
}
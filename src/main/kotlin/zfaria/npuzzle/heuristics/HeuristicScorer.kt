package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node

interface HeuristicScorer {

    /**
     * Returns a score of how far off the current node is from the ideal.
     * A higher score means a worse performer.
     * Score should include current.steps
     */
    fun score(current: Node, ideal: Node): Int

}
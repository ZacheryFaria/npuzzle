package zfaria.npuzzle.heuristics

import zfaria.npuzzle.Node

abstract class Heuristic(private var puzzleSize: Int) {

    var ideal: Node

    init {
        var values = (0 until (puzzleSize * puzzleSize)).toMutableList()
        values.removeAt(0)
        values.add(0)
        ideal = Node(puzzleSize, values)
    }

    /**
     * Returns a score of how far off the current node is from the ideal.
     * A higher score means a worse performer.
     * Score should include current.steps
     */
    abstract fun score(current: Node): Int

    fun getX(n: Int): Int = n % puzzleSize

    fun getY(n: Int): Int = n / puzzleSize

}
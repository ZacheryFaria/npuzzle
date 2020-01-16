package zfaria.npuzzle

import zfaria.npuzzle.heuristics.HeuristicScorer
import java.util.*

fun solve(puzzle: Puzzle, scorer: HeuristicScorer) {
    var queue = PriorityQueue<Node>(kotlin.Comparator { n1, n2 -> scorer.score(n1, puzzle.end) - scorer.score(n2, puzzle.end)})
    var set = HashSet<Node>()

    queue.add(puzzle.start)
    set.add(puzzle.start)

    while (queue.peek() != puzzle.end && queue.size != 0) {
        val expanded = queue.poll().expand()

        for (x in expanded) {
            if (!set.contains(x))
                queue.add(x)
            set.add(x)
        }
    }
    val end = queue.peek()
    if (end == null) {
        println("Not solveable!")
        return
    }
    println("Steps to solve: ${end.steps}")
    println("Opened: ${set.size - queue.size}")
    println("Closed: ${queue.size}")
    println(end.parent)
    end.printFamily()
    println(end.toBoard())
}


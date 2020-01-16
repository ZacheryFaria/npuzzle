package zfaria.npuzzle

import zfaria.npuzzle.heuristics.HeuristicScorer
import java.util.*
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun solve(puzzle: Puzzle, scorer: HeuristicScorer) {
    var queue = PriorityQueue<Node>(kotlin.Comparator { n1, n2 -> n1.score - n2.score })
    var set = HashSet<Node>()

    puzzle.start.scorer = scorer
    puzzle.start.score = scorer.score(puzzle.start)
    queue.add(puzzle.start)
    set.add(puzzle.start)

    val time = measureTimeMillis {
        while (queue.peek() != puzzle.end && queue.size != 0) {
            val expanded = queue.poll().expand()

            for (x in expanded) {
                if (!set.contains(x))
                    queue.add(x)
                set.add(x)
            }
        }
    }
    val end = queue.peek()
    if (end == null) {
        println("Not solveable!")
        return
    }
    end.printFamily()
    println(end.toBoard())
    println("Length of solution: ${end.steps}")
    println("${end.getLength()}")
    println("Number of opened states: ${set.size - queue.size}")
    println("Number of states: ${set.size}")
    println("Time to solve: ${time}ms")
}


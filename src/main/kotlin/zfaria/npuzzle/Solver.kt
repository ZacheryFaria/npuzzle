package zfaria.npuzzle

import com.google.common.math.IntMath.pow
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@ExperimentalTime
fun solve(puzzle: Puzzle, scorer: Scorer) {
    var queue = PriorityQueue<Node>(kotlin.Comparator { n1, n2 -> n1.score - n2.score })
    var set = HashSet<Node>(pow(2, 26)) // we want to rehash as rarely as possible

    puzzle.start.scorer = scorer
    puzzle.start.score = scorer(puzzle.start)
    queue.add(puzzle.start)
    set.add(puzzle.start)

    val time = measureTime {
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
    println("Number of opened states (time complexity): ${set.size - queue.size}")
    println("Number of states (size complexity): ${set.size}")
    println("Time to solve: ${time}")
}


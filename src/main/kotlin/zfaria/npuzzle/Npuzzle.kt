package zfaria.npuzzle

import net.sourceforge.argparse4j.ArgumentParsers
import net.sourceforge.argparse4j.inf.ArgumentParserException
import net.sourceforge.argparse4j.inf.Namespace
import zfaria.npuzzle.heuristics.Atomic
import zfaria.npuzzle.heuristics.Heuristic
import zfaria.npuzzle.heuristics.Manhattan
import zfaria.npuzzle.heuristics.RowCol
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(args: Array<String>) {
    var parser = ArgumentParsers.newFor("Npuzzle").build().defaultHelp(true)

    parser.addArgument("File Name").required(true)
    parser.addArgument("--heuristic", "-H").choices("Manhattan", "Atomic", "RowCol").help("The heuristic function to use.")
            .required(false)
            .setDefault("Manhattan")
    parser.addArgument("--search", "-s").choices("Astar", "Breadth", "Greedy").help("The searching algorithm to use.")
            .required(false)
            .setDefault("Astar")
    parser.addArgument("--goal", "-g").help("A custom set goal file.").required(false)

    var ns: Namespace? = try {
        parser.parseArgs(args)
    } catch (e: ArgumentParserException) {
        parser.handleError(e)
        null
    }

    if (ns == null)
        return

    val puzzle = getPuzzle(ns["File Name"], ns["goal"]) ?: return

    val heuristic: Heuristic
    run {
        val heuristicStr: String = ns["heuristic"]
        heuristic = when (heuristicStr) {
            "Manhattan" -> Manhattan(puzzle.size)
            "Atomic" -> Atomic(puzzle.size)
            "RowCol" -> RowCol(puzzle.size)
            else -> Manhattan(puzzle.size)
        }
    }

    val scorer: Scorer
    run {
        val scorerString: String = ns["search"]
        scorer = when (scorerString) {
            "Astar" -> Scorer.AStarScorer(heuristic)
            "Greedy" -> Scorer.GreedyScorer(heuristic)
            "Breadth" -> Scorer.LinearScorer()
            else -> Scorer.AStarScorer(heuristic)
        }
    }

    val goal: String? = ns["goal"]
    if (goal == null && !canSolvePuzzle(puzzle)) {
        println("Puzzle is unsolveable!")
        return
    }

    solve(puzzle, scorer)
}
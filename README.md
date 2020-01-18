# N-Puzzle Solver

## Build

```sh
mvn package
```

## Usage

```sh
java -jar npuzzle-1.0.jar -h for help
java -jar npuzzle-1.0.jar file
```

Example puzzles are in `puzzles/`

## Algorithm

### Search

* Greedy

	* Not optimal

	* Fast

	* Path based only on H (heuristic) score

	* Heuristic DFS

* Breadth-First / Uniform

	* Optimal

	* Slow

	* Entirely ignores H score

	* BFS

* Astar

	* Optimal

	* Fast

	* Path based on distance from beginning and H score

### Heuristic

* Atomic

	* Number of tiles out of place

* Manhattan

	* Manhattan Distance from goal

* RowCol
  
  * Number of tiles out of Row or out of Column

# Java AI Search Algorithms

An educational collection of graph-search and robot-state exploration examples.

## What is implemented

Breadth-first search, best-first search, A* examples, route exploration, and a robot/vacuum-world model.

## Build and run

Use JDK 23 or newer and Maven from the repository root. The Maven file is the source of truth for any lower compiler target.

~~~sh
mvn -B compile
java -cp target/classes Robot
~~~

## Scope and limitations

Other entry points include AlgoRomania and BFS. These are separate learning examples, not a benchmark suite. Graph/state assumptions and heuristic choices should be checked before reusing an algorithm.

## Verification

See [verification notes](docs/verification.md) for the exact checks performed. A compile result is not an end-to-end test result.

Local configuration, credentials, browser drivers, generated reports, IDE metadata, and personal data are excluded from new source commits. Existing source notices are retained.
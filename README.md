# Fallout Solver

A boredom-inspired take on solving the Fallout series' computer hacking puzzles.

## Wish List

Items that would be nice to implement in the future:

* Benchmarking (understanding performance of different puzzle sizes)
* Performance improvements (initial algorithm could be improved)
* Better suggestions (app doesn't currently differentiate between candidates for next guess)

## Build Instructions

```
sbt assembly
```

## Usage

```
java -jar ./target/scala-2.12/fallout-solver-assembly-0.1.jar OVERLOOKED 1 INTERFERES 2 CUCARACHAS THEREAFTER REBELLIOUS XENOPHOBES
```
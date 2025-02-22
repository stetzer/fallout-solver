# Fallout Solver

A boredom-inspired take on solving the Fallout series' computer hacking puzzles.

## Wish List

Items that would be nice to implement in the future:

* Performance improvements (initial algorithm could be improved)

## Build Instructions

```
sbt nativeLink
```

## Benchmarking

```
sbt 'Jmh/run -prof async:libPath=<path to async profiler> -prof gc .*'
```

## Usage

```
./target/scala-2.13/fallout-solver-out OVERLOOKED 1 INTERFERES 2 CUCARACHAS THEREAFTER REBELLIOUS XENOPHOBES
```

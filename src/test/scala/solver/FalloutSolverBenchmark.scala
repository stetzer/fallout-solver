package solver

import scala.collection.Set

import org.openjdk.jmh.annotations._
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
class FalloutSolverBenchmark {
  var wordMap: Map[String, Option[Int]] = _
  
  @Setup
  def setup(): Unit = {
    wordMap = Map(
      "FOND" -> None,
      "POUR" -> None,
      "LOSS" -> None,
      "GAIN" -> Some(1),
      "PASS" -> None,
      "LOAN" -> None
    )
  }

  @Benchmark
  def benchmarkSolve(): Set[String] = {
    FalloutSolver.solve(wordMap)
  }

  @Benchmark
  def benchmarkSuggestGuess(): String = {
    val results = FalloutSolver.solve(wordMap)
    FalloutSolver.suggestGuess(results)
  }
}

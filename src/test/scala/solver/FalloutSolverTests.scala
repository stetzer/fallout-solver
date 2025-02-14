package solver

import org.specs2.mutable.Specification

import scala.collection.Set

import solver.FalloutSolver

class FalloutSolverTests extends Specification {
  "The FalloutSolver" should {
    "solve this puzzle" in {
      val m = Map("FOND" -> Some(0), "POUR" -> None, "LOSS" -> None, "GAIN" -> Some(1), "PASS" -> None, "LOAN" -> None)

      FalloutSolver.solve(m) must_== Set("PASS")
    }
    "and this puzzle" in {
      val m = Map("OVERLOOKED" -> Some(1), "INTERFERES" -> Some(2), "CUCARACHAS" -> None, "THEREAFTER" -> None,
        "REBELLIOUS" -> None, "XENOPHOBES" -> None)

      FalloutSolver.solve(m) must_== Set("REBELLIOUS")
    }
    "not solve if there's no obvious solution" in {
      val m = Map("FOND" -> None, "POUR" -> None, "LOSS" -> None, "GAIN" -> Some(1), "PASS" -> None, "LOAN" -> None)

      FalloutSolver.solve(m) must_== Set("PASS", "LOAN")
    }
  }
  "String Comparator" should {
    "compare two words" in {
      FalloutSolver.numMatchingChars("FOOL", "FOOD") must_== 3
    }
    "even if they are totally different" in {
      FalloutSolver.numMatchingChars("TABLE", "CHAIR") must_== 0
    }
  }
  "Guess Suggestor" should {
    "guess most decisive word" in {
      Seq("FOOD", "FOOL") must contain(FalloutSolver.suggestGuess(Set("DUDE", "FOAM", "FOOD", "FOOL")))
    }
    "guess the most-decisive word in a tie" in {
      FalloutSolver.suggestGuess(Set("ABC", "ADE", "BCD", "BEF", "BGH")) must_== "BCD"
    }
  }
}

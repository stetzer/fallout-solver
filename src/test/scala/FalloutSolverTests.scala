import org.specs2.mutable.Specification

class FalloutSolverTests extends Specification {
  "The FalloutSolver" >> {
    "must solve this puzzle" >> {
      val m = Map("FOND" -> Some(0), "POUR" -> None, "LOSS" -> None, "GAIN" -> Some(1), "PASS" -> None, "LOAN" -> None)

      FalloutSolver.solve(m) must_== Some("PASS")
    }
    "and this puzzle" >> {
      val m = Map("OVERLOOKED" -> Some(1), "INTERFERES" -> Some(2), "CUCARACHAS" -> None, "THEREAFTER" -> None,
        "REBELLIOUS" -> None, "XENOPHOBES" -> None)

      FalloutSolver.solve(m) must_== Some("REBELLIOUS")
    }
    "and not solve if there's no obvious solution" >> {
      val m = Map("FOND" -> None, "POUR" -> None, "LOSS" -> None, "GAIN" -> Some(1), "PASS" -> None, "LOAN" -> None)

      FalloutSolver.solve(m) must_== None
    }
  }
  "String Comparator" >> {
    "must compare two words" >> {
      FalloutSolver.numMatchingChars("FOOL", "FOOD") must_== 3
    }
    "even if they are totally different" >> {
      FalloutSolver.numMatchingChars("TABLE", "CHAIR") must_== 0
    }
  }
}

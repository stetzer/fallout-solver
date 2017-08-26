import scala.collection.Map
import scala.collection.Set
import scala.util.Try

object FalloutSolver extends App {
  override def main(args: Array[String]): Unit = {
    if(args.length == 0) {
      System.err.println("Usage: FalloutSolver <word> <correct guesses> <word> <word>")
      System.exit(1)
    }

    var i = 0
    val mm = collection.mutable.HashMap.empty[String, Option[Int]]
    while(i < args.length) {
      val word = args(i)
      // Is the next arg the number of correct chars?
      val t = Try(args(i+1).toInt)

      if(t.isSuccess) {
        mm.put(word, Some(t.get))
        i += 2
      }
      else {
        mm.put(word, None)
        i += 1
      }
    }

    println("Attempting to solve...")
    println(solve(mm).getOrElse("No solution yet"))
  }

  def solve(wordMap: Map[String, Option[Int]]) : Option[String] = {
    // Collect only words that have a known number of correct characters
    val (guessed, candidates) = wordMap.partition{case (_,v) => v.isDefined}
    val (partialMatches, neverMatches) = guessed.partition{case (_,v) => v.get != 0}

    println(s"guessed: $guessed")
    println(s"candidates: $candidates")

    // Now collect the words that don't match the known correct character counts
    val nonMatchingWords = candidates.keys.filter{word =>
      !guessed.exists{case (k,v) =>
        // Include if the number of correct chars is the same for this word, and it doesn't match any if zero
        numMatchingChars(k, word) == v.get
      }
    }
    // Collect words that have at least one character in common with words that had 0 correct chars
    val cantMatches = if(neverMatches.isEmpty) Set.empty[String] else candidates.keys.filter{word =>
      neverMatches.keySet.map(numMatchingChars(word, _)).max > 0
    }
    val remaining = candidates -- (nonMatchingWords ++ cantMatches)

    println(s"nonMatchingWords: $nonMatchingWords")
    println(s"cantMatches: $cantMatches")
    println(s"remaining: $remaining")

    if(remaining.size == 1) Some(remaining.head._1) else None
  }

  def numMatchingChars(w1:String, w2:String) : Int = {
    if(w1.length != w2.length) {
      throw new Exception(s"Words have to match in length: $w1, $w2")
    }

    w1.toCharArray.zipWithIndex.map{case (c, i) =>
      if(c == w2.charAt(i)) 1 else 0
    }.sum
  }
}

package solver

import scala.collection.Map
import scala.collection.Set
import scala.util.Try

object FalloutSolver {
  def main(args: Array[String]): Unit = {
    if(args.length == 0) {
      System.err.println("Usage: FalloutSolver <word> <correct guesses> <word> <word>")
      System.exit(1)
    }

    var i = 0
    val mm = collection.mutable.HashMap.empty[String, Option[Int]]
    while(i < args.length) {
      val word = args(i).toUpperCase
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

    if(mm.map(_._1.length).toList.distinct.size > 1) {
      System.err.println("One or more specified words are of varying lengths.")
      mm.keys.foreach(System.err.println(_))
      System.exit(2)
    }

    println("Attempting to solve...")
    val results = solve(mm)
    if(results.size == 1) {
      println(s"Solution: ${results.head}")
    }
    else {
      println("No solution yet.  Try guessing one of the following:")
      println(suggestGuess(results))
    }
  }

  def solve(wordMap: Map[String, Option[Int]]) : Set[String] = {
    // Collect only words that have a known number of correct characters
    val (guessed, candidates) = wordMap.partition{case (_,v) => v.isDefined}
    val (partialMatches, neverMatches) = guessed.partition{case (_,v) => v.get != 0}

    // Collect words whose matching char count doesn't match every > 0 correct char count word
    val nonMatchingWords = if(partialMatches.isEmpty) Set.empty[String] else candidates.keys.filter{word =>
      partialMatches.count{case(k,v) => numMatchingChars(word, k) == v.get} != partialMatches.size
    }
    // Collect words that have at least one character in common with words that had 0 correct chars
    val cantMatches = if(neverMatches.isEmpty) Set.empty[String] else candidates.keys.filter{word =>
      neverMatches.keySet.map(numMatchingChars(word, _)).max > 0
    }
    val remaining = candidates.view.filterKeys((k:String) => !nonMatchingWords.toSet.contains(k) && !cantMatches.toSet.contains(k))
    
    remaining.keySet
  }

  def numMatchingChars(w1:String, w2:String) : Int = {
    if(w1.length != w2.length) {
      throw new Exception(s"Words have to match in length: $w1, $w2")
    }

    var count = 0
    var i = 0
    while (i < w1.length) {
      if (w1.charAt(i) == w2.charAt(i)) count += 1
      i += 1
    }
    count
  }

  def suggestGuess(words:Set[String]) : String = {
    val matchChars = collection.mutable.Map.empty[String, (Int, Int)]
    words.foreach { word =>
      val matches = words.toList.filter(_ != word).map(w => numMatchingChars(w, word))
      val maxMatching = matches.max
      val numAtMax = matches.count(_ == maxMatching)
      matchChars.put(word, (maxMatching, numAtMax))
    }
    // Pick the first word with the most matching characters (in same position) as other words
    matchChars.toSeq.sortBy(w => (-w._2._1, -w._2._2)).head._1
  }
}

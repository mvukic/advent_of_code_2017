package day16

import getLines
import swapAtNames
import swapAtPositions


/**
 * Instead of going to 1_000_000_000 the changes are cyclic (every 60th cycle we gen the first value)
 * so we ned to get the 1_000_000_000 % 60 cycle value of programs array which is 40
 */
fun main() {
  var programs: MutableList<Char> = ('a'..'p').toMutableList()
  val sequence = getLines("Day16.txt").first().splitToSequence(",")

  var first: String
  val cycle = 1_000_000_000 % 60 // 40
  (1..1_000).forEach { num ->
    if (num == 1) {
      first = programs.joinToString("")
      println("First: $first")
    }

    sequence.forEach {
      when {
        it.startsWith("s") -> {
          val spinSize = it.takeLast(it.length - 1).toInt()
          val lastN = programs.takeLast(spinSize).toMutableList()
          programs = (lastN + programs.dropLast(spinSize)).toMutableList()
        }
        it.startsWith("x") -> {
          // x3/4 -> Pair(3,4)
          val byPosition: Pair<Int, Int> =
            it.takeLast(it.length - 1).split("/").zipWithNext { a, b -> Pair(a.toInt(), b.toInt()) }[0]
          programs.swapAtPositions(byPosition)
        }
        it.startsWith("p") -> {
          val byName: Pair<Char, Char> =
            it.takeLast(it.length - 1).split("/")
              .zipWithNext { a, b -> Pair(a.toCharArray()[0], b.toCharArray()[0]) }[0]
          programs.swapAtNames<Char>(byName)
        }
      }
    }

    if (num == cycle) {
      println("Cycle is $num")
      println("Answer is: ${programs.joinToString("")}")
      return
    }
  }

}
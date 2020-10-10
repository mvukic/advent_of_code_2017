package day16

import getLines
import swapAtNames
import swapAtPositions

fun main() {
  var programs: MutableList<Char> = ('a'..'p').toMutableList()
  val sequence = getLines("Day16.txt").first().splitToSequence(",")

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
          it.takeLast(it.length - 1).split("/").zipWithNext { a, b -> Pair(a.toCharArray()[0], b.toCharArray()[0]) }[0]
        programs.swapAtNames<Char>(byName)
      }
    }
  }

  println(programs.joinToString(""))
}


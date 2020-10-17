package day10

import getLines

fun main() {
  val lengths = getLines("Day10.txt").first().split(",").map { it.toInt() }
  val list = (0..255).toMutableList()

  var skipSize = 0
  var startIndex = 0
  lengths
    .filter { it <= list.size }
    .forEach { length ->
      val endingIndex = startIndex + length - 1
      val indexes = (startIndex..endingIndex).map { it % list.size }.toList()
      val sublist = indexes.map { list[it] }
      val reversedSublist = sublist.reversed()
      reversedSublist.zip(indexes).forEach { (value, index) ->
        list[index] = value
      }
      startIndex = (startIndex + length + skipSize) % list.size
      skipSize++
    }
  println("First two numbers are: ${list[0]} and ${list[1]}")
  println("Result is: ${list[0] * list[1]}")

}
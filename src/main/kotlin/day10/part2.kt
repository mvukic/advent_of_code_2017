package day10

import getLines

fun main() {
  val lengths = getLines("Day10.txt").first().toCharArray().map { it.toInt() }.toMutableList()
  lengths.addAll(listOf(17, 31, 73, 47, 23))

  val list = (0..255).toMutableList()

  var skipSize = 0
  var startIndex = 0
  (1..64).forEach { _ ->
    lengths
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
  }
  val hashValues = list.windowed(16, 16).map {
    it.reduce { hash, number -> hash.xor(number) }
  }

  val hash = hashValues.joinToString("") {
    val hex = it.toString(16)
    if (hex.length == 1) "0${hex}" else hex
  }
  println("Hash: $hash")

}
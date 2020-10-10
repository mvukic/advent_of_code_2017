package day5

import getLines

fun main() {
  val commands = getLines("Day5.txt")
    .map { it.toInt() }
    .toMutableList()

  var index = 0
  var steps = 0

  while (index >= 0 && index < commands.count()) {
    // save current index
    val currentIndex = index
    val offset = commands[index]
    // move backward/forward
    index += commands[currentIndex]
    // increase value at current command
    if (offset >= 3) {
      commands[currentIndex]--
    } else {
      commands[currentIndex]++
    }
    steps++
  }
  println("Number of steps is $steps")
}
package day6

import getLines

fun main() {

  // list that represents how many blocks is in the banks
  val memoryBanks: MutableList<Int> = getLines("Day6.txt")
    .first()
    .split("\\s+".toRegex())
    .map { it.toInt() }
    .toMutableList()

  // Previous combinations of redistribution
  val combinations = mutableMapOf<String, Int>()

  // number of redistributions
  var steps = 0

  //Redistribute
  while (true) {
    // save current combination
    combinations[memoryBanks.joinToString()] = 0
    for (combination in combinations) {
      combination.setValue(combination.value + 1)
    }

    var (mostBlocksIndex, blocksBuffer) = memoryBanks.withIndex().maxByOrNull { it.value }!!

    // clear blocks from selected bank
    memoryBanks[mostBlocksIndex] = 0

    // find index from which redistribution will start
    var fillIndex = (mostBlocksIndex + 1) % memoryBanks.count()

    // redistribute blocks
    while (blocksBuffer > 0) {
      // add block
      memoryBanks[fillIndex]++

      // find index of next bank
      fillIndex = (fillIndex + 1) % memoryBanks.count()

      // decrease number of blocks
      blocksBuffer--
    }

    // Increase number of redistributions
    steps++

    // detect redistribution loop
    if (combinations.contains(memoryBanks.joinToString())) {
      println("Combination was before ${combinations[memoryBanks.joinToString()]}")
      break
    }

  }

  println("Number of redistributions is $steps")

}

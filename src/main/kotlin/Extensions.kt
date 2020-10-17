import java.io.File

fun String.asFile() = File(this)

fun <T> MutableList<T>.swapAtPositions(positions: Pair<Int, Int>) {
  val first = this[positions.first]
  val second = this[positions.second]
  this[positions.first] = second
  this[positions.second] = first
}

fun <T> MutableList<Char>.swapAtNames(names: Pair<Char, Char>) {
  val first = this.indexOf(names.first)
  val second = this.indexOf(names.second)
  this[first] = names.second
  this[second] = names.first
}

fun getLines(name: String): List<String> {
  return ClassLoader.getSystemClassLoader().getResource(name)!!.file.asFile().readLines().toList()
}
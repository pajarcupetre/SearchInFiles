package search.model

import scala.collection.immutable.HashSet
import scala.io.Source

case class FileDictionary(filename: String, words: HashSet[String])

object FileDictionary {

  def apply(filename: String): FileDictionary = {
    val lines = Source.fromFile(filename).getLines
    val words = lines.flatMap(line => line.split(Constants.SEPARATORS)).toSeq
    FileDictionary(filename, HashSet(words: _*))
  }
}

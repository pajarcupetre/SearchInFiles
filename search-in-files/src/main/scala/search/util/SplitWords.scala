package search.util

import search.model.Constants

trait SplitWords {

  def splitIntoWords(input: String): Seq[String] = {
    input.split(Constants.SEPARATORS)
  }

  def splitIntoWordsWithAbbreviations(input: String): Seq[String] = {
    val separators = Constants.SEPARATORS.filter(char => !char.equals('.'))
    val temporarySplitWords = input.split(separators)
    temporarySplitWords.flatMap(
      word => if (word.contains('.')) {
        if (word.endsWith(".") && word.filter(p => p.equals('.')).size == 1) {
          word.split('.')
        } else {
          Seq(word)
        }
      } else {
        Seq(word)
      }
    )
  }
}

object SplitWords extends SplitWords
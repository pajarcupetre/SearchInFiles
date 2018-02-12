package search.processor

import search.model._
import search.util.SplitWords

trait RankingComputer {

  def computeRankOnFile(inputStringWords: Seq[String], fileDictionary: FileDictionary): Double = {
    if (inputStringWords.size > 0 && fileDictionary.words.size > 0) {
      val wordsFound = inputStringWords.filter(word => fileDictionary.words.contains(word)).size
      if (wordsFound == inputStringWords.size) {
        100
      } else {
        val fileDictionaryWordsSize = fileDictionary.words.size
        val rankingInputWordsReportedOnWordsOnFile = wordsFound.toDouble /  fileDictionaryWordsSize * 50
        val wordsFoundRelativeToAll = wordsFound.toDouble / inputStringWords.size
        val wordsFoundRelativeAllDifferenceToNextFound = (wordsFound.toDouble + 1) / inputStringWords.size - wordsFoundRelativeToAll
        wordsFoundRelativeToAll * 100 +  wordsFoundRelativeAllDifferenceToNextFound * rankingInputWordsReportedOnWordsOnFile
      }
    } else {
      0
    }
  }

  def computeRankingOnDictionary(inputString: String, dictionary: Dictionary): Seq[SearchResultItem] = {
    val inputStringWords = SplitWords.splitIntoWords(inputString)
    val rankingsPerFile = dictionary.filesInfo.map(
      fileDictionary => SearchResultItem(fileDictionary.filename, computeRankOnFile(inputStringWords, fileDictionary))
    )
    getTopRankingFiles(rankingsPerFile, Constants.MAXRESULT)
  }

  def getTopRankingFiles(rankings: Seq[SearchResultItem], topNumber: Int): Seq[SearchResultItem] = {
    val minHeapOfRankings =  scala.collection.mutable.PriorityQueue.empty(MinOrderSearchResultItem)
    rankings.foreach(
      searchResultItem => {
        if (minHeapOfRankings.size < topNumber) {
          minHeapOfRankings.enqueue(searchResultItem)
        } else {
          val lowestRankingSearchItem = minHeapOfRankings.head
          if (MinOrderSearchResultItem.compare(lowestRankingSearchItem, searchResultItem) > 0) {
            minHeapOfRankings.dequeue()
            minHeapOfRankings.enqueue(searchResultItem)
          }
        }
      }
    )
    var result = List[SearchResultItem]()
    for (_ <- 1 to Math.min(topNumber, rankings.size)) {
      result = minHeapOfRankings.dequeue() :: result
    }
    result
  }

}

object RankingComputer extends RankingComputer

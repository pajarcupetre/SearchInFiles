package search.processor

import org.scalatest.FunSuite
import search.model.{FileDictionary, SearchResultItem}

import scala.collection.immutable.HashSet

class RankingComputerTest extends FunSuite {

  object RankingComputerForTest extends RankingComputer

  test("Compute ranking for empty set o words") {
    val expected = 0
    val actual = RankingComputerForTest.computeRankOnFile(Seq[String](), null)
    assert(expected == actual)
  }

  test("Compute ranking for a list of words that are all found") {
    val expected = 100
    val actual = RankingComputerForTest.computeRankOnFile(
      Seq[String]("ana"),
      FileDictionary("input.txt", HashSet[String]("ana", "karina"))
    )
    assert(expected == actual)
  }

  test("Compute ranking when we don't find any word") {
    val expected = 0
    val actual = RankingComputerForTest.computeRankOnFile(
      Seq[String]("anais"),
      FileDictionary("input.txt", HashSet[String]("ana", "karina"))
    )
    assert(expected == actual)
  }

  test("Compute ranking for a list of words where we can find only some of them") {
    val expected = 70.83333333333333
    val actual = RankingComputerForTest.computeRankOnFile(
      Seq[String]("ana", "apple", "joy"),
      FileDictionary("input.txt", HashSet[String]("ana", "karina", "joy", "in", "the", "world", "pear", "oranges"))
    )
    assert(expected == actual)
  }

  test("Get only first MAX ranking files") {
    val rankingList = Seq[SearchResultItem](
      SearchResultItem("file1", 100),
      SearchResultItem("file2", 10),
      SearchResultItem("file3", 20),
      SearchResultItem("file4", 60),
      SearchResultItem("file5", 70),
      SearchResultItem("file6", 77),
      SearchResultItem("file7", 80),
      SearchResultItem("file8", 99),
      SearchResultItem("file9", 90),
      SearchResultItem("file10", 21),
      SearchResultItem("file11", 31),
      SearchResultItem("file12", 33)
    )
    val expected = Seq[SearchResultItem](
      SearchResultItem("file1", 100),
      SearchResultItem("file8", 99),
      SearchResultItem("file9", 90),
      SearchResultItem("file7", 80),
      SearchResultItem("file6", 77),
      SearchResultItem("file5", 70),
      SearchResultItem("file4", 60),
      SearchResultItem("file12", 33),
      SearchResultItem("file11", 31),
      SearchResultItem("file10", 21)
    )
    val actual = RankingComputerForTest.getTopRankingFiles(rankingList, 10)
    assert(expected.equals(actual))
  }
}

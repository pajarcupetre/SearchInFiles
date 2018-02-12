package search.console

import org.scalatest.FunSuite
import search.model.{Action, Dictionary, FileDictionary, SearchResultItem}

import scala.collection.immutable.HashSet

class ConsoleSearchTest extends FunSuite {

  object ConsoleSearchForTest extends ConsoleSearch

  test("Process input on a empty String") {
    val expected = Right(Action.NewQuestion)
    val actual = ConsoleSearchForTest.processInput("", null)

    assert(expected.equals(actual))
  }

  test("Process input on quit message") {
    val expected = Right(Action.Quit)
    val actual = ConsoleSearchForTest.processInput(":quit", null)

    assert(expected.equals(actual))
  }

  test("Get a result when passing a valid string and dictionary") {
    val fileDictionary1 = FileDictionary("file1", HashSet("apple", "orange"))
    val fileDictionary2 = FileDictionary("file2", HashSet("applegate", "orange"))
    val dictionary = Dictionary(Seq(fileDictionary1, fileDictionary2))
    val input = "apple orange"
    val expected = Left(Seq(SearchResultItem("file1", 100), SearchResultItem("file2", 62.5)))
    val actual = ConsoleSearchForTest.processInput(input, dictionary)
    assert(expected.equals(actual))
  }

}

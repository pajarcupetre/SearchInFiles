package search.console

import java.io.File

import search.model.Action.Action
import search.model.{Action, Dictionary, SearchResultItem}
import search.processor.RankingComputer

import scala.io.StdIn

trait ConsoleSearch {

  val CONSOLESEARCHMESSAGE = "search> "
  val QUITMESSAGE = ":quit"
  val NOMATCH = "no matches found"

  def readNextSearchItem(): String = {
    StdIn.readLine(CONSOLESEARCHMESSAGE)
  }



  def printResult(result: Seq[SearchResultItem]): Unit = {
    if (result.nonEmpty) {
      result.foreach(println)
    } else {
      println(NOMATCH)
    }
  }

  def processInput(input: String, dictionary: Dictionary): Either[Seq[SearchResultItem], Action] = {
    input match {
      case QUITMESSAGE => Right(Action.Quit)
      case inputValue if inputValue.isEmpty() => Right(Action.NewQuestion)
      case inputValue => Left(RankingComputer.computeRankingOnDictionary(inputValue, dictionary))
    }
  }

}

object ConsoleSearch extends ConsoleSearch {

  def runConsole(inputDir: String): Unit = {
    val dictionary = Dictionary(inputDir)
    val countFiles = new File(inputDir).listFiles().size
    val startingInputText = s"$countFiles files read in directory $inputDir"
    println(startingInputText)
    while (true) {
      val input = readNextSearchItem()
      val result = processInput(input, dictionary)
      result match {
        case Right(Action.Quit) => System.exit(0)
        case Left(result) => printResult(result)
        case Right(Action.NewQuestion) =>
      }
    }

  }
}

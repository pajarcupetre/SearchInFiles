package search.model

object Action extends Enumeration {

  type Action = Value

  val Quit = Value("Quit")
  val NewQuestion = Value("NewQuestion")

}

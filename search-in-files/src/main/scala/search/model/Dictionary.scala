package search.model

import java.io.File

case class Dictionary(filesInfo: Seq[FileDictionary])

object Dictionary {

  def apply(directoryPath: String): Dictionary = {
    val directory = new File(directoryPath)
    val files = directory.listFiles()
    val filesWithDictonary = files.map(file => FileDictionary(file.getAbsolutePath))
    Dictionary(filesWithDictonary)
  }
}
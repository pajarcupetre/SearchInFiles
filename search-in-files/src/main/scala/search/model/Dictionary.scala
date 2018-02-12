package search.model

import java.io.File

case class Dictionary(filesInfo: Seq[FileDictionary])

object Dictionary {

  def apply(directoryPath: String): Dictionary = {
    val directory = new File(directoryPath)
    val files = Option(directory.listFiles())
    val filesWithDictionary = files match {
      case Some(filesList) => filesList.filter(file => file.isFile).map(file => FileDictionary(file.getAbsolutePath)).toSeq
      case None => Seq[FileDictionary]()
    }
    Dictionary(filesWithDictionary)
  }
}
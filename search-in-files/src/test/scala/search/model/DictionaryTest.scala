package search.model

import org.scalatest.FunSuite

import scala.reflect.io.File

class DictionaryTest extends FunSuite {

  test("Create a dictionary from a invalid folder") {
    val path = "/lll/www"
    val expected = Dictionary(Seq[FileDictionary]())
    val actual = Dictionary(path)
    assert(expected.equals(actual))
  }

  test("Create a dictionary from a good path") {
    val path = File(getClass.getResource("/5.txt").getPath).parent.path
    val actual = Dictionary(path)
    assert(actual.filesInfo.size.equals(5))
  }

}

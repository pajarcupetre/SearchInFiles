package search.model

import org.scalatest.FunSuite

import scala.collection.immutable.HashSet

class FileDictionaryTest extends FunSuite {

  test("Create file dictionary from a empty file") {
    val input  = getClass.getResource("/4.txt").getPath
    val expected = FileDictionary(input, HashSet[String]())
    val actual = FileDictionary(input)
    assert(expected.equals(actual))
  }

  test("Create file dictionary from a file only with separators") {
    val input  = getClass.getResource("/5.txt").getPath
    val expected = FileDictionary(input, HashSet[String]())
    val actual = FileDictionary(input)
    assert(expected.equals(actual))
  }

  test("Create a file dictionary from a file with multiple lines") {
    val input  = getClass.getResource("/3.txt").getPath
    val actual = FileDictionary(input)
    assert(input.equals(actual.filename))
    assert(actual.words.contains("ion"))

  }

}

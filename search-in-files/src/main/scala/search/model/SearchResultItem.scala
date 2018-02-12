package search.model

case class SearchResultItem(filename: String, rating: Double) {

  override def toString: String = {
    s"${filename} : ${rating}%"
  }
}

object MinOrderSearchResultItem extends Ordering[SearchResultItem] {
  def compare(searchResultItem: SearchResultItem, otherSearchResultItem:SearchResultItem) = {
    otherSearchResultItem.rating compare searchResultItem.rating
  }
}


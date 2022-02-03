package models

enum class SortMenu(val reference: Int, val itemName: String) {
    ID(0, "ID"),
    NAME(1, "Name"),
    PRICE(2, "Price"),
    ITEM_COUNT(3, "Item count")
}

package common

import models.InventoryItem
import models.MainMenu
import models.SortMenu

const val APP_VERSION = "1.0"
const val GREETINGS_MESSAGE = "Inventory manager $APP_VERSION"
const val LEAVE_MESSAGE = "Thank you for using $GREETINGS_MESSAGE"
const val ENTER_MENU_INDEX = "Enter menu index: "
const val ENTER_TO_CONTINUE = "Enter to continue"
const val ENTER_ITEM_DATA = "Enter item data f.e.: OnePlus 9, 1234.99, 170: "
const val WHAT_TO_SELL = "Enter ID of item and count. f.e.: 1-10: "
const val ENTER_ITEM_QUERY = "Enter search query you want to find: "

const val INPUT_ERROR = "Invalid user input"
const val MENU_INDEX_ERROR = "Invalid menu index"
const val ITEM_FORMAT_ERROR = "Incorrectly formatted item data"
const val NO_ITEMS_FOUND = "No such items were found"
const val OUT_OF_BOUNDS_ERROR = "You forgot to specify the count with a -"

// Be careful with object references as constants
val demoData get() = listOf(
    InventoryItem(0, "Samsung s20", 1300.1111, 16),
    InventoryItem(1, "Nokia 3310", 30.0, 99),
    InventoryItem(2, "Apple 10", 1000.0, 20),
    InventoryItem(3, "OnePlus", 500.0, 54),
    InventoryItem(4, "OnePlus 10", 700.0, 20)
)

val mainMenu = mutableListOf("--- Main menu ---").apply {
    addAll(MainMenu.values().map { "${it.index}: ${it.text}" })
}.toList()

val sortMenu = mutableListOf("--- Sort menu ---").apply {
    addAll(SortMenu.values().map { "${it.reference}: ${it.itemName}" })
}.toList()

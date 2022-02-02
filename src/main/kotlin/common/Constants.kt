package common

import models.InventoryItem
import models.MainMenu

const val APP_VERSION = "1.0"
const val GREETINGS_MESSAGE = "Inventory manager $APP_VERSION"
const val LEAVE_MESSAGE = "Thank you for using $GREETINGS_MESSAGE"
const val ENTER_MENU_INDEX = "Enter menu index: "
const val ENTER_TO_CONTINUE = "Enter to continue"
const val ENTER_ITEM_DATA = "Enter item data f.e.: OnePlus 9, 1234.99, 170: "

const val INPUT_ERROR = "Invalid user input"
const val MENU_INDEX_ERROR = "Invalid menu index"
const val INPUT_FORMAT_ERROR = "Incorrectly formatted item data"

val demoData = listOf(
    InventoryItem(0, "Samsung s20", 1300.1111, 16),
    InventoryItem(1, "Nokia 3310", 30.0, 99),
    InventoryItem(2, "Apple 10", 1000.0, 20),
    InventoryItem(3, "OnePlus", 500.0, 54),
    InventoryItem(4, "OnePlus 10", 700.0, 20)
)

val mainMenu = mutableListOf("--- Main menu ---").apply {
    addAll(MainMenu.values().map { "${it.index}: ${it.text}" })
}.toList()

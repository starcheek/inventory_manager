import common.*
import models.InventoryItem
import models.MainMenu

private val viewModel = ViewModel { inventory ->
    displayItems(inventory)
}

fun main(args: Array<String>) {
    println(GREETINGS_MESSAGE)
    showMenu()
}

private fun showMenu() {
    mainMenu.printAllElements()

    when (getIntegerInput(ENTER_MENU_INDEX)) {
        MainMenu.SHOW_ITEMS.index -> {}
        MainMenu.ADD_ITEMS.index -> {}
        MainMenu.SORT_ITEMS.index -> {}
        MainMenu.FIND_ITEMS.index -> {}
        MainMenu.SELL_ITEMS.index -> {}
        MainMenu.EXIT.index -> {}
        else -> println(MENU_INDEX_ERROR)
    }

    showMenu()
}

private fun displayItems(items: List<InventoryItem>) {
    println()
    System.out.printf("%-4s%-20s%-20s%-20s\n", "ID", "NAME", "PRICE", "COUNT")
    items.forEach { item ->
        System.out.printf("%-4s%-20s%-20.2f%-20s\n", item.id, item.name, item.price, item.count)
    }
    print(ENTER_TO_CONTINUE)
    readLine()
}

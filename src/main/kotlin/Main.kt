import common.*
import models.InventoryItem
import models.MainMenu
import kotlin.system.exitProcess

private val viewModel = ViewModel { inventory ->
    displayItems(inventory)
}

fun main(args: Array<String>) {
    println(GREETINGS_MESSAGE)
    println("Arguments: ${args.joinToString(separator = " | ")}")
    viewModel.init(demoData)
    showMenu()
}

private fun showMenu() {
    mainMenu.printAllElements()

    when (getIntegerInput(ENTER_MENU_INDEX)) {
        MainMenu.SHOW_ITEMS.index -> viewModel.getInventoryItems()
        MainMenu.ADD_ITEMS.index -> addItem()
        MainMenu.SELL_ITEMS.index -> sellItems()
        MainMenu.SORT_ITEMS.index -> sortItems()
        MainMenu.FIND_ITEMS.index -> findItems()
        MainMenu.EXIT.index -> exit()
        else -> println(MENU_INDEX_ERROR)
    }

    showMenu()
}

private fun findItems() {
    viewModel.findItems(getStringInput(ENTER_ITEM_QUERY)) { error ->
        println(error)
        findItems()
    }
}

private fun sellItems() {
    viewModel.sellItems(getStringInput(WHAT_TO_SELL)) { error ->
        println(error)
        sellItems()
    }
}

private fun addItem() {
    viewModel.addItem(getStringInput(ENTER_ITEM_DATA)) { error ->
        println(error)
        addItem()
    }
}

private fun sortItems() {
    sortMenu.printAllElements()
    viewModel.sortItems(
        input = getIntegerInput(ENTER_MENU_INDEX),
        onError = { error ->
            println(error)
            sortItems()
        }
    )
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

private fun exit() {
    println(LEAVE_MESSAGE)
    displayItems(demoData)
    exitProcess(0)
}

import common.*
import models.InventoryItem
import models.SortMenu

class ViewModel(
    private val onInventoryUpdated: (List<InventoryItem>) -> Unit
) {
    private val inventory = mutableListOf<InventoryItem>()

    fun init(data: List<InventoryItem>) {
        inventory.clear()
        inventory.addAll(data)
    }

    fun getInventoryItems() {
        // Imagine that this takes 30 seconds to complete
        onInventoryUpdated(inventory)
    }

    fun addItem(input: String, onError: (String) -> Unit) {
        // F.e. input is: OnePlus 9, 1234.99, 170
        try {
            val inputAsArray = input.split(",")
            val name = inputAsArray[0].trim()
            val price = inputAsArray[1].trim().toDouble()
            val count = inputAsArray[2].trim().toInt()
            val id = inventory.maxByOrNull { it.id }?.id?.plus(1) ?: 0
            inventory.add(InventoryItem(count = count, price = price, name = name, id = id))
            onInventoryUpdated(inventory)
        } catch (exception: IndexOutOfBoundsException) {
            onError(INPUT_ERROR)
        } catch (exception: NumberFormatException) {
            onError(ITEM_FORMAT_ERROR)
        }
    }

    fun sortItems(input: Int, onError: (String) -> Unit) {
        val inventoryCopy = inventory.map { it.copy() }.toMutableList()
        when (input) {
            SortMenu.ID.reference -> inventoryCopy.sortBy { it.id } // sortBy sorts by ascending
            SortMenu.NAME.reference -> inventoryCopy.sortBy { it.name }
            SortMenu.PRICE.reference -> inventoryCopy.sortByDescending { it.price }
            SortMenu.ITEM_COUNT.reference -> inventoryCopy.sortByDescending { it.count }
            else -> {
                onError(MENU_INDEX_ERROR)
                return
            }
        }
        onInventoryUpdated(inventoryCopy)
    }

    fun sellItems(input: String, onError: (String) -> Unit) {
        try {
            val inputAsArray = input.split("-")
            val id = inputAsArray[0].convertToInt()
            val count = inputAsArray[1].convertToInt()
            inventory.first { it.id == id }.let { item ->
                if (count > item.count) {
                    item.count = 0
                } else {
                    item.count -= count
                }
                onInventoryUpdated(inventory)
            }
        } catch (e: NumberFormatException) {
            onError(ITEM_FORMAT_ERROR)
        } catch (e: NoSuchElementException) {
            onError(NO_ITEMS_FOUND)
        } catch (e: IndexOutOfBoundsException) {
            onError(OUT_OF_BOUNDS_ERROR)
        }
    }

    fun findItems(input: String, onError: (String) -> Unit) {
        val filteredItems = inventory.filter {
            it.name.contains(input, true)
                    || it.price.toString().contains(input, true)
                    || it.count.toString().contains(input, true)
        }
        if (filteredItems.isNotEmpty()) {
            onInventoryUpdated(filteredItems)
        } else {
            onError(NO_ITEMS_FOUND)
        }
    }
}

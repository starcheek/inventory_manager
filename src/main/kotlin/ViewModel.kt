import common.INPUT_ERROR
import common.INPUT_FORMAT_ERROR
import models.InventoryItem

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
            onError(INPUT_FORMAT_ERROR)
        }
    }

}

import models.InventoryItem

class ViewModel(
    private val onInventoryUpdated: (inventory: List<InventoryItem>) -> Unit
) {

    private val inventory = mutableListOf<InventoryItem>()

}

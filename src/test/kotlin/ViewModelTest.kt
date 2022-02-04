import common.*
import models.InventoryItem
import models.SortMenu
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.*

class ViewModelTest {
    private lateinit var viewModel: ViewModel
    private lateinit var callback: Callback

    @BeforeEach
    fun onBefore() {
        callback = Callback()
        viewModel = ViewModel { receivedList ->
            callback.onInventoryUpdated(receivedList)
        }
    }

    class Callback {
        var inventory = listOf<InventoryItem>()
        var error = ""

        fun onInventoryUpdated(inventory: List<InventoryItem>) {
            this.inventory = inventory
        }

        fun onError(error: String) {
            this.error = error
        }
    }

    @Test
    fun `getInventoryItems should return a list of items`() {
        // Given some demo data
        viewModel.init(demoData)
        // When items are requested
        viewModel.getInventoryItems()
        // Then items are the same as demoData
        assertTrue { callback.inventory == demoData }
    }

    @Test
    fun `getInventoryItems should return an empty list`() {
        // When viewModel is not initialized with demoData
        viewModel.getInventoryItems()

        // Then empty list should be returned
        assertEquals(callback.inventory, emptyList<InventoryItem>())
    }

    @Test
    fun `addItem should update items list with new item`() {
        // When a new item is added to a list
        viewModel.addItem("OnePlus 9, 1234.99, 170") {
            /* Ignored */
        }
        assertTrue(
            callback.inventory.contains(
                InventoryItem(
                    0,
                    "OnePlus 9",
                    1234.99,
                    170
                )
            )
        )
    }

    @Test
    fun `addItem should call input error`() {
        // When incorrect input is passed
        viewModel.addItem("something") { error ->
            callback.onError(error)
        }

        // Then input error should returned
        assertEquals(INPUT_ERROR, callback.error)
    }

    @Test
    fun `addItem should call item format error`() {
        // When incorrect input is passed
        viewModel.addItem("OnePlus 9, 1234.99k, 170") { error ->
            callback.onError(error)
        }

        // Then input error should returned
        assertEquals(ITEM_FORMAT_ERROR, callback.error)
    }


    @Test
    fun `sortItems should return a sorted list by name`() {
        // Given a list
        viewModel.init(demoData)

        // When the list is sorted by name
        viewModel.sortItems(SortMenu.NAME.reference) {
            /* Ignored */
        }

        // Then sorted list should be received
        assertTrue(callback.inventory[0].name == "Apple 10")
        assertTrue(callback.inventory[1].name == "Nokia 3310")
        assertTrue(callback.inventory[2].name == "OnePlus")
        assertTrue(callback.inventory[3].name == "OnePlus 10")
        assertTrue(callback.inventory[4].name == "Samsung s20")
    }

    @Test
    fun `sortItems should return a sorted list by price`() {
        // Given a list
        viewModel.init(demoData)

        // When the list is sorted by name
        viewModel.sortItems(SortMenu.PRICE.reference) {
            /* Ignored */
        }

        // Then sorted list should be received
        assertTrue(callback.inventory[0].name == "Samsung s20")
        assertTrue(callback.inventory[1].name == "Apple 10")
        assertTrue(callback.inventory[2].name == "OnePlus 10")
        assertTrue(callback.inventory[3].name == "OnePlus")
        assertTrue(callback.inventory[4].name == "Nokia 3310")
    }

    @Test
    fun `sortItems should return error if incorrect index is passed`() {
        // Given incorrect menu index
        viewModel.sortItems(10) { error ->
            callback.onError(error)
        }

        // A menu index error should be returned
        assertTrue(callback.error == MENU_INDEX_ERROR)
    }

    @Test
    fun `sellItems should remove items from inventory`() {
        // Given correct input
        viewModel.init(demoData)
        viewModel.sellItems("3-10") {
            /* Ignore */
        }

        // Item should be updated in inventory
        assertEquals(callback.inventory.first { it.id == 3 }.count, 44)
    }

    @Test
    fun `sellItems should return no items found error`() {
        // Given non-existing id
        viewModel.sellItems("10-100") { error ->
            callback.onError(error)
        }

        // Then an no items found error should be returned
        assertEquals(NO_ITEMS_FOUND, callback.error)
    }

    @Test
    fun `findItems should filtered items`() {
        // Given valid query
        viewModel.init(demoData)
        viewModel.findItems("OnePlus") {
            /* ignored */
        }

        // Inventory should be filtered correctly
        assertTrue(callback.inventory[0].name == "OnePlus")
        assertTrue(callback.inventory[1].name == "OnePlus 10")
        assertFalse(callback.inventory.contains(InventoryItem(2, "Apple 10", 1000.0, 20),))
    }
}

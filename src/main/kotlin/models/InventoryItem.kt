package models

data class InventoryItem(
    val id: Int,
    val name: String,
    val price: Double,
    var count: Int
)

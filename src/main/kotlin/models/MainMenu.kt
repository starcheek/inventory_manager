package models

enum class MainMenu(val index: Int, val text: String) {
    SHOW_ITEMS(0, "Show items"),
    ADD_ITEMS(1, "Add items"),
    SELL_ITEMS(2, "Sell items"),
    SORT_ITEMS(3, "Sort items"),
    FIND_ITEMS(4, "Find items"),
    EXIT(5, "Exit")
}

import com.example.fireshoppingwithrecyclerview.ShoppingItem

object DataManager {

    val shoppingItems = mutableListOf<ShoppingItem>()

    init {
         createMockData()
    }

    fun createMockData(){
        shoppingItems.add(ShoppingItem("Äpple",23))
        shoppingItems.add(ShoppingItem("Banan",15))
        shoppingItems.add(ShoppingItem("Kiwi",14))
        shoppingItems.add(ShoppingItem("Apelsin",25))
        shoppingItems.add(ShoppingItem("Kokos",18))
        shoppingItems.add(ShoppingItem("Mjölk",15))
        shoppingItems.add(ShoppingItem("Ägg",42))
        shoppingItems.add(ShoppingItem("Socker",32))
    }
}
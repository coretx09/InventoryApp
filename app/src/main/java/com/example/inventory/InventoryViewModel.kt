package com.example.inventory

import androidx.lifecycle.*
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao): ViewModel() {

    // Get all items
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    // Retrieve Item by id
    fun retrieveItem(id: Int): LiveData<Item> = itemDao.getItem(id).asLiveData()


    // ADD ITEM FRAGMENT BUSINESS LOGIC
    fun addNewItem(itemName: String, itemPrice: String, itemCount: String) {
        val newItem = getNewItemEntry(itemName, itemPrice, itemCount)
        insertIem(newItem)
    }

    // Get input user
    private fun getNewItemEntry(itemName: String, itemPrice: String, itemCount: String): Item {
        return Item(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }

    // valid entry
    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String):Boolean {
        if (itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank()) {
            return false
        }
        return true
    }
    // INSERT NEW ITEM
    private fun insertIem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }



    // ITEM DETAIL B.L

    fun sellItem(item: Item) {
        if(item.quantityInStock > 0) {
            // Decrease the quantity by 1
            val newItem = item.copy(quantityInStock = item.quantityInStock - 1)
            updateItem(newItem)
        }
    }
    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item) }
    }

    fun isStockAvailable(item: Item): Boolean = item.quantityInStock > 0

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }




    class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return InventoryViewModel(itemDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }



}
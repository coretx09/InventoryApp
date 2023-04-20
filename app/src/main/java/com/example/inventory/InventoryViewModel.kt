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
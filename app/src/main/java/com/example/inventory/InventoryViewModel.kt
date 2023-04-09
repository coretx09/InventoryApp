package com.example.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao): ViewModel() {

    // ADD ITEM FRAGMENT BUSINESS LOGIC
    // INSERT NEW ITEM
    private fun insertIem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }
    // Get input user
    private fun getNewItemEntry(itemName:String, itemPrice:String, itemCount:String): Item {
        return Item(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }
    fun addNewItem(itemName:String, itemPrice:String, itemCount:String) {
        val newItem = getNewItemEntry(itemName,itemPrice,itemCount)
        insertIem(newItem)
    }




    class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return InventoryViewModel(itemDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}
package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
// One-shot write queries
    // INSERT NEW ITEM
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    // UPDATE ITEM
    @Update
    suspend fun update(item: Item)

    // DELETE ITEM
    @Delete
    suspend fun delete(item: Item)

// Observable read queries (Flow)
    // Retrieve a particular item from the item table
    @Query("SELECT * FROM item WHERE id = :id")
    fun getItem(id:Int): Flow<Item>

    // Return all columns from the item table
    @Query("SELECT * FROM item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>
}
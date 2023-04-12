package com.example.inventory

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.data.Item
import com.example.inventory.databinding.ItemListItemBinding

class ItemListAdapter(private val onItemClicked:(Item) -> Unit):
    ListAdapter<Item, ItemListAdapter.ItemViewHolder>(DiffCallback){


    class ItemViewHolder(private var binding: ItemListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            TODO("Not yet implemented")
        }

    }
}
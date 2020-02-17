package com.example.heroeslist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.heroeslist.R
import com.example.heroeslist.databinding.ListItemBinding

class ListAdapter(
    private val listItem: MutableList<String>
) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listItem.count()

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.listItemBinding.textItem.text = listItem[position]
    }

    fun updateRecyclerView(list: List<String>) {
        listItem.clear()
        listItem.addAll(list)
        notifyDataSetChanged()
    }
}
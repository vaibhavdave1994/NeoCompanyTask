package com.example.neocompany_task.ui.domain.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neocompany_task.databinding.FruitItemBinding
import com.example.neocompany_task.ui.data.model.FruitModel
import javax.inject.Inject


class FruitAdapter @Inject constructor(val context: Context) : RecyclerView.Adapter<ViewHolder>(){
    private lateinit var recyclerView: RecyclerView
    var items: List<FruitModel> = ArrayList()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FruitItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!!.get(position)
        item?.let {
            holder.apply {
                bind(item, isLinearLayoutManager())
                itemView.tag = item
            }
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        if (items == null) {
            return 0
        } else {
            return items!!.size
        }
    }

    fun submitList(itemList: List<FruitModel>){
        items = itemList!!
        notifyDataSetChanged()
    }

    private fun isLinearLayoutManager() = recyclerView.layoutManager is LinearLayoutManager
}

class ViewHolder(private val binding: FruitItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FruitModel, isLinearLayoutManager: Boolean) {
        binding.apply {
            model = item
        }
    }
}




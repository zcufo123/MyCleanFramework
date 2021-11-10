package com.example.mytestapplicationframework.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.mytestapplicationframework.data.entities.Character
import com.example.mytestapplicationframework.databinding.ItemBinding

class MainAdapter(private val listener: MainItemListener) : RecyclerView.Adapter<MainViewHolder>() {

    interface MainItemListener {
        fun onClicked(id: Int)
    }

    private val items = ArrayList<Character>()

    fun setItems(items: ArrayList<Character>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding: ItemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) = holder.bind(items[position])
}

class MainViewHolder(private val itemBinding: ItemBinding, private val listener: MainAdapter.MainItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var character: Character

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Character) {
        this.character = item
        itemBinding.name.text = item.name
        itemBinding.speciesAndStatus.text = """${item.species} - ${item.status}"""
        Glide.with(itemBinding.root)
            .load(item.image)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClicked(character.id)
    }
}


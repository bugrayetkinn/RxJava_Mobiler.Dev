package com.yetkin.rxjava_mobilerdev.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yetkin.rxjava_mobilerdev.data.model.PixabayPhoto
import com.yetkin.rxjava_mobilerdev.databinding.ItemPixabayBinding

class PixabayAdapter : ListAdapter<PixabayPhoto, PixabayAdapter.PixabayHolder>(DiffUtilCallBack) {

    class PixabayHolder(private val binding: ItemPixabayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: PixabayPhoto) {
            binding.apply {
                pixabayModel = model
                executePendingBindings()
            }
        }
    }

    companion object {
        val DiffUtilCallBack = object : DiffUtil.ItemCallback<PixabayPhoto>() {
            override fun areItemsTheSame(oldItem: PixabayPhoto, newItem: PixabayPhoto): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PixabayPhoto, newItem: PixabayPhoto): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayHolder =
        PixabayHolder(
            ItemPixabayBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: PixabayHolder, position: Int) =
        holder.bind(getItem(position))
}
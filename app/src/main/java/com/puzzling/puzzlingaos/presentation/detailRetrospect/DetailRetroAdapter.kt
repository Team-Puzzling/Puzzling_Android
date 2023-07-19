package com.puzzling.puzzlingaos.presentation.detailRetrospect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.data.model.response.ResponseDetailRetroDto
import com.puzzling.puzzlingaos.databinding.ItemDetailRetroBinding
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class DetailRetroAdapter() :
    ListAdapter<ResponseDetailRetroDto.DetailReviewData.Content, DetailRetroAdapter.DetailRetroViewHolder>(
        diffCallback,
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRetroViewHolder {
        val binding: ItemDetailRetroBinding =
            ItemDetailRetroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailRetroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailRetroViewHolder, position: Int) {
        return holder.onBind(currentList[position])
    }

    inner class DetailRetroViewHolder(private val binding: ItemDetailRetroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ResponseDetailRetroDto.DetailReviewData.Content) {
            binding.tvDetailRetroTitle.text = item.title
            binding.tvDetailRetroBody.text = item.content
        }
    }

    companion object {
        private val diffCallback =
            ItemDiffCallback<ResponseDetailRetroDto.DetailReviewData.Content>(
                onContentsTheSame = { old, new -> old == new },
                onItemsTheSame = { old, new -> old == new },
            )
    }
}

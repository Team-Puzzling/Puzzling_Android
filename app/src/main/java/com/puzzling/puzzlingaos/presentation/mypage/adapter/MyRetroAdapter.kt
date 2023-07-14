package com.puzzling.puzzlingaos.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import com.puzzling.puzzlingaos.databinding.ItemMyretroRetroBinding
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class MyRetroAdapter() : ListAdapter<ResponseMyRetroListDto, MyRetroAdapter.MyRetroViewHolder>(
    diffCallback,
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRetroViewHolder {
        val binding: ItemMyretroRetroBinding =
            ItemMyretroRetroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyRetroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRetroViewHolder, position: Int) {
        return holder.onBind(currentList[position])
    }

    class MyRetroViewHolder(private val binding: ItemMyretroRetroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ResponseMyRetroListDto) {
            binding.tvMyretroDate.text = item.reviewDate
        }
    }

    companion object {
        private val diffCallback =
            ItemDiffCallback<ResponseMyRetroListDto>(
                onContentsTheSame = { old, new -> old == new },
                onItemsTheSame = { old, new -> old == new },
            )
    }
}

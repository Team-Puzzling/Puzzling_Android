package com.puzzling.puzzlingaos.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import com.puzzling.puzzlingaos.databinding.ItemMyretroRetroBinding
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class MyRetroContentAdapter() : ListAdapter<ResponseMyRetroListDto, MyRetroContentAdapter.MyRetroContenViewHolder>(
    diffCallback,
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRetroContenViewHolder {
        val binding: ItemMyretroRetroBinding =
            ItemMyretroRetroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyRetroContenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRetroContenViewHolder, position: Int) {
        return holder.onBind(currentList[position])
    }

    class MyRetroContenViewHolder(private val binding: ItemMyretroRetroBinding) :
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

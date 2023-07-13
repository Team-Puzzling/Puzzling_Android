package com.puzzling.puzzlingaos.presentation.home.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.ItemMyProjectBinding
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class MyProjectAdapter() :
    ListAdapter<ResponseMyPageProjectDto, MyProjectAdapter.MyProjectViewHolder>(
        diffCallback,
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProjectViewHolder {
        val binding: ItemMyProjectBinding =
            ItemMyProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyProjectViewHolder, position: Int) {
        return holder.onBind(currentList[position])
    }

    class MyProjectViewHolder(private val binding: ItemMyProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ResponseMyPageProjectDto) {
            with(binding) {
                tvMyPageProjectName.text = item.projectName
            }
        }
    }

    companion object {
        private val diffCallback =
            ItemDiffCallback<ResponseMyPageProjectDto>(
                onContentsTheSame = { old, new -> old == new },
                onItemsTheSame = { old, new -> old == new },
            )
    }
}

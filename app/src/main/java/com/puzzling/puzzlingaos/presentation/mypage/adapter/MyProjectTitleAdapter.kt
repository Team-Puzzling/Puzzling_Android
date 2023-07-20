package com.puzzling.puzzlingaos.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemMypageNicknameBinding
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class MyProjectTitleAdapter() :
    ListAdapter<String, MyProjectTitleAdapter.MyProjectTitleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProjectTitleViewHolder {
        val binding: ItemMypageNicknameBinding =
            ItemMypageNicknameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProjectTitleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MyProjectTitleViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class MyProjectTitleViewHolder(private val binding: ItemMypageNicknameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(nickName: String) {
            binding.tvMyPageNickName.text = nickName + "님 안녕하세요 :)"
        }
    }

    companion object {
        private val diffCallback =
            ItemDiffCallback<String>(
                onContentsTheSame = { old, new -> old == new },
                onItemsTheSame = { old, new -> old == new },
            )
    }
}

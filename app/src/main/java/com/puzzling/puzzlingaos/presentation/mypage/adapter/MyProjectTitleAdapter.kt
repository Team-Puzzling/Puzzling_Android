package com.puzzling.puzzlingaos.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemMypageNicknameBinding

class MyProjectTitleAdapter(private val nickName: String) : RecyclerView.Adapter<MyProjectTitleAdapter.MyProjectTitleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProjectTitleViewHolder {
        val binding: ItemMypageNicknameBinding =
            ItemMypageNicknameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProjectTitleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MyProjectTitleViewHolder, position: Int) {
        holder.onBind(nickName)
    }

    class MyProjectTitleViewHolder(private val binding: ItemMypageNicknameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(nickName: String) {
            binding.tvMyPageNickName.text = nickName + "님 안녕하세요 :)"
        }
    }
}

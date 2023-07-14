package com.puzzling.puzzlingaos.presentation.home.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemMypageNicknameBinding

class MyProjectTopAdapter(private val nickName: String) : RecyclerView.Adapter<MyProjectTopAdapter.TopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val binding: ItemMypageNicknameBinding =
            ItemMypageNicknameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.onBind(nickName)
    }

    class TopViewHolder(private val binding: ItemMypageNicknameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(nickName: String) {
            binding.tvMyPageNickName.text = nickName + "님 안녕하세요 :)"
        }
    }
}

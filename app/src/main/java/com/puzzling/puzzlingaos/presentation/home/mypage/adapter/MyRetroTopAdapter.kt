package com.puzzling.puzzlingaos.presentation.home.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemMyretroProjectBinding

class MyRetroTopAdapter(private val projectName: String) :
    RecyclerView.Adapter<MyRetroTopAdapter.MyRetroTopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRetroTopViewHolder {
        val binding : ItemMyretroProjectBinding = ItemMyretroProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyRetroTopViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MyRetroTopViewHolder, position: Int) {
        holder.onBind(projectName)
    }

    class MyRetroTopViewHolder(private val binding: ItemMyretroProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(projectName: String) {
            binding.tvMyretroProjectName.text = projectName
        }
    }
}

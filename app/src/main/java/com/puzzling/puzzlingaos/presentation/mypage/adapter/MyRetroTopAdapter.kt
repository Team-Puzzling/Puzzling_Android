package com.puzzling.puzzlingaos.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemMyretroProjectBinding

class MyRetroTopAdapter(private val projectName: String) :
    RecyclerView.Adapter<MyRetroTopAdapter.MyRetroTopViewHolder>() {

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRetroTopViewHolder {
        val binding: ItemMyretroProjectBinding = ItemMyretroProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyRetroTopViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MyRetroTopViewHolder, position: Int) {
        holder.onBind(projectName)
    }

    inner class MyRetroTopViewHolder(private val binding: ItemMyretroProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(projectName: String) {
            binding.tvMyretroProjectName.text = projectName

            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                binding.containerMyPageProject.setOnClickListener {
                    listener?.onItemClick(itemView, projectName, bindingAdapterPosition)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, data: String, pos: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}

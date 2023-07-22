package com.puzzling.puzzlingaos.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import com.puzzling.puzzlingaos.databinding.ItemMyretroRetroBinding
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class MyRetroContentAdapter() :
    ListAdapter<ResponseMyRetroListDto.ReviewData, MyRetroContentAdapter.MyRetroContenViewHolder>(
        diffCallback,
    ) {

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRetroContenViewHolder {
        val binding: ItemMyretroRetroBinding =
            ItemMyretroRetroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyRetroContenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRetroContenViewHolder, position: Int) {
        return holder.onBind(currentList[position])
    }

    inner class MyRetroContenViewHolder(private val binding: ItemMyretroRetroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ResponseMyRetroListDto.ReviewData) {
            binding.tvMyretroDate.text = item.reviewDate

            if (item.contents.length < 17) {
                binding.tvMyretroDesc.text = item.contents + "..."
            } else {
                binding.tvMyretroDesc.text = item.contents.substring(0, 17)
                    .replace("\\r\\n|\\r|\\n|\\n\\r".toRegex(), " ") + "..."
            }

            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                binding.clMyRetroContainer.setOnClickListener {
                    listener?.onItemClick(itemView,item, bindingAdapterPosition)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, item:ResponseMyRetroListDto.ReviewData, pos: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    companion object {
        private val diffCallback =
            ItemDiffCallback<ResponseMyRetroListDto.ReviewData>(
                onContentsTheSame = { old, new -> old == new },
                onItemsTheSame = { old, new -> old == new },
            )
    }
}

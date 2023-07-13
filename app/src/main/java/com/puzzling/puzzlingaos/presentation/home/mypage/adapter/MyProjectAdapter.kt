package com.puzzling.puzzlingaos.presentation.home.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.ItemMyProjectBinding
import com.puzzling.puzzlingaos.util.ItemDiffCallback
import java.text.SimpleDateFormat
import java.util.*

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

                val now = Date()
                val startDate =
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(item.startDate)
                val timeDiff = (now.time - startDate.time) / (24 * 60 * 60 * 1000)

                if (timeDiff < 0) {
                    binding.tvMyPageStartDate.text = "D - ${kotlin.math.abs(timeDiff - 1)}"
                } else {
                    binding.tvMyPageStartDate.text = "D + ${timeDiff.toString()}"
                }
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

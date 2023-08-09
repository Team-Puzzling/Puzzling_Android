package com.puzzling.puzzlingaos.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.ItemMyretroAllProjectsBinding
import com.puzzling.puzzlingaos.databinding.ItemMyretroCurrentProjectBinding
import com.puzzling.puzzlingaos.domain.entity.Project

class ChooseProjectAdapter(private val selectedItem: (Project) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList = listOf<Project>()


    var currentProject = "프로젝트 이름"

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position].projectName) {
            currentProject -> CURRENT_PROJECT
            else -> CHOOSE_PROJECT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            CURRENT_PROJECT -> {
                val binding: ItemMyretroCurrentProjectBinding =
                    ItemMyretroCurrentProjectBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return CurrentProjectViewHolder(binding)
            }
            else -> {
                val binding: ItemMyretroAllProjectsBinding = ItemMyretroAllProjectsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                return ChooseProjectViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrentProjectViewHolder -> {
                holder.onBind(itemList[position])
            }
            is ChooseProjectViewHolder -> {
                holder.onBind(itemList[position])
            }
        }
    }

    inner class CurrentProjectViewHolder(private val binding: ItemMyretroCurrentProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Project) {
            binding.tvMyRetroProjectName.text = item.projectName

            binding.clMyRetroContainer.setOnClickListener {
                selectedItem(item)
                currentProject = item.projectName
            }
        }
    }

    inner class ChooseProjectViewHolder(private val binding: ItemMyretroAllProjectsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Project) {
            binding.tvMyRetroProjectName.text = item.projectName

            binding.clMyRetroContainer.setOnClickListener {
                selectedItem(item)
                currentProject = item.projectName
            }
        }
    }

    fun setItemList(newItemList: List<Project>) {
        itemList = newItemList
    }


    companion object {
        const val CURRENT_PROJECT = 0
        const val CHOOSE_PROJECT = 1
    }
}

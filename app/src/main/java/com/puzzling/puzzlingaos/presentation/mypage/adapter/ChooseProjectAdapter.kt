package com.puzzling.puzzlingaos.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemMyretroAllProjectsBinding
import com.puzzling.puzzlingaos.databinding.ItemMyretroCurrentProjectBinding
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class ChooseProjectAdapter(private val selectedItem: (Project) -> Unit) :
    ListAdapter<Project, RecyclerView.ViewHolder>(diffCallback) {


    var currentProject = "프로젝트 이름"

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position].projectName) {
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

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrentProjectViewHolder -> {
                holder.onBind(currentList[position])
            }
            is ChooseProjectViewHolder -> {
                holder.onBind(currentList[position])
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


    companion object {
        const val CURRENT_PROJECT = 0
        const val CHOOSE_PROJECT = 1
        private val diffCallback =
            ItemDiffCallback<Project>(
                onContentsTheSame = { old, new -> old == new },
                onItemsTheSame = { old, new -> old == new },
            )
    }
}

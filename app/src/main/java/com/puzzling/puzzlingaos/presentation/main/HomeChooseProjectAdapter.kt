package com.puzzling.puzzlingaos.presentation.main

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.ItemHomeProjectBinding
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class HomeChooseProjectAdapter(private val selectedItem: (String) -> Unit) :
    ListAdapter<ResponseMyPageProjectDto, HomeChooseProjectAdapter.HomeChooseProjectViewHolder>(
        ItemDiffCallback<ResponseMyPageProjectDto>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old == new },
        ),
    ) {
    private var selectedItemPosition = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeChooseProjectViewHolder {
        val binding =
            ItemHomeProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeChooseProjectViewHolder(binding, selectedItem)
    }

    override fun onBindViewHolder(holder: HomeChooseProjectViewHolder, position: Int) {
        holder.onBind(
            getItem(position) as ResponseMyPageProjectDto,
        )
    }

    inner class HomeChooseProjectViewHolder(
        private val binding: ItemHomeProjectBinding,
        private val selectedItem: (String) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private var isItemSelected = false
        fun onBind(data: ResponseMyPageProjectDto) = with(binding) {
            tvHomeProjectName.text = data.projectName
            clHomeProject.setOnClickListener {
                isItemSelected = !isItemSelected
                selectedItemPosition = bindingAdapterPosition
                val selectedProject = getItem(selectedItemPosition)
                Log.d("home", "selectedProject.projectName: ${selectedProject.projectName}")
                if (isItemSelected) {
                    binding.clHomeProject.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.ivHomeChooseProject.visibility = View.VISIBLE
                    selectedItem(selectedProject.projectName)
                } else {
                    binding.clHomeProject.setBackgroundColor(Color.parseColor("#FAFAFA"))
                    binding.ivHomeChooseProject.visibility = View.INVISIBLE
                }
            }
        }
    }
}

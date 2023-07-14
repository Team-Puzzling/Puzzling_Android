package com.puzzling.puzzlingaos.presentation.home.personal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemHomeActionplanBinding
import com.puzzling.puzzlingaos.domain.entity.ActionPlan
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class ActionPlanListAdapter : ListAdapter<ActionPlan, ActionPlanListAdapter.ActionPlanViewHolder>(
    ItemDiffCallback<ActionPlan>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new },
    ),
) {
    class ActionPlanViewHolder(private val binding: ItemHomeActionplanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ActionPlan) = with(binding) {
            tvActionplanContent.text = data.actionPlanContent
            tvActionplanDate.text = data.actionPlanDate
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ActionPlanViewHolder {
        val binding =
            ItemHomeActionplanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActionPlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActionPlanViewHolder, position: Int) {
        holder.onBind(
            getItem(position) as ActionPlan,
        )
    }
}

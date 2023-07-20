package com.puzzling.puzzlingaos.presentation.home.personal

import android.util.Log
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
        fun onBind(data: ActionPlan?) = with(binding) {
            /*
            Log.d("personal", "data.actionPlanContent:: ${data.actionPlanContent}")
            Log.d("personal", "data.actionPlanDate:: ${data.actionPlanDate}")
            // TODO item 텍스트 null일 경우 분기처리 해야함
            tvActionplanContent.text = data.actionPlanContent ?: "아직 회고가 없어요.\n첫 번째 회고를\n작성해 보세요!"
            tvActionplanDate.text = data.actionPlanDate ?: ""

             */
            if (data != null) {
                Log.d("personal", "data.actionPlanContent:: ${data.actionPlanContent}")
                Log.d("personal", "data.actionPlanDate:: ${data.actionPlanDate}")
                tvActionplanContent.text =
                    data.actionPlanContent ?: "아직 회고가 없어요.\n첫 번째 회고를\n작성해 보세요!"
                tvActionplanDate.text = data.actionPlanDate ?: ""
            } else {
                // Set default text when data is null or empty
                tvActionplanContent.text = "아직 회고가 없어요.\n첫 번째 회고를\n작성해 보세요!"
                tvActionplanDate.text = ""
            }
        }
    }

    fun setData(data: List<ActionPlan>) {
        submitList(data)
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

package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemTeamThisWeekBinding
import com.puzzling.puzzlingaos.domain.entity.ThisWeek
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class RetrospectThisWeekAdapter(private val viewModel: TeamCurrentSituationViewModel) : ListAdapter<ThisWeek, RetrospectThisWeekAdapter.RetrospectThisWeekViewHolder>(
    ItemDiffCallback<ThisWeek>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new },
    ),
) {

    private var itemList = mutableListOf<ThisWeek>(
        ThisWeek("3"),
        ThisWeek("4"),
        ThisWeek("5"),
        ThisWeek("6"),
        ThisWeek("7"),
        ThisWeek("8"),
        ThisWeek("9"),

    )

    inner class RetrospectThisWeekViewHolder(private val binding: ItemTeamThisWeekBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(thisWeek: ThisWeek) {
            binding.tvTeamThisWeek.text = thisWeek.thisDay
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrospectThisWeekViewHolder {
        val binding = ItemTeamThisWeekBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RetrospectThisWeekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RetrospectThisWeekViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}

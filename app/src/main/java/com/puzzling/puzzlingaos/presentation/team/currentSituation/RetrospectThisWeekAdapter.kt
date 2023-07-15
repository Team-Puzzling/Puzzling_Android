package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemTeamThisWeekBinding
import com.puzzling.puzzlingaos.domain.entity.ThisWeek
import com.puzzling.puzzlingaos.util.ItemDiffCallback
import java.time.DayOfWeek
import java.time.LocalDate

class RetrospectThisWeekAdapter(private val viewModel: TeamCurrentSituationViewModel) : ListAdapter<ThisWeek, RetrospectThisWeekAdapter.RetrospectThisWeekViewHolder>(
    ItemDiffCallback<ThisWeek>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new },
    ),
) {

    @RequiresApi(Build.VERSION_CODES.O)
    private var itemList = mutableListOf(
        ThisWeek(getWeekDatesWithToday()[0].dayOfMonth.toString()),
        ThisWeek(getWeekDatesWithToday()[1].dayOfMonth.toString()),
        ThisWeek(getWeekDatesWithToday()[2].dayOfMonth.toString()),
        ThisWeek(getWeekDatesWithToday()[3].dayOfMonth.toString()),
        ThisWeek(getWeekDatesWithToday()[4].dayOfMonth.toString()),
        ThisWeek(getWeekDatesWithToday()[5].dayOfMonth.toString()),
        ThisWeek(getWeekDatesWithToday()[6].dayOfMonth.toString()),
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RetrospectThisWeekViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getItemCount(): Int = itemList.size

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeekDatesWithToday(): List<LocalDate> {
        val today = LocalDate.now()
        val startOfWeek = today.with(DayOfWeek.MONDAY) // 해당 주의 시작일
        val endOfWeek = today.with(DayOfWeek.SUNDAY) // 해당 주의 종료일

        val dates = mutableListOf<LocalDate>()
        var currentDate = startOfWeek

        while (!currentDate.isAfter(endOfWeek)) {
            dates.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }

        return dates
    }
}

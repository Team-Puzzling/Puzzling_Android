package com.puzzling.puzzlingaos.presentation.register

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.databinding.ItemRegisterRetrospectWeekCycleBinding
import com.puzzling.puzzlingaos.domain.entity.RetrospectWeekCycle
import com.puzzling.puzzlingaos.util.ItemDiffCallback

class RetrospectWeekCycleAdapter(private val viewmodel: RegisterViewModel) : ListAdapter<RetrospectWeekCycle, RetrospectWeekCycleAdapter.RetrospectWeekCycleViewHolder>(
    ItemDiffCallback<RetrospectWeekCycle>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new },
    ),
) {

    private val itemList: List<RetrospectWeekCycle> = listOf(
        RetrospectWeekCycle("월"),
        RetrospectWeekCycle("화"),
        RetrospectWeekCycle("수"),
        RetrospectWeekCycle("목"),
        RetrospectWeekCycle("금"),
        RetrospectWeekCycle("토"),
        RetrospectWeekCycle("일"),
    )

    inner class RetrospectWeekCycleViewHolder(private val binding: ItemRegisterRetrospectWeekCycleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dayCycle: RetrospectWeekCycle) {
            binding.tvRegisterDayCycle.text = dayCycle.day
            binding.root.setOnClickListener {
                daySelection(binding, dayCycle)
                onItemClickListener?.let { it(dayCycle) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrospectWeekCycleViewHolder {
        val binding = ItemRegisterRetrospectWeekCycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RetrospectWeekCycleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RetrospectWeekCycleViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    private var onItemClickListener: ((RetrospectWeekCycle) -> Unit)? = null
    fun setOnDayClickListener(listener: (RetrospectWeekCycle) -> Unit) {
        onItemClickListener = listener
    }

    var selectedRetrospectDayArray = arrayListOf<String>()
    private val dayKey = listOf("월", "화", "수", "목", "금", "토", "일")
    private var dayMap = HashMap<Int, String>()
    private var daySortedMap = LinkedHashMap<Int, String>()
    private var sortedSelectedRetrospectDayArray = arrayListOf<String>()

    private fun daySelection(binding: ItemRegisterRetrospectWeekCycleBinding, retrospectWeekCycle: RetrospectWeekCycle) {
        if (selectedRetrospectDayArray.contains(retrospectWeekCycle.day)) {
            selectedRetrospectDayArray.remove(retrospectWeekCycle.day)
            changeBackground(binding, false)
        } else {
            selectedRetrospectDayArray.add(retrospectWeekCycle.day)
            changeBackground(binding, true)
        }
        daySorted(selectedRetrospectDayArray)

        viewmodel.isDateCycleSelected.value = sortedSelectedRetrospectDayArray
    }

    private fun daySorted(dayArray: ArrayList<String>) {
        for (i in 0 until dayArray.size) {
            dayMap[dayKey.indexOf(dayArray[i])] = dayArray[i]
        }
        dayMap.keys.sorted().forEach { daySortedMap[it] = dayMap[it]!! }
        sortedSelectedRetrospectDayArray = daySortedMap.values.sorted().toTypedArray().toCollection(ArrayList())
    }

    private fun changeBackground(binding: ItemRegisterRetrospectWeekCycleBinding, bool: Boolean) {
        binding.root.backgroundTintList =
            when (bool) {
                true -> ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.blue_400))
                false -> ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.bg_050))
            }
    }

}

package com.puzzling.puzzlingaos.presentation.register

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.databinding.ItemRegisterDayCycleBinding
import com.puzzling.puzzlingaos.domain.entity.DayCycle

class DayCycleAdapter(context: Context) : RecyclerView.Adapter<DayCycleAdapter.DayCycleViewHolder>() {
    // private val inflater by lazy { LayoutInflater.from(context) }

    private val itemList: List<DayCycle> = listOf(
        DayCycle("일"),
        DayCycle("월"),
        DayCycle("화"),
        DayCycle("수"),
        DayCycle("목"),
        DayCycle("금"),
        DayCycle("토"),
    )

    inner class DayCycleViewHolder(private val binding: ItemRegisterDayCycleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dayCycle: DayCycle) {
            binding.tvRegisterDayCycle.text = dayCycle.day
            binding.root.setOnClickListener {
                daySelection(binding, dayCycle)
                onItemClickListener?.let { it(dayCycle) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayCycleViewHolder {
        val binding = ItemRegisterDayCycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayCycleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayCycleViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    private var onItemClickListener: ((DayCycle) -> Unit)? = null
    fun setOnDayClickListener(listener: (DayCycle) -> Unit) {
        onItemClickListener = listener
    }

    var selectedDayCycle = arrayListOf<DayCycle>()
    var selectedDayArray = arrayListOf<String>()

    private fun daySelection(binding: ItemRegisterDayCycleBinding, dayCycle: DayCycle) {
        if (selectedDayCycle.contains(dayCycle)) {
            selectedDayCycle.remove(dayCycle)
            selectedDayArray.remove(dayCycle.day)
            changeBackground(binding, false)
        } else {
            selectedDayCycle.add(dayCycle)
            selectedDayArray.add(dayCycle.day)
            changeBackground(binding, true)
        }
    }

    private fun changeBackground(binding: ItemRegisterDayCycleBinding, bool: Boolean) {
        binding.root.backgroundTintList =
            when (bool) {
                true -> ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.blue_400))
                false -> ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.white_000))
            }
    }

    fun getSelectedDayCycleNum(): Int = selectedDayCycle.size
}

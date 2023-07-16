package com.puzzling.puzzlingaos.presentation.detailRetrospect

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class WeekTapAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailRetroFragment("5")
            1 -> DetailRetroFragment("6")
            2 -> DetailRetroFragment("7")
            3 -> DetailRetroFragment("8")
            4 -> DetailRetroFragment("9")
            5 -> DetailRetroFragment("10")
            6 -> DetailRetroFragment("11")
            else -> DetailRetroFragment("5")
        }
    }
}

package com.puzzling.puzzlingaos.presentation.detailRetrospect

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.puzzling.puzzlingaos.data.model.response.ResponseDetailRetroDto

class WeekTapAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NoDetailRetroFragment()
            1 -> NoDetailRetroFragment()
            2 -> NoDetailRetroFragment()
            3 -> NoDetailRetroFragment()
            4 -> NoDetailRetroFragment()
            5 -> NoDetailRetroFragment()
            6 -> NoDetailRetroFragment()
            else -> NoDetailRetroFragment()
        }
    }
}

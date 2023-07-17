package com.puzzling.puzzlingaos.presentation.team.currentSituation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.puzzling.puzzlingaos.presentation.detailRetrospect.NoDetailRetroFragment

class RetrospectThisWeekAdapter(
    fragment: Fragment,
    private val num: MutableList<Int>,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        return when (num[position]) {
            0 -> NoDetailRetroFragment()
            1 -> NoDetailRetroFragment()
            2 -> ItemTeamRetrospectListFragment(position)
            else -> ItemTeamRetrospectListFragment(position)
        }
    }
}

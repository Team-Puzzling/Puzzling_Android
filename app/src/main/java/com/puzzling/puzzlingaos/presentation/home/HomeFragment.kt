package com.puzzling.puzzlingaos.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentHomeBinding

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabItem()
        clickTabItem()
    }

    private fun initTabItem() {
        parentFragmentManager.commit {
            replace(R.id.fcv_home_container, PersonalDashboardFragment())
        }
    }

    private fun clickTabItem() {
        binding.tlHomeDashboard.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment: Fragment = when (tab.position) {
                    0 -> PersonalDashboardFragment()
                    1 -> JyTeamDashboardFragment()
                    else -> PersonalDashboardFragment()
                }
                parentFragmentManager.commit {
                    replace(R.id.fcv_home_container, fragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Not used
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Not used
            }
        })
    }

//    private inline fun <reified T : Fragment> navigateTo() {
//        supportFragmentManager.commit {
//            replace(R.id.fcv_main_container, T::class.java.newInstance())
//        }
//    }
}

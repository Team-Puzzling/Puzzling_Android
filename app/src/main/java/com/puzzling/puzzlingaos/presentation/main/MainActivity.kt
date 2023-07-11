package com.puzzling.puzzlingaos.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityMainBinding
import com.puzzling.puzzlingaos.presentation.home.JyTeamDashBoardFragment
import com.puzzling.puzzlingaos.presentation.home.PersonalBoardFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTabLayout()
        clickTabItem()
    }

    private fun initTabLayout() {
        supportFragmentManager.findFragmentById(R.id.fl_home_main)
            ?: navigateTo<PersonalBoardFragment>()
    }

    private fun clickTabItem() {
        binding.tlHomeDashboard.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> navigateTo<PersonalBoardFragment>()
                    1 -> navigateTo<JyTeamDashBoardFragment>()
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

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace(R.id.fl_home_main, T::class.java.newInstance())
        }
    }
}

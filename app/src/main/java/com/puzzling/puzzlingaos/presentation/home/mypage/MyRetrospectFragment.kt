package com.puzzling.puzzlingaos.presentation.home.mypage

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentMyRetrospectBinding

class MyRetrospectFragment :
    BaseFragment<FragmentMyRetrospectBinding>(R.layout.fragment_my_retrospect) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
    }

    private fun initToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.tbMyRetrospectMain)
        activity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_main_container, MyPageFragment())
                    .commit()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

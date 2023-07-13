package com.puzzling.puzzlingaos.presentation.home.mypage

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityMyRetrospectBinding
import com.puzzling.puzzlingaos.presentation.main.MainActivity

class MyRetrospectActivity :
    BaseActivity<ActivityMyRetrospectBinding>(R.layout.activity_my_retrospect) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbMyRetrospectMain)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

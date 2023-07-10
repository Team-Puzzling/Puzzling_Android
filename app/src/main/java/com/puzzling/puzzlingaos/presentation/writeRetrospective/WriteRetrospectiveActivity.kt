package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.os.Bundle
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityWriteRetrospectiveBinding

class WriteRetrospectiveActivity :
    BaseActivity<ActivityWriteRetrospectiveBinding>(R.layout.activity_write_retrospective) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_retrospective)
    }
}

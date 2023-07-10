package com.puzzling.puzzlingaos.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.puzzling.puzzlingaos.data.repository.RegisterRepositoryImpl
import com.puzzling.puzzlingaos.data.source.remote.RegisterRemoteDataSource
import com.puzzling.puzzlingaos.presentation.register.RegisterViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun<T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(RegisterRepositoryImpl(RegisterRemoteDataSource())) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}
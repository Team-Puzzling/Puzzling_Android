package com.puzzling.puzzlingaos.presentation.detailRetrospect

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.response.ResponseDetailRetroDto
import kotlinx.coroutines.launch

class DetailRetroViewModel(context: Context) : ViewModel() {
    private val _detailRetroList = MutableLiveData<ArrayList<ResponseDetailRetroDto>>()
    val detailRetroList: LiveData<ArrayList<ResponseDetailRetroDto>> get() = _detailRetroList

    val week = listOf(
        "월",
        "화",
        "수",
        "목",
        "금",
        "토",
        "일",
    )

    private val dummyList = arrayListOf<ResponseDetailRetroDto>(
        ResponseDetailRetroDto(null, "월", "2023-07-17", null),
        ResponseDetailRetroDto(
            4,
            "화",
            "2023-07-18",
            listOf(
                ResponseDetailRetroDto.Content("잘한점...", "잘한점 어쩌구...."),
                ResponseDetailRetroDto.Content("아쉬운점...", "아쉬운점 어쩌구...."),
                ResponseDetailRetroDto.Content("배운점...", "배운점 어쩌구...."),
            ),
        ),
        ResponseDetailRetroDto(
            9,
            "목",
            "2023-07-20",
            listOf(
                ResponseDetailRetroDto.Content("잘한점...", "잘한점 어쩌구...."),
                ResponseDetailRetroDto.Content("아쉬운점...", "아쉬운점 어쩌구...."),
                ResponseDetailRetroDto.Content("배운점...", "배운점 어쩌구...."),
            ),
        ),
        ResponseDetailRetroDto(null, "금", "2023-07-21", null),
    )

    fun getDetailRetroList() = viewModelScope.launch {
        _detailRetroList.value = dummyList
    }
}

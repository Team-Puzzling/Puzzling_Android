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
                ResponseDetailRetroDto.Content(
                    "잘한점...",
                    "placeholder 위치, 텍스트박스는 이 사이즈가 기본 사이즈입니다. " +
                        "양 옆 마진 16px을 가진 텍스트박스가 존재합니다. 회고를 길게길게 작성하여 내용이 더욱 길어지면 다음과 같이 작성한 회고 길이에 맞게 길어지게 됩니다. " +
                        "길어지고 길어지고 빙빙 돌아가는 회전목마처럼 어쩌고 ",
                ),
                ResponseDetailRetroDto.Content(
                    "아쉬운점...",
                    "placeholder 위치, 텍스트박스는 이 사이즈가 기본 사이즈입니다. " +
                        "양 옆 마진 16px을 가진 텍스트박스가 존재합니다. 회고를 길게길게 작성하여 내용이 더욱 길어지면 다음과 같이 작성한 회고 길이에 맞게 길어지게 됩니다. " +
                        "길어지고 길어지고 빙빙 돌아가는 회전목마처럼 어쩌고 ",
                ),
                ResponseDetailRetroDto.Content(
                    "배운점...",
                    "placeholder 위치, 텍스트박스는 이 사이즈가 기본 사이즈입니다. " +
                        "양 옆 마진 16px을 가진 텍스트박스가 존재합니다. 회고를 길게길게 작성하여 내용이 더욱 길어지면 다음과 같이 작성한 회고 길이에 맞게 길어지게 됩니다. " +
                        "길어지고 길어지고 빙빙 돌아가는 회전목마처럼 어쩌고 ",
                ),
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

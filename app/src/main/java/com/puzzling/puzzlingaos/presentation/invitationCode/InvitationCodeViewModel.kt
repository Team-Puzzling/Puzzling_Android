package com.puzzling.puzzlingaos.presentation.invitationCode

import android.util.Log
import androidx.lifecycle.*
import com.puzzling.puzzlingaos.data.model.request.RequestInvitationCode
import com.puzzling.puzzlingaos.data.model.response.ResponseInvitationCodeDto
import com.puzzling.puzzlingaos.domain.repository.ProjectRepository
import com.puzzling.puzzlingaos.util.UserInfo.MEMBER_ID
import com.puzzling.puzzlingaos.util.UserInfo.PROJECT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvitationCodeViewModel @Inject constructor(private val repository: ProjectRepository) :
    ViewModel() {

    // inputCode 관련
    val inputCode = MutableStateFlow("")
    val inputCodeLength = inputCode.map { inputCode ->
        inputCode.length.toString()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    private val isCodeInEmoji: StateFlow<Boolean> = inputCode.map { inputCode ->
        inputCode.matches(Regex(EMOJI_PATTERN))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

    // nickName 관련
    val inputNickName = MutableStateFlow("")
    val inputNickNameLength = inputNickName.map { inputNickName ->
        inputNickName.length.toString()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    val isNickNameInEmoji: StateFlow<Boolean> = inputNickName.map { inputNickName ->
        inputNickName.matches(Regex(EMOJI_PATTERN))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

    // Role 관련
    val inputRole = MutableStateFlow("")
    val inputRoleLength = inputRole.map { inputRole ->
        inputRole.length.toString()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    val isRoleInEmoji: StateFlow<Boolean> = inputRole.map { inputRole ->
        inputRole.matches(Regex(EMOJI_PATTERN))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

    private val _codeResponse =
        MutableStateFlow<ResponseInvitationCodeDto.InvitationCodeData?>(null)
    val codeResponse = _codeResponse.asStateFlow()

    private val _isCodeSucces = MutableStateFlow<Boolean?>(null)
    val isCodeSuccess = _isCodeSucces.asStateFlow()

    private val _isProfileSucces = MutableStateFlow<Boolean?>(null)
    val isProfileSuccess = _isProfileSucces.asStateFlow()

    val codeErrorMessage = MutableStateFlow("")
    val nickNameErrorMessage = MutableStateFlow<String>("")
    val roleErrorMessage = MutableStateFlow<String>("")

    val isValidCode: StateFlow<Boolean> =
        combine(isCodeSuccess, isCodeInEmoji) { isCodeSuccess, isCodeInEmoji ->
            when {
                isCodeInEmoji.not() -> {
                    codeErrorMessage.value = EMOJI_ERROR
                    return@combine false
                }
                isCodeSuccess == null -> true
                isCodeInEmoji && isCodeSuccess -> true
                isCodeSuccess.not() -> {
                    codeErrorMessage.value = INPUT_CODE_ERROR
                    return@combine false
                }
                else -> true
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

    val isValidNickName: StateFlow<Boolean> =
        combine(isProfileSuccess, isNickNameInEmoji) { isProfileSuccess, isNickNameInEmoji ->
            when {
                isNickNameInEmoji.not() -> {
                    nickNameErrorMessage.value = EMOJI_ERROR
                    return@combine false
                }
                isProfileSuccess == null -> true
                isNickNameInEmoji && isProfileSuccess -> true
                isProfileSuccess.not() -> {
                    nickNameErrorMessage.value = NICKNAME_ALREADY_INUSE
                    return@combine false
                }
                else -> true
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

    val isValidRole: StateFlow<Boolean> =
        combine(isProfileSuccess, isRoleInEmoji) { isProfileSuccess, isRoleInEmoji ->
            when {
                isRoleInEmoji.not() -> {
                    roleErrorMessage.value = EMOJI_ERROR
                    return@combine false
                }
                isProfileSuccess == null -> true
                isRoleInEmoji && isProfileSuccess -> true
                else -> true
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

    fun isCodeValid() = viewModelScope.launch {
        kotlin.runCatching {
            repository.isValidInvitationCode(inputCode.value)
        }.onSuccess { response ->
            _isCodeSucces.value = true
            _codeResponse.value = response.data
            Log.d("초대코드", "$response")
        }.onFailure {
            _isCodeSucces.value = false
            Log.d("초대코드", "$it")
        }
    }

    fun joinProject() = viewModelScope.launch {
        kotlin.runCatching {
            repository.joinProject(
                MEMBER_ID,
                RequestInvitationCode(1, inputNickName.value, inputRole.value),
            )
        }.onSuccess { response ->
            _isProfileSucces.value = true
            Log.d("프로젝트 참여하기", "프로젝트 참여하기 $response")
        }.onFailure {
            _isProfileSucces.value = false
            Log.d("프로젝트 참여하기", "프로젝트 참여 실패 $it")
        }
    }

    companion object {
        const val EMOJI_PATTERN: String =
            "^[ㄱ-ㅣ가-힣a-zA-Z0-9 \\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$"
        const val INPUT_CODE_ERROR = "유효하지 않은 초대코드에요. 코드를 확인해주세요."
        const val EMOJI_ERROR = "특수문자, 이모지를 사용할 수 없어요."
        const val NICKNAME_ALREADY_INUSE = "이미 사용 중인 닉네임이에요."
    }
}

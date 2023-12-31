package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.request.RequestReview5FDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewAARDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewTILDto
import com.puzzling.puzzlingaos.domain.entity.AAR
import com.puzzling.puzzlingaos.domain.entity.F5
import com.puzzling.puzzlingaos.domain.entity.ReviewType
import com.puzzling.puzzlingaos.domain.entity.TIL
import com.puzzling.puzzlingaos.domain.repository.WriteReviewRepository
import com.puzzling.puzzlingaos.util.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteReviewViewModel @Inject constructor(
    private val repository: WriteReviewRepository,
) : ViewModel() {
    private val registerRegex = WRITE_REGEX.toRegex()
    private var til: TIL? = null
    private var aar: AAR? = null
    private var f5: F5? = null

    val tilQuestion1 = MutableLiveData<String>()
    val tilQuestion2 = MutableLiveData<String>()
    val tilQuestion3 = MutableLiveData<String>()
    var isValidTilquestion1: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidTilquestion2: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidTilquestion3: MutableLiveData<Boolean> = MutableLiveData(true)

    val question5f1 = MutableLiveData<String>()
    val question5f2 = MutableLiveData<String>()
    val question5f3 = MutableLiveData<String>()
    val question5f4 = MutableLiveData<String>()
    val question5f5 = MutableLiveData<String>()
    var isValid5fquestion1: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValid5fquestion2: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValid5fquestion3: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValid5fquestion4: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValid5fquestion5: MutableLiveData<Boolean> = MutableLiveData(true)

    val aarQuestion1 = MutableLiveData<String>()
    val aarQuestion2 = MutableLiveData<String>()
    val aarQuestion3 = MutableLiveData<String>()
    val aarQuestion4 = MutableLiveData<String>()
    val aarQuestion5 = MutableLiveData<String>()
    var isValidAarquestion1: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidAarquestion2: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidAarquestion3: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidAarquestion4: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidAarquestion5: MutableLiveData<Boolean> = MutableLiveData(true)

    private val _isReviewTypeSelected = MutableLiveData(false)
    val isReviewTypeSelected: LiveData<Boolean>
        get() = _isReviewTypeSelected

    private val _selectedReviewType = MutableLiveData<String>()
    val selectedReviewType: LiveData<String>
        get() = _selectedReviewType

    private val _selectedReviewNum = MutableLiveData<Int>()
    val selectedReviewNum: LiveData<Int>
        get() = _selectedReviewNum

    private val _reviewTypeText = MutableLiveData<String>("TIL")
    val reviewTypeText: LiveData<String>
        get() = _reviewTypeText

    private val _selectedChipText = MutableLiveData<String>()
    val selectedChipText: LiveData<String>
        get() = _selectedChipText

    private var _reviewTypeList: MutableLiveData<List<ReviewType>> = MutableLiveData()
    val reviewTypeList: LiveData<List<ReviewType>>
        get() = _reviewTypeList

    private var _reviewNameList: MutableLiveData<List<String>> = MutableLiveData()
    val reviewNameList: LiveData<List<String>>
        get() = _reviewNameList

    private var _reviewDescList: MutableLiveData<List<String>> = MutableLiveData()
    val reviewDescList: LiveData<List<String>>
        get() = _reviewDescList

    private var _templateIdList: MutableLiveData<List<Int>> = MutableLiveData()
    val templateIdList: LiveData<List<Int>>
        get() = _templateIdList

    private val _previousReviewType = MutableLiveData<Int>()
    val previousReviewType: LiveData<Int>
        get() = _previousReviewType

    private val _isPostSuccess = MutableLiveData<Boolean>(false)
    val isPostSuccess: LiveData<Boolean>
        get() = _isPostSuccess

    val projectId = MutableLiveData<Int>()
    val projectName = MutableLiveData<String>()


    fun setSelectedChipText(chipText: String) {
        _selectedChipText.value = chipText
    }

    fun validTextBox(textBox: String): Boolean {
        return textBox.matches(registerRegex)
    }

    fun validRegister(textBox: String): Boolean {
        return textBox.matches(registerRegex) && !textBox.isNullOrBlank()
    }

    fun isTilValid(): Boolean {
        return validRegister(tilQuestion1.value.orEmpty()) &&
            validRegister(tilQuestion2.value.orEmpty()) &&
            validRegister(tilQuestion3.value.orEmpty())
    }

    fun is5fValid(): Boolean {
        return validRegister(question5f1.value.orEmpty()) &&
            validRegister(question5f2.value.orEmpty()) &&
            validRegister(question5f3.value.orEmpty()) &&
            validRegister(question5f4.value.orEmpty()) &&
            validRegister(question5f5.value.orEmpty())
    }

    fun isAarValid(): Boolean {
        return validRegister(aarQuestion1.value.orEmpty()) &&
            validRegister(aarQuestion2.value.orEmpty()) &&
            validRegister(aarQuestion3.value.orEmpty()) &&
            validRegister(aarQuestion4.value.orEmpty()) &&
            validRegister(aarQuestion5.value.orEmpty())
    }

    val isEnabledTilRegister = MediatorLiveData<Boolean>().apply {
        addSource(tilQuestion1) { value = isTilValid() }
        addSource(tilQuestion2) { value = isTilValid() }
        addSource(tilQuestion3) { value = isTilValid() }
    }

    val isEnabled5fRegister = MediatorLiveData<Boolean>().apply {
        addSource(question5f1) { value = is5fValid() }
        addSource(question5f2) { value = is5fValid() }
        addSource(question5f3) { value = is5fValid() }
        addSource(question5f4) { value = is5fValid() }
        addSource(question5f5) { value = is5fValid() }
    }

    val isEnabledAarRegister = MediatorLiveData<Boolean>().apply {
        addSource(aarQuestion1) { value = isAarValid() }
        addSource(aarQuestion2) { value = isAarValid() }
        addSource(aarQuestion3) { value = isAarValid() }
        addSource(aarQuestion4) { value = isAarValid() }
        addSource(aarQuestion5) { value = isAarValid() }
    }

    var isInputEnabled: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkBtnEnabled() {
        isInputEnabled.value = when (_selectedReviewType.value) {
            "TIL" -> isTilValid()
            "5F" -> is5fValid()
            "AAR" -> isAarValid()
            else -> false
        }
    }

    init {
        getReviewType()
    }

    fun setSelectedReviewTypeText(reviewType: String) {
        _selectedReviewType.value = reviewType
        when (reviewType) {
            "TIL" -> _selectedReviewNum.value = 1
            "5F" -> _selectedReviewNum.value = 2
            "AAR" -> _selectedReviewNum.value = 3
        }
        Log.d("write", "_selectedReviewType.value :: ${_selectedReviewType.value}")

        _isReviewTypeSelected.value = true
    }

    fun postReviewTIL(projectId: Int) {
        val requestReviewTIL = RequestReviewTILDto(
            _selectedReviewNum.value!!,
            tilQuestion1.value.toString(),
            tilQuestion2.value.toString(),
            tilQuestion3.value.toString(),
        )
        viewModelScope.launch {
            repository.uploadTIL(
                UserInfo.POST_MEMBER_ID,
                projectId,
                requestReviewTIL,
            ).onSuccess {
                _isPostSuccess.value = true
                projectName.value = it.data.projectName
                Log.d("write", "postReviewTIL() success!!")
            }.onFailure {
                Log.d("write", "postReviewTIL() Fail:: $it")
            }
        }
    }

    fun postReview5F(projectId: Int) {
        val requestReview5F = RequestReview5FDto(
            _selectedReviewNum.value!!,
            question5f1.value.toString(),
            question5f2.value.toString(),
            question5f3.value.toString(),
            question5f4.value.toString(),
            question5f5.value.toString(),
        )
        viewModelScope.launch {
            repository.upload5F(
                UserInfo.POST_MEMBER_ID,
                projectId,
                requestReview5F,
            ).onSuccess {
                projectName.value = it.data.projectName
                _isPostSuccess.value = true
                Log.d("write", "postReview5F() success!!")
            }.onFailure {
                Log.d("write", "postReview5F() Fail:: $it")
            }
        }
    }

    fun postReviewAAR(projectId: Int) {
        val requestReviewAAR = RequestReviewAARDto(
            _selectedReviewNum.value!!,
            aarQuestion1.value.toString(),
            aarQuestion2.value.toString(),
            aarQuestion3.value.toString(),
            aarQuestion4.value.toString(),
            aarQuestion5.value.toString(),
        )
        viewModelScope.launch {
            repository.uploadAAR(
                UserInfo.POST_MEMBER_ID,
                projectId,
                requestReviewAAR,
            ).onSuccess {
                projectName.value = it.data.projectName
                _isPostSuccess.value = true
                Log.d("write", "postReviewAAR() success!!")
            }.onFailure {
                Log.d("write", "postReviewAAR() Fail:: $it")
            }
        }
    }

    fun getReviewType() {
        viewModelScope.launch {
            repository.getReviewType().onSuccess { response ->
                _reviewTypeList.value = response
                val reviewTypes: List<ReviewType> =
                    _reviewTypeList.value!!
                Log.d("write", "getReviewType() success:: $response")
                _reviewNameList.value = reviewTypes.map {
                    it.reviewTemplateName
                }
                _reviewDescList.value = reviewTypes.map {
                    it.reviewTemplateMeaning
                }
                _templateIdList.value = reviewTypes.map {
                    it.reviewTemplateId
                }
                Log.d("write", "_reviewName success:: ${_reviewNameList.value}")
                Log.d("write", "_reviewDesc success:: ${_reviewDescList.value}")
            }.onFailure {
                Log.d("write", "getReviewType() Fail:: $it")
            }
        }
    }

    fun getPreviousTemplate(projectId: Int) {
        viewModelScope.launch {
            repository.getPreviousTemplate(UserInfo.MEMBER_ID, projectId)
                .onSuccess { response ->
                    _previousReviewType.value = response.data.previousTemplateId
                    when (_previousReviewType.value) {
                        1 -> _reviewTypeText.value = "TIL"
                        2 -> _reviewTypeText.value = "5F"
                        3 -> _reviewTypeText.value = "AAR"
                    }
                    Log.d("write", "getPreviousTemplate() success:: ${_previousReviewType.value}")
                }.onFailure {
                    Log.d("write", "getPreviousTemplate() Fail:: $it")
                }
        }
    }

    companion object {
        const val WRITE_REGEX =
            "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9 \\\\\\\\s!@#\$%^&*()-_=+\\\\[{\\\\]}\\\\|;:'\",.<>/?]*\$"
    }
}

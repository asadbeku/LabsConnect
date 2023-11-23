package uz.project.labsconnect.user.main.profile.container.qa.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.project.labsconnect.user.main.profile.container.types.QAType

class QAViewModel : ViewModel() {

    private val repository = QARepository()

    private var _qaList = MutableLiveData<List<QAType>>()
    val qaList: LiveData<List<QAType>>
        get() = _qaList

    fun getQAList() {
        _qaList.postValue(repository.qaList)
    }

}
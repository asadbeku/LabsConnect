package uz.project.labsconnect.user.main.profile.tab_fragments.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.project.labsconnect.user.main.types.LabsType

class LabsViewModel : ViewModel() {

    private val repository = TabsRepository()

    private var _labs = MutableLiveData<List<LabsType>>()
    val labs: LiveData<List<LabsType>>
        get() = _labs

    fun getLabs() {
        _labs.postValue(repository.labs)
    }

}
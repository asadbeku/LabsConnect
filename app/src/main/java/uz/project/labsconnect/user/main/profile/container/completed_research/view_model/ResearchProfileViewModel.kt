package uz.project.labsconnect.user.main.profile.container.completed_research.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.project.labsconnect.user.main.profile.container.types.ResearchType

class ResearchProfileViewModel : ViewModel() {

    private val repository = ResearchProfileRepository()
    private var _researches = MutableLiveData<List<ResearchType>>()
    val researches: LiveData<List<ResearchType>>
        get() = _researches

    fun getResearchesList() {
        _researches.postValue(repository.researches)
    }
}
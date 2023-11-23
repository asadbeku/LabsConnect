package uz.project.labsconnect.user.main.research.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.project.labsconnect.user.main.types.MyResearchType
import uz.project.labsconnect.user.main.types.SimilarResearchType

class ResearchViewModel : ViewModel() {

    private val repository = ResearchRepository()

    private var _similarResearches = MutableLiveData<List<SimilarResearchType>>()
    private var _getMyResearch = MutableLiveData<MyResearchType?>()
    private var _updatingState = MutableLiveData<Boolean>()
    private var _updatingStateSimilar = MutableLiveData<Boolean>()
    val similarResearches: LiveData<List<SimilarResearchType>>
        get() = _similarResearches
    val updatingState: LiveData<Boolean>
        get() = _updatingState

    val updatingStateSimilar: LiveData<Boolean>
        get() = _updatingStateSimilar
    val getMyResearch: LiveData<MyResearchType?>
        get() = _getMyResearch

    fun getSimilarResearches() {
        _updatingStateSimilar.postValue(true)

        viewModelScope.launch {
            repository.generateSimilarResearches {
                _similarResearches.postValue(it)

                _updatingStateSimilar.postValue(false)
            }
        }

    }

    fun getMyResearch() {
        viewModelScope.launch {

            _updatingState.postValue(true)

            repository.getMyResearchFromNetwork {
                _getMyResearch.postValue(it)
                _updatingState.postValue(false)
            }

        }
    }

}
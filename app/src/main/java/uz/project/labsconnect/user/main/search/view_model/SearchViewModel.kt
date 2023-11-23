package uz.project.labsconnect.user.main.search.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import uz.project.labsconnect.user.main.types.CategoryType
import uz.project.labsconnect.user.main.types.LabsType
import uz.project.labsconnect.user.main.types.TopicType

class SearchViewModel : ViewModel() {

    private val TAG = "SearchViewModel"

    private val repository = SearchRepository()

    private var _category = MutableLiveData<List<CategoryType>>()
    private var _topic = MutableLiveData<List<TopicType>>()
    private var _recommended = MutableLiveData<List<LabsType>>()

    val category: LiveData<List<CategoryType>>
        get() = _category
    val topic: LiveData<List<TopicType>>
        get() = _topic
    val recommended: LiveData<List<LabsType>>
        get() = _recommended

    fun getCategory() {

        viewModelScope.launch {
            repository.getCategory()
                .catch {
                    Log.e(TAG, "getCategory: $it")
                }
                .collect {
                    _category.postValue(it)
                }
        }
    }

    fun getTopic() {
        _topic.postValue(repository.topic)
    }

    fun getRecommended() {
        viewModelScope.launch {

            repository.getRecommendation()
                .catch {
                    Log.e(TAG, "getRecommended: $it")
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    _recommended.postValue(it)
                }
        }
    }

    fun getOrganizationIdByPosition(position: Int): Int {
        return _recommended.value?.get(position)?.id ?: 0
    }

}
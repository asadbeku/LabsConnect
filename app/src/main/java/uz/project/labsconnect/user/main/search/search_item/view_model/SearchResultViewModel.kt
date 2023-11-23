package uz.project.labsconnect.user.main.search.search_item.view_model

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
import kotlinx.coroutines.withContext
import uz.project.labsconnect.user.main.types.EquipmentType
import uz.project.labsconnect.user.main.types.LabsType
import java.lang.Exception

class SearchResultViewModel : ViewModel() {

    private val TAG = "SearchResultViewModel"

    private val repository = SearchResultRepository()

    private var _labs = MutableLiveData<List<LabsType>>()
    private var _equipments = MutableLiveData<List<EquipmentType>>()
    private var _updatingState = MutableLiveData<Boolean>()

    val labs: LiveData<List<LabsType>>
        get() = _labs
    val equipments: LiveData<List<EquipmentType>>
        get() = _equipments
    val updatingState: LiveData<Boolean>
        get() = _updatingState

    fun getAllData() {
        viewModelScope.launch {
            if (repository.isDataEmpty()) {
                repository.getAllLabsFlow()
                    .catch {
                        Log.e(TAG, "getAllData: $it")
                    }
                    .collect {
                        repository.saveLabs(it)
                    }

                repository.getAllEquipmentsFlow()
                    .catch {
                        Log.e(TAG, "getAllData: $it")
                    }
                    .collect {
                        repository.saveEquipments(it)
                    }
            }
        }
    }

    fun searchOrganization(query: String) {
        viewModelScope.launch {

            repository.searchLabs(query)
                .catch {
                    Log.e(TAG, "searchOrganization: $it")
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    _labs.postValue(it)
                }
        }
    }

    fun searchEquipment(query: String) {
        viewModelScope.launch {
            repository.searchEquipment(query)
                .catch {
                    Log.e(TAG, "searchEquipment: $it")
                }
                .collect {
                    Log.d(TAG, "searchEquipment: $it")
                    _equipments.postValue(it)
                }
        }
    }

    fun getOrganizationIdByPosition(position: Int): Int {
        return _labs.value?.get(position)?.id ?: 0
    }

    fun getEquipmentIdByPosition(position: Int): Int {
        return _equipments.value?.get(position)?.id ?: 0
    }
}
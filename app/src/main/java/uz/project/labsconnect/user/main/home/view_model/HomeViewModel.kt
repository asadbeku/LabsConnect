package uz.project.labsconnect.user.main.home.view_model

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
import uz.project.labsconnect.user.main.types.EquipmentType
import uz.project.labsconnect.user.main.types.LabsType
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val homeRepository = HomeRepository()

    private val TAG = "CheckHome"

    private var _category = MutableLiveData<List<CategoryType>>()
    private var _labs = MutableLiveData<List<LabsType>>()
    private var _equipments = MutableLiveData<List<EquipmentType>>()
    private var _ourNumbersLabs = MutableLiveData<Int>()
    private var _ourNumbersEquipments = MutableLiveData<Int>()

    val category: LiveData<List<CategoryType>>
        get() = _category
    val labs: LiveData<List<LabsType>>
        get() = _labs
    val equipments: LiveData<List<EquipmentType>>
        get() = _equipments

    val ourNumbersLabs: LiveData<Int>
        get() = _ourNumbersLabs

    val ourNumbersEquipments: LiveData<Int>
        get() = _ourNumbersEquipments

    fun getCategory() {
        try {
            _category.postValue(homeRepository.category.take(6))
        } catch (e: Exception) {
            Log.e("exceptionCheck", "Exception: $e")
        }


    }

    fun getLabs() {


        viewModelScope.launch {

            homeRepository.getLabsFlow()
                .catch {
                    Log.e(TAG, "getLabs: $it")
                }
                .map {
                    it.take(3)
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    _labs.postValue(it)
                }
        }
    }

    fun getPopularEquipments() {
        viewModelScope.launch {

            homeRepository.getEquipmentsFlow(6)
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.e(TAG, "getPopularEquipments: $it")
                }
                .collect {
                    _equipments.postValue(it)
                }
        }
    }

    fun getOurNumbers() {
        viewModelScope.launch {

            homeRepository.getOurNumbersFlow()
                .catch {
                    Log.e(TAG, "getOurNumbers: $it")
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    _ourNumbersEquipments.postValue(it[1])
                    _ourNumbersLabs.postValue(it[0])
                }
        }
    }

    fun getEquipmentIdByPosition(position: Int): Int {
        return _equipments.value?.get(position)?.id ?: 0
    }

    fun getOrganizationIdByPosition(position: Int): Int {
        return _labs.value?.get(position)?.id ?: 0
    }

}
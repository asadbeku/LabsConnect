package uz.project.labsconnect.user.main.equipment.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.project.labsconnect.user.main.types.EquipmentType

class EquipmentViewModel : ViewModel() {
    private val repository = EquipmentRepository()
    private var _equipmentData = MutableLiveData<EquipmentType>()
    val equipmentData: LiveData<EquipmentType>
        get() = _equipmentData

    fun getEquipmentData(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getEquipment(id) {
                    _equipmentData.postValue(it)
                }
            }
        }
    }

}
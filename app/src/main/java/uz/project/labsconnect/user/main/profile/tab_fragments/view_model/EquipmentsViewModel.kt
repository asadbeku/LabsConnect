package uz.project.labsconnect.user.main.profile.tab_fragments.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.project.labsconnect.user.main.types.EquipmentType

class EquipmentsViewModel : ViewModel() {

    private val repository = TabsRepository()

    private var _equipments = MutableLiveData<List<EquipmentType>>()
    val equipments: LiveData<List<EquipmentType>>
        get() = _equipments

    fun getEquipments() {
        _equipments.postValue(repository.equipments)
    }

}
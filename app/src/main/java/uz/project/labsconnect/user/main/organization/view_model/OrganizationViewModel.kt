package uz.project.labsconnect.user.main.organization.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.convertlist.types.convert.Institution
import com.example.myapplication.types.org_type.OrganizationType
import kotlinx.coroutines.launch
import uz.project.labsconnect.user.main.profile.container.types.QAType
import uz.project.labsconnect.user.main.types.EquipmentType

class OrganizationViewModel : ViewModel() {

    private val repository = OrganizationRepository()

    private var _organizationInfo = MutableLiveData<Institution>()
    private var _equipmentsList = MutableLiveData<List<EquipmentType>>()
    private var _updatingState = MutableLiveData<Boolean>()
    private var _qaList = MutableLiveData<List<QAType>>()

    val organizationInfo: LiveData<Institution>
        get() = _organizationInfo
    val equipmentList: LiveData<List<EquipmentType>>
        get() = _equipmentsList
    val qaList: LiveData<List<QAType>>
        get() = _qaList
    val updatingState: LiveData<Boolean>
        get() = _updatingState

    fun getOrganizationInfo(id: Int) {
        viewModelScope.launch {
            repository.getOrganizationById(id) {
                _organizationInfo.postValue(it)
            }

            getOrganizationEquipments(id)
        }
    }

    private fun getOrganizationEquipments(id: Int) {
        viewModelScope.launch {
            repository.getOrgEquipments(id) {
                _equipmentsList.postValue(it)
            }
        }
    }

    fun getQAList(id: Int) {
        viewModelScope.launch {
            _qaList.postValue(repository.qaList)
        }
    }

}
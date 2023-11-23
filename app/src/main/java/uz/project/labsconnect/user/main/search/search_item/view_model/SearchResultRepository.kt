package uz.project.labsconnect.user.main.search.search_item.view_model

import android.util.Log
import com.example.convertlist.types.convert.equipment.EquipmentResult
import com.example.myapplication.types.list.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import uz.project.labsconnect.user.main.home.view_model.HomeRepository
import uz.project.labsconnect.user.main.types.EquipmentType
import uz.project.labsconnect.user.main.types.LabsType

class SearchResultRepository {
    private val mainRepository = HomeRepository()
    private var labs = mutableListOf<LabsType>()
    private var equipments = mutableListOf<EquipmentType>()

    suspend fun getAllLabsFlow(): Flow<List<LabsType>> {
        return mainRepository.getLabsFlow()
    }

    suspend fun getAllEquipmentsFlow(): Flow<List<EquipmentType>> {
        return mainRepository.getEquipmentsFlow(5000)
    }

    fun isDataEmpty(): Boolean {
        return labs.isEmpty() && equipments.isEmpty()
    }

    fun saveLabs(list: List<LabsType>) {
        labs.addAll(list)
    }

    fun saveEquipments(list: List<EquipmentType>) {
        equipments.addAll(list)
    }

    fun convertEquipment(list: List<EquipmentResult>): List<EquipmentType> {
        return mainRepository.convertEquipmentType(list)
    }

    fun convertResultToLabsType(item: Result): LabsType {
        return mainRepository.convertInstitutionShortToLabsType(item)
    }


    fun searchLabs(query: String): Flow<List<LabsType>> = flow {
        emit(labs.filter { it.name.contains(query, ignoreCase = true) })
    }

    fun searchEquipment(query: String): Flow<List<EquipmentType>> = flow {
        emit(equipments.filter { it.equipment_name.contains(query, ignoreCase = true) })
    }

}
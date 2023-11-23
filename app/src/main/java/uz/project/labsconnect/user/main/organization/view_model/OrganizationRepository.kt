package uz.project.labsconnect.user.main.organization.view_model

import android.util.Log
import com.example.convertlist.types.convert.Institution
import com.example.convertlist.types.convert.equipment.EquipmentResult
import com.example.myapplication.types.org_type.OrganizationType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.project.labsconnect.data_type.EquipmentByOrganizationType
import uz.project.labsconnect.data_type.ResultX
import uz.project.labsconnect.network.GetOrgList
import uz.project.labsconnect.network.Network
import uz.project.labsconnect.user.main.profile.container.types.QAType
import uz.project.labsconnect.user.main.types.EquipmentType
import uz.project.labsconnect.user.main.types.LabsType

class OrganizationRepository {

    val qaList = listOf(
        QAType(
            1,
            "Umarov Umar Umaraliyevich",
            "https://i.ibb.co/PYbmsN4/ASADBEK.jpg",
            "This is incredible app for researchers. Thank yuo for developers!",
            "1699363550631",
            "1",
            "Thank you for greater question!",
            "1699353550631",
            "National University of Uzbekistan"
        ),
        QAType(
            2,
            "Umarov Asadbek Nemataliyevich",
            "https://i.ibb.co/PYbmsN4/ASADBEK.jpg",
            "Hello question",
            "1699363550631",
            "1",
            "Thank you for greater question!",
            "1699353550631",
            "National University of Uzbekistan"
        ),
        QAType(
            3,
            "Umarov Umar Umaraliyevich",
            "https://i.ibb.co/PYbmsN4/ASADBEK.jpg",
            "This is incredible app for researchers. Thank yuo for developers!",
            "1699363550631",
            "0",
            null,
            null,
            "National University of Uzbekistan"
        )
    )

    suspend fun getOrganizationById(id: Int, response: (info: Institution) -> Unit) {
        withContext(Dispatchers.IO) {
            callOrganizationFromNetwork(id)?.let {
                Log.d("checkInfo","Info: $it")
                response(it)
            }
        }
    }

    suspend fun getOrgEquipments(id: Int, response: (info: List<EquipmentType>) -> Unit) {
        withContext(Dispatchers.IO) {
            val listEquipments = callEquipmentsFromNetwork(id)?.results

            listEquipments?.let {
                response(convertEquipmentType(it))
            }
        }
    }

    private fun callOrganizationFromNetwork(id: Int): Institution? {
        val response = Network.buildService(GetOrgList::class.java).getInstitutionById(id).execute()

        return when (response.code()) {
            200, 201 -> {
                Log.d("checkNetwork", "Response body: ${response.body()}")
                response.body()
            }
            400, 401 -> {
                Log.d("checkNetwork", "Response code 400 or 401")
                null
            }

            else -> {
                Log.d("checkNetwork", "Unable error")
                null
            }
        }
    }

    private fun callEquipmentsFromNetwork(id: Int): EquipmentByOrganizationType? {
        val response =
            Network.buildService(GetOrgList::class.java).getEquipmentByOrganizationId(id, 20)
                .execute()

        return when (response.code()) {
            200, 201 -> response.body()
            400, 401 -> {
                Log.d("checkNetwork", "Response code 400 or 401")
                null
            }

            else -> {
                Log.d("checkNetwork", "Unable error")
                null
            }
        }
    }

    private fun convertEquipmentType(
        equipmentList: List<ResultX>
    ): List<EquipmentType> {
        return equipmentList.map { equipmentType ->
            EquipmentType(
                id = equipmentType.id,
                equipment_name = equipmentType.equipment_name,
                organisation_name = equipmentType.organisation_name,
                organizationId = equipmentType.organisation,
                equipment_image = "",
                typeOrganization = true,
                quantity = equipmentType.quantity,
                designed = equipmentType.manufacture_country.toString(),
                purchases_prices = equipmentType.purchase_price.toString(),
                manufacture_country = equipmentType.manufacture_country.toString(),
                manufacture_year = "${equipmentType.manufacture_year}",
                expiration_year = equipmentType.expiration_year.toString(),
                resources = listOf(),
                description = equipmentType.description.toString(),
                createdDate = equipmentType.created_date.toString()
            )
        }
    }

}
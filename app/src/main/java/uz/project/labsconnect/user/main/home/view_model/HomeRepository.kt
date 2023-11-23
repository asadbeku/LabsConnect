package uz.project.labsconnect.user.main.home.view_model

import android.util.Log
import com.example.convertlist.types.convert.Institution
import com.example.convertlist.types.convert.equipment.EquipmentResult
import com.example.myapplication.types.list.Result
import com.google.common.reflect.TypeToken
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import uz.project.labsconnect.network.GetOrgList
import uz.project.labsconnect.network.Network
import uz.project.labsconnect.user.main.types.CategoryType
import uz.project.labsconnect.user.main.types.EquipmentResources
import uz.project.labsconnect.user.main.types.EquipmentType
import uz.project.labsconnect.user.main.types.LabResources
import uz.project.labsconnect.user.main.types.LabsType
import uz.project.labsconnect.user.main.types.ResearchType
import uz.project.labsconnect.user.main.types.UserQuestion
import uz.project.labsconnect.user.main.types.UserType
import java.lang.reflect.Type

class HomeRepository {

    private val TAG = "HomeRepository"

    private val labsIds: IntArray = intArrayOf(
        190,
        7,
        184,
        282,
        232,
        111,
        91,
        193,
        160,
        105,
        58,
        6,
        96,
        102,
        92,
        112,
        137,
        113,
        9,
        73,
        101,
        5,
        255,
        178,
        199,
        194,
        260,
        76,
        97,
        213,
        134,
        19,
        11,
        70,
        99,
        231,
        53,
        100,
        155,
        242,
        287,
        117,
        103,
        285,
        164,
        281,
        158,
        107,
        77,
        249,
        148,
        78,
        283,
        154,
        284,
        41,
        80,
        35,
        29,
        89,
        20,
        25,
        12,
        262,
        175,
        34,
        233,
        54,
        52,
        67,
        138,
        267,
        270,
        286,
        209,
        234,
        36,
        272,
        82,
        294,
        15,
        51,
        30,
        291,
        256,
        114,
        243,
        48,
        290,
        207,
        26,
        142,
        268,
        189
    )

    val category = listOf(
        CategoryType("1", "Biology Laboratory", ""),
        CategoryType("1", "Computer Laboratory", ""),
        CategoryType("1", "Environmental Laboratory", ""),
        CategoryType("1", "Forensic Laboratory", ""),
        CategoryType("1", "Chemistry Laboratory", ""),
        CategoryType("1", "Space Laboratory", ""),
        CategoryType("1", "Medical Laboratory", ""),
        CategoryType("1", "Microbiology Laboratory", ""),
        CategoryType("1", "Astronomy Observatory", ""),
        CategoryType("1", "Geology Laboratory", ""),
        CategoryType("1", "Engineering Laboratory", ""),
        CategoryType("1", "Robotics Laboratory", ""),
        CategoryType("1", "Nanotechnology Laboratory", ""),
        CategoryType("1", "Food Science Laboratory", ""),
        CategoryType("1", "Materials Science Laboratory", ""),
        CategoryType("1", "Biotechnology Laboratory", ""),
        CategoryType("1", "Physics Laboratory", ""),
        CategoryType("1", "Pharmaceutical Laboratory", ""),
        CategoryType("1", "Psychology Laboratory", ""),
        CategoryType("1", "Social Science Laboratory", ""),
        CategoryType("1", "Clean Energy Laboratory", ""),
        CategoryType("1", "Neuroscience Laboratory", "")
    )
    val db = Firebase.firestore
    suspend fun sendDataFlow(json: String) {

        val gson = Gson()
        val listType: Type = object : TypeToken<List<EquipmentResources>>() {}.type
        val labsList: List<EquipmentResources> = gson.fromJson(json, listType)

        val labs = userQuestionListToHashMapList(labsList)

        labs.forEach {
            db.collection("EquipmentResources")
                .add(it)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error adding document", e)
                }
        }
    }

    private fun userQuestionListToHashMapList(userQuestionList: List<EquipmentResources>): List<HashMap<String, Any>> {
        return userQuestionList.map { userQuestion ->
            hashMapOf(
                "id" to userQuestion.id,
                "resourceLink" to userQuestion.resourceLink,
                "size" to userQuestion.size
            )
        }
    }

    suspend fun getLabsFlow(): Flow<List<LabsType>> = flow {
        try {
            val result = db.collection("labs")
                .limit(3)
                .get()
                .await()

            val labsList = result.documents.map { document ->
                val labData = document.data

                Log.d(TAG, "getLabsFlow: $labData")

                LabsType(
                    id = (labData?.get("id") as Long).toInt(),
                    name = labData["name"] as String,
                    likes = (labData["likes"] as Long).toInt(),
                    shared = (labData["shared"] as Long).toInt(),
                    payment = labData["payment"] as String,
                    description = labData["description"] as String,
                    resources = labData["resources"] as? List<String> ?: emptyList(),
                    orgChancelor = labData["orgChancelor"] as String,
                    orgEmail = labData["orgEmail"] as String,
                    orgTel = labData["orgTel"] as String,
                    orgAddress = labData["orgAddress"] as String,
                    orgWebSite = labData["orgWebSite"] as String,
                    orgType = labData["orgType"] as Boolean,
                    orgLogo = labData["orgLogo"] as String,
                    orgPhoto = labData["orgPhoto"] as String,
                    postedQuestions = labData["postedQuestions"] as? List<Int> ?: emptyList(),
                    equipmentList = labData["equipmentList"] as? List<Int> ?: emptyList()
                )
            }

            emit(labsList)
        } catch (exception: Exception) {
            Log.w(TAG, "Error getting documents.", exception)
        }
    }

    suspend fun getEquipmentsFlow(count: Int) =
        flow {

            val result = db.collection("Equipments")
                .limit(count.toLong())
                .get()
                .await()

            val equipmentsList = result.documents.map {
                val data = it.data

                Log.d(TAG, "getEquipmentsFlow: $data")

                EquipmentType(
                    equipment_name = data?.get("equipment_name") as String,
                    quantity = (data["quantity"] as Long).toInt(),
                    manufacture_country = data["manufacture_country"] as String,
                    resources = (data["resources"] as List<*>).filterIsInstance<Long>()
                        .map { it.toInt() },
                    description = data["description"] as String,
                    organisation_name = data["organisation_name"] as String,
                    organizationId = (data["organizationId"] as Long).toInt(),
                    purchases_prices = data["purchases_prices"] as String,
                    expiration_year = (data["expiration_year"] as String),
                    createdDate = data["createdDate"] as String,
                    manufacture_year = (data["manufacture_year"] as String),
                    typeOrganization = data["typeOrganization"] as Boolean,
                    id = (data["id"] as Long).toInt(),
                    designed = data["designed"] as String,
                    equipment_image = data["equipment_image"] as String
                )

            }

            emit(equipmentsList)
        }

    suspend fun getOurNumbersFlow() = flow {
        val labsCount =
            Network.buildService(GetOrgList::class.java).getInstitutions(10000, "uz").count
        val equipmentCount = getEquipmentById(null, 1)?.count

        emit(listOf(labsCount, equipmentCount))
    }

    private fun getEquipmentById(
        id: Int?,
        count: Int
    ): com.example.convertlist.types.convert.equipment.EquipmentType? {
        val response =
            Network.buildService(GetOrgList::class.java).getOrgEquipmentById(id, count).execute()

        return when (response.code()) {
            200, 201 -> response.body()
            400, 401 -> {
                Log.d("checkTag", response.errorBody().toString())
                null
            }

            else -> {
                Log.d("checkTag", "Unidentified error")
                null
            }

        }
    }

    fun convertInstitutionShortToLabsType(lab: Result): LabsType {
        return LabsType(
            id = lab.id,
            name = lab.name_uz,
            likes = 0, // You might need to replace this with actual data
            shared = 0, // You might need to replace this with actual data
            payment = "", // You might need to replace this with actual data
            description = "",
            resources = listOf(), // You might need to replace this with actual data
            orgChancelor = "",
            orgEmail = "",
            orgTel = "",
            orgAddress = "",
            orgWebSite = "",
            orgType = true,
            orgLogo = lab.logo.toString()
                ?: "", // You might need to replace this with actual data
            orgPhoto = "",
            postedQuestions = listOf(), // You might need to replace this with actual data
            equipmentList = listOf()
        )
    }

    private fun convertInstitutionToLabsType(org: Institution): LabsType {
        return LabsType(
            id = org.id,
            name = org.name,
            likes = 0, // You might need to replace this with actual data
            shared = 0, // You might need to replace this with actual data
            payment = "", // You might need to replace this with actual data
            description = org.description,
            resources = listOf(), // You might need to replace this with actual data
            orgChancelor = org.chief_name,
            orgEmail = org.contact_email ?: "",
            orgTel = org.contact_phone ?: "",
            orgAddress = org.address_line,
            orgWebSite = org.web ?: "",
            orgType = org.disabled,
            orgLogo = org.logo.toString()
                ?: "", // You might need to replace this with actual data
            orgPhoto = org.photo.toString() ?: "",
            postedQuestions = listOf(), // You might need to replace this with actual data
            equipmentList = listOf()
        )
    }

    fun convertEquipmentType(
        equipmentList: List<EquipmentResult>
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
                manufacture_year = equipmentType.manufacture_year.toString(),
                expiration_year = equipmentType.expiration_year.toString(),
                resources = listOf(),
                description = equipmentType.description.toString(),
                createdDate = equipmentType.created_date.toString()
            )
        }
    }
}


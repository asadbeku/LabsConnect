package uz.project.labsconnect.network

import com.example.convertlist.types.convert.Institution
import com.example.myapplication.types.list.OrganizationList
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.project.labsconnect.data_type.EquipmentByOrganizationType
import uz.project.labsconnect.user.main.equipment.type.EquipmentByIdType

interface GetOrgList {
    @GET("/api/v1/public/organisation/")
    suspend fun getInstitutions(
        @Query("page_size") pageSize: Int = 10000,
        @Query("lang") lang: String
    ): OrganizationList

    @GET("/api/v1/organisation/organisation/{id}/")
    fun getInstitutionById(
        @Path("id") id: Int
    ): Call<Institution>

    @GET("/api/v1/organisation/organisation/{id}/")
    fun getInstitutionRetrofitById(
        @Path("id") id: Int
    ): Call<Institution>

    @GET("/api/v1/organisation/equipment/")
    fun getOrgEquipmentById(
        @Query("organisation") id: Int?,
        @Query("page_size") pageSize: Int,
        @Query("lang") language: String = "uz",
        @Query("ordering") ordering: String? = null
    ): Call<com.example.convertlist.types.convert.equipment.EquipmentType>

    @GET("/api/v1/organisation/equipment/?organisation=160&page_size=10000")
    fun getEquipmentByOrganizationId(
        @Query("organisation") id: Int,
        @Query("page_size") pageSize: Int = 50
    ): Call<EquipmentByOrganizationType>

    @GET("/api/v1/organisation/equipment/{id}/")
    fun getEquipmentById(
        @Path("id") id: Int
    ): Call<EquipmentByIdType>

    @GET("/api/v1/public/organisation/")
    suspend fun getLabsFlow(
        @Query("page_size") pageSize: Int = 10000,
        @Query("lang") lang: String
    ): OrganizationList

    @GET("/api/v1/organisation/equipment/")
    suspend fun getEquipmentsFlow(
        @Query("organisation") id: Int?,
        @Query("page_size") pageSize: Int,
        @Query("lang") language: String = "uz",
        @Query("ordering") ordering: String? = null
    ): com.example.convertlist.types.convert.equipment.EquipmentType
}
package com.example.myapplication.types.list

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("equipment_count")
    val equipment_count: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("institution")
    val institution: Int,
    @SerializedName("institution_name_ru")
    val institution_name_ru: String,
    @SerializedName("institution_name_uz")
    val institution_name_uz: String,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("name_ru")
    val name_ru: String,
    @SerializedName("name_uz")
    val name_uz: String,
    @SerializedName("organization_type")
    val organization_type: Int,
    @SerializedName("organization_type_name_ru")
    val organization_type_name_ru: String,
    @SerializedName("organization_type_name_uz")
    val organization_type_name_uz: String
)
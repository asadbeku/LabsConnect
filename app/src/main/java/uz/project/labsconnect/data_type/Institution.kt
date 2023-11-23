package com.example.convertlist.types.convert

import com.example.myapplication.types.org_type.ScienceFieldName

data class Institution(
    val address_line: String,
    val chief_email: Any,
    val chief_name: String,
    val chief_phone: Any,
    val contact_email: String?,
    val contact_name: String,
    val contact_phone: String?,
    val created_at: String,
    val created_by: Int,
    val created_date: String,
    val description: String,
    val description_ru: String,
    val description_uz: String,
    val disabled: Boolean,
    val geolocation: Any,
    val id: Int,
    val institution: Int,
    val institution_name: String,
    val level: Int,
    val lft: Int,
    val logo: String?,
    val name: String,
    val name_ru: String,
    val name_uz: String,
    val organization_type: Int,
    val organization_type_name: String,
    val parent: Any,
    val photo: String?,
    val purpose: Any,
    val purpose_ru: Any,
    val purpose_uz: Any,
    val region: Int,
    val region_name: String,
    val rght: Int,
    val science_field: List<Int>,
    val science_field_name: List<ScienceFieldName>,
    val short_name: String,
    val short_name_ru: String,
    val short_name_uz: String,
    val since_year: Int,
    val tasks_description: Any,
    val tasks_description_ru: Any,
    val tasks_description_uz: Any,
    val tree_id: Int,
    val updated_at: String,
    val updated_by: Any,
    val updated_date: String,
    val web: String?,
    val zip_code: String
)
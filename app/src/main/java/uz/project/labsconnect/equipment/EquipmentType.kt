package com.example.convertlist.types.convert.equipment

data class EquipmentType(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<EquipmentResult>
)
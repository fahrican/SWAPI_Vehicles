package com.example.swapivehicles.model

data class VehiclesResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Vehicle>
)
package com.eltescode.rpgsession.features.magic.domain.model

data class Magic(
    val magiName: String,
    val castingNumbers: Int,
    val castingTimeIn: String,
    val ingredient: String,
    val duration: String,
    val description: String
)
package com.eltescode.rpgsession.features.proffesion.domain

interface ProfessionRepository {

    suspend fun getProfessions(): String
}
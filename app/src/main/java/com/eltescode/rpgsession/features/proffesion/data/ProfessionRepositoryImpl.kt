package com.eltescode.rpgsession.features.proffesion.data

import com.eltescode.rpgsession.features.proffesion.domain.ProfessionRepository

class ProfessionRepositoryImpl : ProfessionRepository {
    override suspend fun getProfessions(): String {
        return "Łotrzyk"
    }
}
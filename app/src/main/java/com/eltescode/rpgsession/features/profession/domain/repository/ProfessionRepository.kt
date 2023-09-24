package com.eltescode.rpgsession.features.profession.domain.repository

import com.eltescode.rpgsession.features.profession.domain.model.CareerDomain
import kotlinx.coroutines.flow.StateFlow

interface ProfessionRepository {

    suspend fun getCareers(): StateFlow<List<CareerDomain>>

    suspend fun addSingleCareer(career: CareerDomain)
}
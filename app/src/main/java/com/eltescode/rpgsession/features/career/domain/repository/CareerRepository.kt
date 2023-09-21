package com.eltescode.rpgsession.features.career.domain.repository

import com.eltescode.rpgsession.features.career.domain.model.CareerDomain
import kotlinx.coroutines.flow.StateFlow

interface CareerRepository {

    suspend fun getCareers(): StateFlow<List<CareerDomain>>

    suspend fun addSingleCareer(career: CareerDomain)
}
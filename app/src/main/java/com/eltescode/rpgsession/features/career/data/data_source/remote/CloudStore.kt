package com.eltescode.rpgsession.features.career.data.data_source.remote

import com.eltescode.rpgsession.features.career.domain.model.CareerDomain
import com.eltescode.rpgsession.features.career.domain.model.CareerDomainTest
import kotlinx.coroutines.flow.StateFlow

interface CloudStore {

    suspend fun addSingleCareer(career: CareerDomain)
    suspend fun getAllCareers(): StateFlow<List<CareerDomain>>
    suspend fun addSingleCareerTest(career: CareerDomainTest)
}
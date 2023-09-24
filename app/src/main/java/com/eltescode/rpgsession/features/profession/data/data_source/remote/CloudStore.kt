package com.eltescode.rpgsession.features.profession.data.data_source.remote

import com.eltescode.rpgsession.features.profession.domain.model.CareerDomain
import com.eltescode.rpgsession.features.profession.domain.model.CareerDomainTest
import kotlinx.coroutines.flow.StateFlow

interface CloudStore {

    suspend fun addSingleCareer(career: CareerDomain)
    suspend fun getAllCareers(): StateFlow<List<CareerDomain>>
    suspend fun addSingleCareerTest(career: CareerDomainTest)
}
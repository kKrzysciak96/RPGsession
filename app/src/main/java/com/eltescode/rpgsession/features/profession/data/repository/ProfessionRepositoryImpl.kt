package com.eltescode.rpgsession.features.profession.data.repository

import com.eltescode.rpgsession.features.profession.data.data_source.remote.CloudStore
import com.eltescode.rpgsession.features.profession.domain.repository.ProfessionRepository
import com.eltescode.rpgsession.features.profession.domain.model.CareerDomain
import com.eltescode.rpgsession.features.profession.domain.model.CareerDomainTest
import com.eltescode.rpgsession.features.profession.domain.model.MainProfileDomain
import com.eltescode.rpgsession.features.profession.domain.model.SecondaryProfileDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfessionRepositoryImpl(private val cloud: CloudStore) : ProfessionRepository {
    override suspend fun getCareers(): StateFlow<List<CareerDomain>> {
        return cloud.getAllCareers()
    }

    override suspend fun addSingleCareer(career: CareerDomain) {
        cloud.addSingleCareer(career)
    }

    init {
        val scope = CoroutineScope(Job())
        scope.launch {
            addSingleCareer(
                CareerDomain(
                    careerName = "Lotrzyk4",
                    mainProfile = MainProfileDomain(1, 1, 1, 1, 1, 1, 1, 1),
                    secondaryProfile = SecondaryProfileDomain(1, 1, 1, 1, 1, 1, 1, 1),
                    skills = emptyList(),
                    talents = emptyList(),
                    trappings = emptyList(),
                    careerEntries = emptyList(),
                    careerExits = emptyList(),
                )
            )

            scope.launch {
                cloud.addSingleCareerTest(
                    CareerDomainTest("TEST2")
                )
            }

        }
    }
}
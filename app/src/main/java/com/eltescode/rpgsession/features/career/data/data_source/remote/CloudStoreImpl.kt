package com.eltescode.rpgsession.features.career.data.data_source.remote

import com.eltescode.rpgsession.features.career.domain.model.CareerDomain
import com.eltescode.rpgsession.features.career.domain.model.CareerDomainTest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*


class CloudStoreImpl(private val cloud: FirebaseFirestore) : CloudStore {

    override suspend fun addSingleCareer(career: CareerDomain) {
        cloud.collection("careers")
            .document(career.careerName.lowercase(Locale.ROOT))
            .set(career)
    }

    override suspend fun addSingleCareerTest(career: CareerDomainTest) {
        cloud.collection("careersTest")
            .document(career.careerName.lowercase(Locale.ROOT))
            .set(career)
    }

    override suspend fun getAllCareers(): StateFlow<List<CareerDomain>> {
        val careers = MutableStateFlow<List<CareerDomain>>(emptyList())
        cloud.collection("careers")
            .get()
            .addOnSuccessListener {
                val career = it.toObjects(CareerDomain::class.java)
                careers.value = career
            }
            .addOnFailureListener {

            }

        return careers

    }
}
package com.eltescode.rpgsession.features.admin_user.data.repository

import com.eltescode.rpgsession.features.admin_user.domain.repository.AdminUserRepository
import com.eltescode.rpgsession.features.profession.domain.model.Profession
import com.eltescode.rpgsession.features.skill.domain.model.Skill
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class AdminUserRepositoryImpl(
    private val cloud: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
) : AdminUserRepository {

    override suspend fun createNewSkill(skill: Skill) {
        cloud.collection("data")
            .document("book")
            .collection("skills")
            .document(skill.skillName)
            .set(skill)
    }

    override suspend fun createNewProfession(profession: Profession) {
        cloud.collection("data")
            .document("book")
            .collection("professions")
            .document(profession.professionName)
            .set(profession)
    }

}


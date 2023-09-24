package com.eltescode.rpgsession.core.di

import com.eltescode.rpgsession.features.admin_user.data.repository.AdminUserRepositoryImpl
import com.eltescode.rpgsession.features.admin_user.domain.repository.AdminUserRepository
import com.eltescode.rpgsession.features.admin_user.domain.use_case.AdminUseCases
import com.eltescode.rpgsession.features.admin_user.domain.use_case.CreateNewProfessionUseCase
import com.eltescode.rpgsession.features.admin_user.domain.use_case.CreateNewSkillUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AdminModule {

    @Provides
    @Singleton
    fun provideAdminUserRepository(
        cloud: FirebaseFirestore,
        auth: FirebaseAuth,
        storage: FirebaseStorage
    ): AdminUserRepository {
        return AdminUserRepositoryImpl(cloud = cloud, auth = auth, storage = storage)
    }

    @Provides
    @Singleton
    fun providesAdminUseCases(repository: AdminUserRepository): AdminUseCases {
        return AdminUseCases(
            createNewSkillUseCase = CreateNewSkillUseCase(repository),
            createNewProfessionUseCase = CreateNewProfessionUseCase(repository)
        )
    }
}
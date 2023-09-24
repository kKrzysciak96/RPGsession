package com.eltescode.rpgsession.core.di

import android.content.Context
import androidx.work.WorkManager
import com.eltescode.rpgsession.features.profession.data.data_source.remote.CloudStore
import com.eltescode.rpgsession.features.profession.data.data_source.remote.CloudStoreImpl
import com.eltescode.rpgsession.features.profession.data.repository.ProfessionRepositoryImpl
import com.eltescode.rpgsession.features.profession.domain.repository.ProfessionRepository
import com.eltescode.rpgsession.features.user.data.repository.UserRepositoryImpl
import com.eltescode.rpgsession.features.user.data.utils.EmailCredentials
import com.eltescode.rpgsession.features.user.domain.repository.UserRepository
import com.eltescode.rpgsession.features.user.domain.use_case.*
import com.eltescode.rpgsession.features.user.presentation.utils.Authenticator
import com.eltescode.rpgsession.features.user.presentation.utils.FireBaseEmailAndPasswordAuthenticator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFireBaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideFireBaseStorage(): FirebaseStorage {
        return Firebase.storage
    }

    @Provides
    @Singleton
    fun provideFireBaseFireStore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideCloudStore(cloud: FirebaseFirestore): CloudStore {
        return CloudStoreImpl(cloud)
    }

    @Provides
    @Singleton
    fun provideEmailAuthenticator(auth: FirebaseAuth): Authenticator<EmailCredentials> {
        return FireBaseEmailAndPasswordAuthenticator(auth)
    }

    @Provides
    @Singleton
    fun provideCareerRepository(cloud: CloudStore): ProfessionRepository {
        return ProfessionRepositoryImpl(cloud)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        cloud: FirebaseFirestore,
        auth: FirebaseAuth,
        storage: FirebaseStorage
    ): UserRepository {
        return UserRepositoryImpl(cloud, auth, storage)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getUserDataUseCase = GetUserDataUseCase(repository),
            createNewUserUseCase = CreateNewUserUseCase(repository),
            signInUseCase = SignInUseCase(repository),
            signUpUseCase = SignUpUseCase(repository),
            signOutUseCase = SignOutUseCase(repository),
            getCurrentUserUseCase = GetCurrentUser(repository),
            editUserPersonalDataUseCase = EditUserPersonalDataUseCase(repository),
            uploadUserPhotoUseCase = UploadUserPhotoUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext appContext: Context): WorkManager {
        return WorkManager.getInstance(appContext)
    }
}
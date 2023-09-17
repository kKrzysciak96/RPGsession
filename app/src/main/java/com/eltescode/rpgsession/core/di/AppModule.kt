package com.eltescode.rpgsession.core.di

import com.eltescode.rpgsession.features.proffesion.data.ProfessionRepositoryImpl
import com.eltescode.rpgsession.features.proffesion.domain.ProfessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProfessionRepository(): ProfessionRepository {
        return ProfessionRepositoryImpl()
    }
}
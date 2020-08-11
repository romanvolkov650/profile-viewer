package com.romanvolkov.profileviewer.dagger

import com.romanvolkov.profileviewer.model.api.ProfileApi
import com.romanvolkov.profileviewer.model.repository.IMainRepository
import com.romanvolkov.profileviewer.model.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(api: ProfileApi): IMainRepository {
        return MainRepository(api)
    }
}
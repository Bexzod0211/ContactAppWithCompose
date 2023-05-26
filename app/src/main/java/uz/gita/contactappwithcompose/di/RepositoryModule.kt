package uz.gita.contactappwithcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappwithcompose.domain.repository.AppRepository
import uz.gita.contactappwithcompose.domain.repository.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideRepository(impl:AppRepositoryImpl):AppRepository



}
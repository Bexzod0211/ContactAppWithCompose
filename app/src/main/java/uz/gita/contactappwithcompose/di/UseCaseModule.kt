package uz.gita.contactappwithcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.contactappwithcompose.ui.usecase.HomeUseCase
import uz.gita.contactappwithcompose.ui.usecase.impl.HomeUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindHomeUseCase(impl:HomeUseCaseImpl):HomeUseCase
}
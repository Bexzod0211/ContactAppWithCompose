package uz.gita.contactappwithcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappwithcompose.ui.directions.AddContactDirection
import uz.gita.contactappwithcompose.ui.directions.HomeDirection
import uz.gita.contactappwithcompose.ui.viewmodels.AddContactContract
import uz.gita.contactappwithcompose.ui.viewmodels.HomeContract

@Module
@InstallIn(SingletonComponent::class)
interface DirectionsModule {

    @Binds
    fun bindHomeDirection(impl:HomeDirection):HomeContract.Direction

    @Binds
    fun bindAddContactDirection(impl:AddContactDirection):AddContactContract.Direction
}
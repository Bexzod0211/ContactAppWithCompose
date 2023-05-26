package uz.gita.contactappwithcompose.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappwithcompose.data.source.local.database.ContactDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):ContactDatabase = Room.databaseBuilder(context,ContactDatabase::class.java,"Contact.db")
        .allowMainThreadQueries()
        .build()


}
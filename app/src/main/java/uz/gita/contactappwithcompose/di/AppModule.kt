package uz.gita.contactappwithcompose.di

import android.content.Context
import androidx.room.Room
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.contactappwithcompose.data.Api
import uz.gita.contactappwithcompose.data.source.local.database.ContactDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule : Initializer<WorkManager> {
    private val base_url = "http://b9e1-185-139-137-21.ngrok-free.app"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):ContactDatabase = Room.databaseBuilder(context,ContactDatabase::class.java,"Contact.db")
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient):Retrofit = Retrofit
        .Builder()
        .baseUrl(base_url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideClient(@ApplicationContext context: Context):OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit):Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    override fun create(@ApplicationContext context: Context): WorkManager {
        val configuration = Configuration.Builder().build()
        WorkManager.initialize(context, configuration)
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
package com.seekho.animepilot.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seekho.animepilot.core.data.api.JikanApi
import com.seekho.animepilot.core.data.api.JikanApiClient
import com.seekho.animepilot.core.data.db.AnimeDatabase
import com.seekho.animepilot.core.data.db.dao.AnimeDao
import com.seekho.animepilot.core.data.db.dao.AnimeDetailDao
import com.seekho.animepilot.core.data.local.LocalDataSource
import com.seekho.animepilot.core.data.local.LocalDataSourceImpl
import com.seekho.animepilot.core.data.remote.RemoteDataSource
import com.seekho.animepilot.core.data.remote.RemoteDataSourceImpl
import com.seekho.animepilot.core.data.repository.AnimeRepositoryImpl
import com.seekho.animepilot.core.domain.repository.AnimeRepository
import com.seekho.animepilot.core.domain.usecase.GetAnimeDetailUseCase
import com.seekho.animepilot.core.util.ConnectivityObserver
import com.seekho.animepilot.core.util.ConnectivityObserverImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import androidx.room.Room
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import javax.inject.Singleton
import kotlin.jvm.JvmSuppressWildcards

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return JikanApiClient.createOkHttpClient()
    }
    
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }
    
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(JikanApiClient.getBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    
    @Provides
    @Singleton
    fun provideJikanApi(retrofit: Retrofit): JikanApi {
        return retrofit.create(JikanApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideAnimeDatabase(application: Application): AnimeDatabase {
        return Room.databaseBuilder(
            application,
            AnimeDatabase::class.java,
            "anime_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideStringListTypeConverter(gson: Gson): com.seekho.animepilot.core.data.db.converter.StringListTypeConverter {
        // Room TypeConverters don't support constructor injection, so we use a provider pattern
        com.seekho.animepilot.core.data.db.converter.StringListTypeConverter.setGson(gson)
        // Initialize EntityMapper with Gson as well
        com.seekho.animepilot.core.data.mapper.EntityMapper.setGson(gson)
        return com.seekho.animepilot.core.data.db.converter.StringListTypeConverter()
    }
    
    @Provides
    @Singleton
    fun provideAnimeDao(database: AnimeDatabase): AnimeDao {
        return database.animeDao()
    }
    
    @Provides
    @Singleton
    fun provideAnimeDetailDao(database: AnimeDatabase): AnimeDetailDao {
        return database.animeDetailDao()
    }
    
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun provideAnimeRemoteMediatorFactory(
        jikanApi: JikanApi,
        database: AnimeDatabase
    ): @JvmSuppressWildcards () -> RemoteMediator<Int, AnimeEntity> {
        return { com.seekho.animepilot.core.data.paging.AnimeRemoteMediator(jikanApi, database) }
    }
    
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun provideAnimeDaoFactory(
        database: AnimeDatabase
    ): @JvmSuppressWildcards () -> PagingSource<Int, AnimeEntity> {
        return { database.animeDao().getAllAnimePaged() }
    }
    
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    
    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
    
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource
    
    @Binds
    @Singleton
    abstract fun bindConnectivityObserver(
        connectivityObserverImpl: ConnectivityObserverImpl
    ): ConnectivityObserver
    
    @Binds
    @Singleton
    abstract fun bindAnimeRepository(
        animeRepositoryImpl: AnimeRepositoryImpl
    ): AnimeRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    
    @Provides
    fun provideGetAnimeDetailUseCase(
        repository: AnimeRepository
    ): GetAnimeDetailUseCase {
        return GetAnimeDetailUseCase(repository)
    }
}
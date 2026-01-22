package com.seekho.animepilot.core.data.repository;

import androidx.paging.PagingSource;
import androidx.paging.RemoteMediator;
import com.seekho.animepilot.core.data.db.entity.AnimeEntity;
import com.seekho.animepilot.core.data.local.LocalDataSource;
import com.seekho.animepilot.core.data.remote.RemoteDataSource;
import com.seekho.animepilot.core.util.ConnectivityObserver;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlin.jvm.functions.Function0;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AnimeRepositoryImpl_Factory implements Factory<AnimeRepositoryImpl> {
  private final Provider<RemoteDataSource> remoteDataSourceProvider;

  private final Provider<LocalDataSource> localDataSourceProvider;

  private final Provider<ConnectivityObserver> connectivityObserverProvider;

  private final Provider<Function0<RemoteMediator<Integer, AnimeEntity>>> remoteMediatorFactoryProvider;

  private final Provider<Function0<PagingSource<Integer, AnimeEntity>>> pagingSourceFactoryProvider;

  private AnimeRepositoryImpl_Factory(Provider<RemoteDataSource> remoteDataSourceProvider,
      Provider<LocalDataSource> localDataSourceProvider,
      Provider<ConnectivityObserver> connectivityObserverProvider,
      Provider<Function0<RemoteMediator<Integer, AnimeEntity>>> remoteMediatorFactoryProvider,
      Provider<Function0<PagingSource<Integer, AnimeEntity>>> pagingSourceFactoryProvider) {
    this.remoteDataSourceProvider = remoteDataSourceProvider;
    this.localDataSourceProvider = localDataSourceProvider;
    this.connectivityObserverProvider = connectivityObserverProvider;
    this.remoteMediatorFactoryProvider = remoteMediatorFactoryProvider;
    this.pagingSourceFactoryProvider = pagingSourceFactoryProvider;
  }

  @Override
  public AnimeRepositoryImpl get() {
    return newInstance(remoteDataSourceProvider.get(), localDataSourceProvider.get(), connectivityObserverProvider.get(), remoteMediatorFactoryProvider.get(), pagingSourceFactoryProvider.get());
  }

  public static AnimeRepositoryImpl_Factory create(
      Provider<RemoteDataSource> remoteDataSourceProvider,
      Provider<LocalDataSource> localDataSourceProvider,
      Provider<ConnectivityObserver> connectivityObserverProvider,
      Provider<Function0<RemoteMediator<Integer, AnimeEntity>>> remoteMediatorFactoryProvider,
      Provider<Function0<PagingSource<Integer, AnimeEntity>>> pagingSourceFactoryProvider) {
    return new AnimeRepositoryImpl_Factory(remoteDataSourceProvider, localDataSourceProvider, connectivityObserverProvider, remoteMediatorFactoryProvider, pagingSourceFactoryProvider);
  }

  public static AnimeRepositoryImpl newInstance(RemoteDataSource remoteDataSource,
      LocalDataSource localDataSource, ConnectivityObserver connectivityObserver,
      Function0<RemoteMediator<Integer, AnimeEntity>> remoteMediatorFactory,
      Function0<PagingSource<Integer, AnimeEntity>> pagingSourceFactory) {
    return new AnimeRepositoryImpl(remoteDataSource, localDataSource, connectivityObserver, remoteMediatorFactory, pagingSourceFactory);
  }
}

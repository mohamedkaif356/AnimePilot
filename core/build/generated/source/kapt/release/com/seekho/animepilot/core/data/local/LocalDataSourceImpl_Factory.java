package com.seekho.animepilot.core.data.local;

import com.seekho.animepilot.core.data.db.dao.AnimeDao;
import com.seekho.animepilot.core.data.db.dao.AnimeDetailDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class LocalDataSourceImpl_Factory implements Factory<LocalDataSourceImpl> {
  private final Provider<AnimeDao> animeDaoProvider;

  private final Provider<AnimeDetailDao> animeDetailDaoProvider;

  private LocalDataSourceImpl_Factory(Provider<AnimeDao> animeDaoProvider,
      Provider<AnimeDetailDao> animeDetailDaoProvider) {
    this.animeDaoProvider = animeDaoProvider;
    this.animeDetailDaoProvider = animeDetailDaoProvider;
  }

  @Override
  public LocalDataSourceImpl get() {
    return newInstance(animeDaoProvider.get(), animeDetailDaoProvider.get());
  }

  public static LocalDataSourceImpl_Factory create(Provider<AnimeDao> animeDaoProvider,
      Provider<AnimeDetailDao> animeDetailDaoProvider) {
    return new LocalDataSourceImpl_Factory(animeDaoProvider, animeDetailDaoProvider);
  }

  public static LocalDataSourceImpl newInstance(AnimeDao animeDao, AnimeDetailDao animeDetailDao) {
    return new LocalDataSourceImpl(animeDao, animeDetailDao);
  }
}

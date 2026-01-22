package com.seekho.animepilot.core.data.paging;

import com.seekho.animepilot.core.data.api.JikanApi;
import com.seekho.animepilot.core.data.db.AnimeDatabase;
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
public final class AnimeRemoteMediator_Factory implements Factory<AnimeRemoteMediator> {
  private final Provider<JikanApi> jikanApiProvider;

  private final Provider<AnimeDatabase> databaseProvider;

  private AnimeRemoteMediator_Factory(Provider<JikanApi> jikanApiProvider,
      Provider<AnimeDatabase> databaseProvider) {
    this.jikanApiProvider = jikanApiProvider;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AnimeRemoteMediator get() {
    return newInstance(jikanApiProvider.get(), databaseProvider.get());
  }

  public static AnimeRemoteMediator_Factory create(Provider<JikanApi> jikanApiProvider,
      Provider<AnimeDatabase> databaseProvider) {
    return new AnimeRemoteMediator_Factory(jikanApiProvider, databaseProvider);
  }

  public static AnimeRemoteMediator newInstance(JikanApi jikanApi, AnimeDatabase database) {
    return new AnimeRemoteMediator(jikanApi, database);
  }
}

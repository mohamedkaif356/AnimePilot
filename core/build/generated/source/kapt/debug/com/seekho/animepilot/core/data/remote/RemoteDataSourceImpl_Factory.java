package com.seekho.animepilot.core.data.remote;

import com.seekho.animepilot.core.data.api.JikanApi;
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
public final class RemoteDataSourceImpl_Factory implements Factory<RemoteDataSourceImpl> {
  private final Provider<JikanApi> jikanApiProvider;

  private RemoteDataSourceImpl_Factory(Provider<JikanApi> jikanApiProvider) {
    this.jikanApiProvider = jikanApiProvider;
  }

  @Override
  public RemoteDataSourceImpl get() {
    return newInstance(jikanApiProvider.get());
  }

  public static RemoteDataSourceImpl_Factory create(Provider<JikanApi> jikanApiProvider) {
    return new RemoteDataSourceImpl_Factory(jikanApiProvider);
  }

  public static RemoteDataSourceImpl newInstance(JikanApi jikanApi) {
    return new RemoteDataSourceImpl(jikanApi);
  }
}

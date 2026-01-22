package com.seekho.animepilot.core.domain.usecase;

import com.seekho.animepilot.core.domain.repository.AnimeRepository;
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
public final class GetAnimeDetailUseCase_Factory implements Factory<GetAnimeDetailUseCase> {
  private final Provider<AnimeRepository> repositoryProvider;

  private GetAnimeDetailUseCase_Factory(Provider<AnimeRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetAnimeDetailUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetAnimeDetailUseCase_Factory create(Provider<AnimeRepository> repositoryProvider) {
    return new GetAnimeDetailUseCase_Factory(repositoryProvider);
  }

  public static GetAnimeDetailUseCase newInstance(AnimeRepository repository) {
    return new GetAnimeDetailUseCase(repository);
  }
}

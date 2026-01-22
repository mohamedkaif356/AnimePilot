package com.seekho.animepilot.core.domain.usecase;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0007J\b\u0010\n\u001a\u00020\u000bH\u0002J\f\u0010\f\u001a\u00060\tj\u0002`\rH\u0007J\f\u0010\u000e\u001a\u00060\tj\u0002`\rH\u0007J\f\u0010\u000f\u001a\u00060\tj\u0002`\rH\u0007J\f\u0010\u0010\u001a\u00060\tj\u0002`\rH\u0007J\f\u0010\u0011\u001a\u00060\tj\u0002`\rH\u0007J\f\u0010\u0012\u001a\u00060\tj\u0002`\rH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/seekho/animepilot/core/domain/usecase/GetAnimeDetailUseCaseTest;", "", "<init>", "()V", "repository", "Lcom/seekho/animepilot/core/domain/repository/AnimeRepository;", "useCase", "Lcom/seekho/animepilot/core/domain/usecase/GetAnimeDetailUseCase;", "setup", "", "createTestAnimeDetail", "Lcom/seekho/animepilot/core/domain/model/AnimeDetail;", "invoke with valid ID delegates to repository", "Lkotlinx/coroutines/test/TestResult;", "invoke with ID zero returns error immediately", "invoke with negative ID returns error immediately", "invoke with valid ID does not call repository when ID is invalid", "invoke with valid ID returns Flow from repository", "invoke with large valid ID delegates to repository", "core_releaseUnitTest"})
public final class GetAnimeDetailUseCaseTest {
    private com.seekho.animepilot.core.domain.repository.AnimeRepository repository;
    private com.seekho.animepilot.core.domain.usecase.GetAnimeDetailUseCase useCase;
    
    public GetAnimeDetailUseCaseTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
    
    private final com.seekho.animepilot.core.domain.model.AnimeDetail createTestAnimeDetail() {
        return null;
    }
}
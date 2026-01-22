package com.seekho.animepilot.core.data.repository;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0007J\f\u0010\u000e\u001a\u00060\rj\u0002`\u000fH\u0007J\f\u0010\u0010\u001a\u00060\rj\u0002`\u000fH\u0007J\f\u0010\u0011\u001a\u00060\rj\u0002`\u000fH\u0007J\f\u0010\u0012\u001a\u00060\rj\u0002`\u000fH\u0007J\f\u0010\u0013\u001a\u00060\rj\u0002`\u000fH\u0007J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/seekho/animepilot/core/data/repository/AnimeRepositoryImplTest;", "", "<init>", "()V", "remoteDataSource", "Lcom/seekho/animepilot/core/data/remote/RemoteDataSource;", "localDataSource", "Lcom/seekho/animepilot/core/data/local/LocalDataSource;", "connectivityObserver", "Lcom/seekho/animepilot/core/util/ConnectivityObserver;", "repository", "Lcom/seekho/animepilot/core/data/repository/AnimeRepositoryImpl;", "setup", "", "getAnimeDetail returns cached data first when available", "Lkotlinx/coroutines/test/TestResult;", "getAnimeDetail emits Loading first", "getAnimeDetail returns error when offline and no cache", "getAnimeDetail saves remote data and emits success", "getAnimeDetail does not emit error when cache exists and remote fails", "createAnimeDetailEntity", "Lcom/seekho/animepilot/core/data/db/entity/AnimeDetailEntity;", "id", "", "title", "", "core_releaseUnitTest"})
@kotlin.OptIn(markerClass = {androidx.paging.ExperimentalPagingApi.class})
public final class AnimeRepositoryImplTest {
    private com.seekho.animepilot.core.data.remote.RemoteDataSource remoteDataSource;
    private com.seekho.animepilot.core.data.local.LocalDataSource localDataSource;
    private com.seekho.animepilot.core.util.ConnectivityObserver connectivityObserver;
    private com.seekho.animepilot.core.data.repository.AnimeRepositoryImpl repository;
    
    public AnimeRepositoryImplTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
    
    private final com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity createAnimeDetailEntity(int id, java.lang.String title) {
        return null;
    }
}
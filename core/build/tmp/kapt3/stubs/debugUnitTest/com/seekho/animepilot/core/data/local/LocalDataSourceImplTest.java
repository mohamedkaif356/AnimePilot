package com.seekho.animepilot.core.data.local;

@org.junit.runner.RunWith(value = org.robolectric.RobolectricTestRunner.class)
@org.robolectric.annotation.Config(sdk = {28})
@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0007J\b\u0010\n\u001a\u00020\tH\u0007J\f\u0010\u000b\u001a\u00060\tj\u0002`\fH\u0007J\f\u0010\r\u001a\u00060\tj\u0002`\fH\u0007J\f\u0010\u000e\u001a\u00060\tj\u0002`\fH\u0007J\f\u0010\u000f\u001a\u00060\tj\u0002`\fH\u0007J\f\u0010\u0010\u001a\u00060\tj\u0002`\fH\u0007J\f\u0010\u0011\u001a\u00060\tj\u0002`\fH\u0007J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/seekho/animepilot/core/data/local/LocalDataSourceImplTest;", "", "<init>", "()V", "database", "Lcom/seekho/animepilot/core/data/db/AnimeDatabase;", "localDataSource", "Lcom/seekho/animepilot/core/data/local/LocalDataSourceImpl;", "setup", "", "tearDown", "saveAnimeList and observeAnimeList work correctly", "Lkotlinx/coroutines/test/TestResult;", "clearAnimeList removes all anime", "saveAnimeDetail and getAnimeDetail work correctly", "observeAnimeDetail emits updated data", "getAnimeDetail returns null when not found", "observeAnimeDetail emits null when not found", "createAnimeEntity", "Lcom/seekho/animepilot/core/data/db/entity/AnimeEntity;", "id", "", "title", "", "createAnimeDetailEntity", "Lcom/seekho/animepilot/core/data/db/entity/AnimeDetailEntity;", "core_debugUnitTest"})
public final class LocalDataSourceImplTest {
    private com.seekho.animepilot.core.data.db.AnimeDatabase database;
    private com.seekho.animepilot.core.data.local.LocalDataSourceImpl localDataSource;
    
    public LocalDataSourceImplTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
    
    @org.junit.After()
    public final void tearDown() {
    }
    
    private final com.seekho.animepilot.core.data.db.entity.AnimeEntity createAnimeEntity(int id, java.lang.String title) {
        return null;
    }
    
    private final com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity createAnimeDetailEntity(int id, java.lang.String title) {
        return null;
    }
}
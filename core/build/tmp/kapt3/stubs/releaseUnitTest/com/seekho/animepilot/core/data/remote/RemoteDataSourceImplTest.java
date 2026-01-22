package com.seekho.animepilot.core.data.remote;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0007J\f\u0010\n\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\f\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\r\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\u000e\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\u000f\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\u0010\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\u0011\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\u0012\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\u0013\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\u0014\u001a\u00060\tj\u0002`\u000bH\u0007J\f\u0010\u0015\u001a\u00060\tj\u0002`\u000bH\u0007J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/seekho/animepilot/core/data/remote/RemoteDataSourceImplTest;", "", "<init>", "()V", "jikanApi", "Lcom/seekho/animepilot/core/data/api/JikanApi;", "remoteDataSource", "Lcom/seekho/animepilot/core/data/remote/RemoteDataSourceImpl;", "setup", "", "fetchTopAnime returns success when API returns data", "Lkotlinx/coroutines/test/TestResult;", "fetchTopAnime returns error when API returns empty list", "fetchTopAnime returns error when API returns null body", "fetchTopAnime returns HttpError when API returns 404", "fetchTopAnime returns HttpError when API returns 500", "fetchTopAnime returns Timeout when SocketTimeoutException occurs", "fetchTopAnime returns NoInternet when UnknownHostException occurs", "fetchAnimeDetail returns success when API returns data", "fetchAnimeDetail returns error when API returns null body", "fetchAnimeDetail returns HttpError when API returns 404", "fetchAnimeDetail returns HttpError when HttpException occurs", "createAnimeDto", "Lcom/seekho/animepilot/core/data/api/dto/AnimeDto;", "id", "", "title", "", "createAnimeDetailDto", "Lcom/seekho/animepilot/core/data/api/dto/AnimeDetailDto;", "core_releaseUnitTest"})
public final class RemoteDataSourceImplTest {
    private com.seekho.animepilot.core.data.api.JikanApi jikanApi;
    private com.seekho.animepilot.core.data.remote.RemoteDataSourceImpl remoteDataSource;
    
    public RemoteDataSourceImplTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
    
    private final com.seekho.animepilot.core.data.api.dto.AnimeDto createAnimeDto(int id, java.lang.String title) {
        return null;
    }
    
    private final com.seekho.animepilot.core.data.api.dto.AnimeDetailDto createAnimeDetailDto(int id, java.lang.String title) {
        return null;
    }
}
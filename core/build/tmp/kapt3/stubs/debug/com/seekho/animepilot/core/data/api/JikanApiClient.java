package com.seekho.animepilot.core.data.api;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/seekho/animepilot/core/data/api/JikanApiClient;", "", "<init>", "()V", "BASE_URL", "", "TIMEOUT_SECONDS", "", "createOkHttpClient", "Lokhttp3/OkHttpClient;", "getBaseUrl", "core_debug"})
public final class JikanApiClient {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "https://api.jikan.moe/v4/";
    private static final long TIMEOUT_SECONDS = 15L;
    @org.jetbrains.annotations.NotNull()
    public static final com.seekho.animepilot.core.data.api.JikanApiClient INSTANCE = null;
    
    private JikanApiClient() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient createOkHttpClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBaseUrl() {
        return null;
    }
}
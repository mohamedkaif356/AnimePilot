package com.seekho.animepilot.core.util;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\b\u0010\u0005\u001a\u00020\u0004H&\u00a8\u0006\u0006\u00c0\u0006\u0003"}, d2 = {"Lcom/seekho/animepilot/core/util/ConnectivityObserver;", "", "observeConnectivity", "Lkotlinx/coroutines/flow/Flow;", "", "isNetworkAvailable", "core_release"})
public abstract interface ConnectivityObserver {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> observeConnectivity();
    
    public abstract boolean isNetworkAvailable();
}
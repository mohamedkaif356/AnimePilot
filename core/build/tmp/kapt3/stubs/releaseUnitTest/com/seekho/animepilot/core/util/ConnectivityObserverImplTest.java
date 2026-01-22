package com.seekho.animepilot.core.util;

@org.junit.runner.RunWith(value = org.robolectric.RobolectricTestRunner.class)
@org.robolectric.annotation.Config(sdk = {28})
@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0007J\f\u0010\f\u001a\u00060\u000bj\u0002`\rH\u0007J\f\u0010\u000e\u001a\u00060\u000bj\u0002`\rH\u0007J\f\u0010\u000f\u001a\u00060\u000bj\u0002`\rH\u0007J\f\u0010\u0010\u001a\u00060\u000bj\u0002`\rH\u0007J\f\u0010\u0011\u001a\u00060\u000bj\u0002`\rH\u0007J\f\u0010\u0012\u001a\u00060\u000bj\u0002`\rH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/seekho/animepilot/core/util/ConnectivityObserverImplTest;", "", "<init>", "()V", "context", "Landroid/content/Context;", "connectivityManager", "Landroid/net/ConnectivityManager;", "connectivityObserver", "Lcom/seekho/animepilot/core/util/ConnectivityObserverImpl;", "setup", "", "isNetworkAvailable returns true when network is available with internet", "Lkotlinx/coroutines/test/TestResult;", "isNetworkAvailable returns false when no active network", "isNetworkAvailable returns false when network has no internet capability", "isNetworkAvailable returns false when network is not validated", "observeConnectivity emits initial network state", "observeConnectivity registers network callback", "core_releaseUnitTest"})
public final class ConnectivityObserverImplTest {
    private android.content.Context context;
    private android.net.ConnectivityManager connectivityManager;
    private com.seekho.animepilot.core.util.ConnectivityObserverImpl connectivityObserver;
    
    public ConnectivityObserverImplTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
}
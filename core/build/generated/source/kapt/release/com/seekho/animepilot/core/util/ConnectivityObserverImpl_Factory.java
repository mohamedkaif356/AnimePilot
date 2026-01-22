package com.seekho.animepilot.core.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ConnectivityObserverImpl_Factory implements Factory<ConnectivityObserverImpl> {
  private final Provider<Context> contextProvider;

  private ConnectivityObserverImpl_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ConnectivityObserverImpl get() {
    return newInstance(contextProvider.get());
  }

  public static ConnectivityObserverImpl_Factory create(Provider<Context> contextProvider) {
    return new ConnectivityObserverImpl_Factory(contextProvider);
  }

  public static ConnectivityObserverImpl newInstance(Context context) {
    return new ConnectivityObserverImpl(context);
  }
}

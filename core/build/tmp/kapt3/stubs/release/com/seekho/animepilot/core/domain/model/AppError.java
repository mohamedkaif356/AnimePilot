package com.seekho.animepilot.core.domain.model;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004\u00a2\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t\u00a8\u0006\n"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError;", "", "<init>", "()V", "NetworkError", "DataError", "CacheError", "Lcom/seekho/animepilot/core/domain/model/AppError$CacheError;", "Lcom/seekho/animepilot/core/domain/model/AppError$DataError;", "Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError;", "core_release"})
public abstract class AppError {
    
    private AppError() {
        super();
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0004\u00a2\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0001\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$CacheError;", "Lcom/seekho/animepilot/core/domain/model/AppError;", "<init>", "()V", "NotFound", "Lcom/seekho/animepilot/core/domain/model/AppError$CacheError$NotFound;", "core_release"})
    public static abstract class CacheError extends com.seekho.animepilot.core.domain.model.AppError {
        
        private CacheError() {
        }
        
        @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$CacheError$NotFound;", "Lcom/seekho/animepilot/core/domain/model/AppError$CacheError;", "<init>", "()V", "core_release"})
        public static final class NotFound extends com.seekho.animepilot.core.domain.model.AppError.CacheError {
            @org.jetbrains.annotations.NotNull()
            public static final com.seekho.animepilot.core.domain.model.AppError.CacheError.NotFound INSTANCE = null;
            
            private NotFound() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004\u00a2\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007\u00a8\u0006\b"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$DataError;", "Lcom/seekho/animepilot/core/domain/model/AppError;", "<init>", "()V", "ParseError", "EmptyResponse", "Lcom/seekho/animepilot/core/domain/model/AppError$DataError$EmptyResponse;", "Lcom/seekho/animepilot/core/domain/model/AppError$DataError$ParseError;", "core_release"})
    public static abstract class DataError extends com.seekho.animepilot.core.domain.model.AppError {
        
        private DataError() {
        }
        
        @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$DataError$EmptyResponse;", "Lcom/seekho/animepilot/core/domain/model/AppError$DataError;", "<init>", "()V", "core_release"})
        public static final class EmptyResponse extends com.seekho.animepilot.core.domain.model.AppError.DataError {
            @org.jetbrains.annotations.NotNull()
            public static final com.seekho.animepilot.core.domain.model.AppError.DataError.EmptyResponse INSTANCE = null;
            
            private EmptyResponse() {
            }
        }
        
        @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$DataError$ParseError;", "Lcom/seekho/animepilot/core/domain/model/AppError$DataError;", "<init>", "()V", "core_release"})
        public static final class ParseError extends com.seekho.animepilot.core.domain.model.AppError.DataError {
            @org.jetbrains.annotations.NotNull()
            public static final com.seekho.animepilot.core.domain.model.AppError.DataError.ParseError INSTANCE = null;
            
            private ParseError() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004\u00a2\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b\u00a8\u0006\f"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError;", "Lcom/seekho/animepilot/core/domain/model/AppError;", "<init>", "()V", "NoInternet", "Timeout", "HttpError", "UnknownError", "Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError$HttpError;", "Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError$NoInternet;", "Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError$Timeout;", "Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError$UnknownError;", "core_release"})
    public static abstract class NetworkError extends com.seekho.animepilot.core.domain.model.AppError {
        
        private NetworkError() {
        }
        
        @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0083\u0004J\n\u0010\u000e\u001a\u00020\u0003H\u00d6\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u00d6\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError$HttpError;", "Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError;", "code", "", "<init>", "(I)V", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "core_release"})
        public static final class HttpError extends com.seekho.animepilot.core.domain.model.AppError.NetworkError {
            private final int code = 0;
            
            public HttpError(int code) {
            }
            
            public final int getCode() {
                return 0;
            }
            
            public final int component1() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.seekho.animepilot.core.domain.model.AppError.NetworkError.HttpError copy(int code) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError$NoInternet;", "Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError;", "<init>", "()V", "core_release"})
        public static final class NoInternet extends com.seekho.animepilot.core.domain.model.AppError.NetworkError {
            @org.jetbrains.annotations.NotNull()
            public static final com.seekho.animepilot.core.domain.model.AppError.NetworkError.NoInternet INSTANCE = null;
            
            private NoInternet() {
            }
        }
        
        @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError$Timeout;", "Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError;", "<init>", "()V", "core_release"})
        public static final class Timeout extends com.seekho.animepilot.core.domain.model.AppError.NetworkError {
            @org.jetbrains.annotations.NotNull()
            public static final com.seekho.animepilot.core.domain.model.AppError.NetworkError.Timeout INSTANCE = null;
            
            private Timeout() {
            }
        }
        
        @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError$UnknownError;", "Lcom/seekho/animepilot/core/domain/model/AppError$NetworkError;", "<init>", "()V", "core_release"})
        public static final class UnknownError extends com.seekho.animepilot.core.domain.model.AppError.NetworkError {
            @org.jetbrains.annotations.NotNull()
            public static final com.seekho.animepilot.core.domain.model.AppError.NetworkError.UnknownError INSTANCE = null;
            
            private UnknownError() {
            }
        }
    }
}
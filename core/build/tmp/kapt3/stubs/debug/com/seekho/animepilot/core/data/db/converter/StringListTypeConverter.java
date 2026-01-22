package com.seekho.animepilot.core.data.db.converter;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J\u0018\u0010\b\u001a\u00020\u00062\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/seekho/animepilot/core/data/db/converter/StringListTypeConverter;", "", "<init>", "()V", "fromString", "", "", "value", "toString", "list", "Companion", "core_debug"})
public final class StringListTypeConverter {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.google.gson.Gson gsonInstance;
    @org.jetbrains.annotations.NotNull()
    public static final com.seekho.animepilot.core.data.db.converter.StringListTypeConverter.Companion Companion = null;
    
    public StringListTypeConverter() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> fromString(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toString(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> list) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/seekho/animepilot/core/data/db/converter/StringListTypeConverter$Companion;", "", "<init>", "()V", "gsonInstance", "Lcom/google/gson/Gson;", "setGson", "", "gson", "getGson", "()Lcom/google/gson/Gson;", "core_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void setGson(@org.jetbrains.annotations.NotNull()
        com.google.gson.Gson gson) {
        }
        
        private final com.google.gson.Gson getGson() {
            return null;
        }
    }
}
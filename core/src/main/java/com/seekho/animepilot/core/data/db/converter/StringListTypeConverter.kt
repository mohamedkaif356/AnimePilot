package com.seekho.animepilot.core.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListTypeConverter {
    
    companion object {
        @Volatile
        private var gsonInstance: Gson? = null
        
        fun setGson(gson: Gson) {
            gsonInstance = gson
        }
        
        private val gson: Gson
            get() = gsonInstance ?: throw IllegalStateException("Gson not initialized. Call setGson() first.")
    }
    
    @TypeConverter
    fun fromString(value: String?): List<String> {
        if (value == null || value.isEmpty()) {
            return emptyList()
        }
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun toString(list: List<String>?): String {
        if (list == null || list.isEmpty()) {
            return "[]"
        }
        return gson.toJson(list)
    }
}
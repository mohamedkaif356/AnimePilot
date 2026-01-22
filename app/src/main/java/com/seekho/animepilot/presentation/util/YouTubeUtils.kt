package com.seekho.animepilot.presentation.util

import android.util.Log

private const val TAG = "YouTubeUtils"

/**
 * Utility functions for YouTube video URL processing.
 */
object YouTubeUtils {

    /**
     * Extracts YouTube video ID from various URL formats:
     * - https://www.youtube.com/watch?v=VIDEO_ID
     * - https://youtu.be/VIDEO_ID
     * - https://www.youtube.com/embed/VIDEO_ID
     * - https://www.youtube-nocookie.com/embed/VIDEO_ID (Jikan API format)
     * - https://m.youtube.com/watch?v=VIDEO_ID
     * - Embed URLs from Jikan API with query parameters
     *
     * @param url The YouTube URL or embed URL
     * @return The video ID if valid YouTube URL, null otherwise
     */
    fun extractVideoId(url: String?): String? {
        if (url.isNullOrBlank()) {
            Log.d(TAG, "URL is null or blank")
            return null
        }

        // Remove whitespace
        val cleanUrl = url.trim()
        Log.d(TAG, "Extracting video ID from URL: $cleanUrl")

        // Pattern 1: youtube.com/watch?v=VIDEO_ID or youtu.be/VIDEO_ID
        val watchPattern =
            Regex("(?:youtube\\.com/watch\\?v=|youtu\\.be/|youtube\\.com/embed/|m\\.youtube\\.com/watch\\?v=)([a-zA-Z0-9_-]{11})")
        val watchMatch = watchPattern.find(cleanUrl)
        if (watchMatch != null) {
            val videoId = watchMatch.groupValues[1]
            Log.d(TAG, "Extracted video ID (watch pattern): $videoId")
            return videoId
        }

        // Pattern 2: Embed URL format - supports both youtube.com and youtube-nocookie.com
        // Handles URLs like: https://www.youtube.com/embed/VIDEO_ID or https://www.youtube-nocookie.com/embed/VIDEO_ID?params
        val embedPattern =
            Regex("(?:youtube\\.com|youtube-nocookie\\.com)/embed/([a-zA-Z0-9_-]{11})(?:\\?|$|/)")
        val embedMatch = embedPattern.find(cleanUrl)
        if (embedMatch != null) {
            val videoId = embedMatch.groupValues[1]
            Log.d(TAG, "Extracted video ID (embed pattern): $videoId")
            return videoId
        }

        // Pattern 3: Direct video ID (if URL is just the ID)
        val directIdPattern = Regex("^[a-zA-Z0-9_-]{11}$")
        if (directIdPattern.matches(cleanUrl)) {
            Log.d(TAG, "URL is direct video ID: $cleanUrl")
            return cleanUrl
        }

        // Pattern 4: Handle URLs with additional parameters (e.g., &t=, &list=)
        val urlWithParamsPattern =
            Regex("(?:youtube\\.com/watch\\?v=|youtu\\.be/)([a-zA-Z0-9_-]{11})(?:&|$)")
        val paramsMatch = urlWithParamsPattern.find(cleanUrl)
        if (paramsMatch != null) {
            val videoId = paramsMatch.groupValues[1]
            Log.d(TAG, "Extracted video ID (with params): $videoId")
            return videoId
        }

        Log.w(TAG, "Could not extract video ID from URL: $cleanUrl")
        return null
    }

    /**
     * Extracts the ?si= parameter from a YouTube URL if present.
     * This parameter is used for tracking and attribution in modern YouTube sharing links.
     *
     * @param url The YouTube URL (watch URL, embed URL, or share URL)
     * @return The si parameter value if found, null otherwise
     */
    fun extractSiParameter(url: String?): String? {
        if (url.isNullOrBlank()) {
            Log.d(TAG, "URL is null or blank, cannot extract si parameter")
            return null
        }
        val siPattern = Regex("[?&]si=([a-zA-Z0-9_-]+)")
        val match = siPattern.find(url)
        val siValue = match?.groupValues?.get(1)

        if (siValue != null) {
            Log.d(TAG, "Extracted si parameter from URL: $siValue")
        } else {
            Log.d(TAG, "No si parameter found in URL")
        }

        return siValue
    }
}

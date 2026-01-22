package com.seekho.animepilot.presentation.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class YouTubeUtilsTest {

    @Test
    fun extractVideoId_withWatchUrl_returnsVideoId() {
        // Given
        val url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractVideoId_withYoutuBeUrl_returnsVideoId() {
        // Given
        val url = "https://youtu.be/dQw4w9WgXcQ"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractVideoId_withEmbedUrl_returnsVideoId() {
        // Given
        val url = "https://www.youtube.com/embed/dQw4w9WgXcQ"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractVideoId_withYoutubeNocookieEmbedUrl_returnsVideoId() {
        // Given
        val url = "https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractVideoId_withYoutubeNocookieEmbedUrlWithParameters_returnsVideoId() {
        // Given
        val url = "https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ?enablejsapi=1&wmode=opaque&autoplay=1"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractVideoId_withMobileUrl_returnsVideoId() {
        // Given
        val url = "https://m.youtube.com/watch?v=dQw4w9WgXcQ"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractVideoId_withDirectVideoId_returnsVideoId() {
        // Given
        val url = "dQw4w9WgXcQ"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractVideoId_withUrlContainingParameters_returnsVideoId() {
        // Given
        val url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ&t=30s&list=PLxxxxx"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractVideoId_withYoutuBeUrlContainingParameters_returnsVideoId() {
        // Given
        val url = "https://youtu.be/dQw4w9WgXcQ?t=30"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractVideoId_withNull_returnsNull() {
        // When
        val videoId = YouTubeUtils.extractVideoId(null)

        // Then
        assertThat(videoId).isNull()
    }

    @Test
    fun extractVideoId_withBlankString_returnsNull() {
        // When
        val videoId = YouTubeUtils.extractVideoId("   ")

        // Then
        assertThat(videoId).isNull()
    }

    @Test
    fun extractVideoId_withInvalidUrl_returnsNull() {
        // Given
        val url = "https://example.com/video"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isNull()
    }

    @Test
    fun extractVideoId_withInvalidVideoIdFormat_returnsNull() {
        // Given
        val url = "https://www.youtube.com/watch?v=invalid"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isNull()
    }

    @Test
    fun extractVideoId_trimsWhitespace() {
        // Given
        val url = "  https://www.youtube.com/watch?v=dQw4w9WgXcQ  "

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }

    @Test
    fun extractSiParameter_withSiParameterInUrl_returnsSiValue() {
        // Given
        val url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ?si=abc123xyz456"

        // When
        val siParameter = YouTubeUtils.extractSiParameter(url)

        // Then
        assertThat(siParameter).isEqualTo("abc123xyz456")
    }

    @Test
    fun extractSiParameter_withSiParameterAsSecondParameter_returnsSiValue() {
        // Given
        val url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ&t=30s&si=def789ghi012"

        // When
        val siParameter = YouTubeUtils.extractSiParameter(url)

        // Then
        assertThat(siParameter).isEqualTo("def789ghi012")
    }

    @Test
    fun extractSiParameter_withoutSiParameter_returnsNull() {
        // Given
        val url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"

        // When
        val siParameter = YouTubeUtils.extractSiParameter(url)

        // Then
        assertThat(siParameter).isNull()
    }

    @Test
    fun extractSiParameter_withNull_returnsNull() {
        // When
        val siParameter = YouTubeUtils.extractSiParameter(null)

        // Then
        assertThat(siParameter).isNull()
    }

    @Test
    fun extractSiParameter_withBlankString_returnsNull() {
        // When
        val siParameter = YouTubeUtils.extractSiParameter("   ")

        // Then
        assertThat(siParameter).isNull()
    }

    @Test
    fun extractSiParameter_withSiParameterInEmbedUrl_returnsSiValue() {
        // Given
        val url = "https://www.youtube.com/embed/dQw4w9WgXcQ?si=test123param"

        // When
        val siParameter = YouTubeUtils.extractSiParameter(url)

        // Then
        assertThat(siParameter).isEqualTo("test123param")
    }

    @Test
    fun extractSiParameter_handlesSiParameterWithSpecialCharacters() {
        // Given
        val url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ?si=abc-123_xyz456"

        // When
        val siParameter = YouTubeUtils.extractSiParameter(url)

        // Then
        assertThat(siParameter).isEqualTo("abc-123_xyz456")
    }

    @Test
    fun extractVideoId_withComplexEmbedUrl_returnsCorrectVideoId() {
        // Given
        val url = "https://www.youtube-nocookie.com/embed/RH-FcW94z00?enablejsapi=1&wmode=opaque&autoplay=1"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("RH-FcW94z00")
    }

    @Test
    fun extractVideoId_withUrlEndingWithSlash_returnsVideoId() {
        // Given
        val url = "https://www.youtube.com/embed/dQw4w9WgXcQ/"

        // When
        val videoId = YouTubeUtils.extractVideoId(url)

        // Then
        assertThat(videoId).isEqualTo("dQw4w9WgXcQ")
    }
}

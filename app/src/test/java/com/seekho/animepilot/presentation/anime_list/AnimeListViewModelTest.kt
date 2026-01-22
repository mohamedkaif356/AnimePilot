package com.seekho.animepilot.presentation.anime_list

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.seekho.animepilot.core.domain.model.Anime
import com.seekho.animepilot.core.domain.repository.AnimeRepository
import com.seekho.animepilot.core.util.ConnectivityObserver
import com.seekho.animepilot.presentation.NavigationEvent
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import app.cash.turbine.test

@OptIn(ExperimentalCoroutinesApi::class)
class AnimeListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: AnimeRepository
    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var viewModel: AnimeListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        connectivityObserver = mockk()

        // Setup default mocks
        every { repository.getTopAnimePagingFlow() } returns flowOf(PagingData.empty())
        every { connectivityObserver.observeConnectivity() } returns MutableStateFlow(true)

        viewModel = AnimeListViewModel(repository, connectivityObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state has isOffline false`() = runTest {
        // When
        val isOffline = viewModel.isOffline.value

        // Then
        assertThat(isOffline).isFalse()
    }

    @Test
    fun `initial state has isRefreshing false`() = runTest {
        // When
        val isRefreshing = viewModel.isRefreshing.value

        // Then
        assertThat(isRefreshing).isFalse()
    }

    @Test
    fun `animePagingFlow is correctly exposed`() = runTest {
        // Given
        val testAnime = Anime(
            id = 1,
            title = "Test Anime",
            posterUrl = "test.jpg",
            episodes = 12,
            rating = 8.5
        )
        val pagingData = PagingData.from(listOf(testAnime))
        every { repository.getTopAnimePagingFlow() } returns flowOf(pagingData)

        viewModel = AnimeListViewModel(repository, connectivityObserver)

        // When
        val result = viewModel.animePagingFlow

        // Then
        assertThat(result).isNotNull()
    }

    @Test
    fun `setRefreshing updates state correctly to true`() = runTest {
        // When
        viewModel.setRefreshing(true)

        // Then
        viewModel.isRefreshing.test {
            assertThat(awaitItem()).isTrue()
        }
    }

    @Test
    fun `setRefreshing updates state correctly to false`() = runTest {
        // Given
        viewModel.setRefreshing(true)

        // When
        viewModel.setRefreshing(false)

        // Then
        viewModel.isRefreshing.test {
            skipItems(1) // Skip initial false
            assertThat(awaitItem()).isFalse()
        }
    }

    @Test
    fun `onAnimeClicked emits navigation event`() = runTest {
        // Given
        val animeId = 123

        // When
        viewModel.onAnimeClicked(animeId)

        // Then
        viewModel.navigationEvents.test {
            val event = awaitItem()
            assertThat(event).isInstanceOf(NavigationEvent.NavigateToDetail::class.java)
            assertThat((event as NavigationEvent.NavigateToDetail).animeId).isEqualTo(animeId)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `connectivity changes update isOffline state to true when offline`() = runTest {
        // Given
        val connectivityFlow = MutableStateFlow(true)
        every { connectivityObserver.observeConnectivity() } returns connectivityFlow

        viewModel = AnimeListViewModel(repository, connectivityObserver)

        // When
        connectivityFlow.value = false

        // Then
        viewModel.isOffline.test {
            skipItems(1) // Skip initial false
            assertThat(awaitItem()).isTrue()
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `connectivity changes update isOffline state to false when online`() = runTest {
        // Given
        val connectivityFlow = MutableStateFlow(false)
        every { connectivityObserver.observeConnectivity() } returns connectivityFlow

        viewModel = AnimeListViewModel(repository, connectivityObserver)

        // When
        connectivityFlow.value = true

        // Then
        viewModel.isOffline.test {
            skipItems(1) // Skip initial true
            assertThat(awaitItem()).isFalse()
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `multiple rapid clicks emit all navigation events`() = runTest {
        // Given
        val animeIds = listOf(1, 2, 3)

        // When
        animeIds.forEach { viewModel.onAnimeClicked(it) }

        // Then
        viewModel.navigationEvents.test {
            animeIds.forEach { expectedId ->
                val event = awaitItem()
                assertThat(event).isInstanceOf(NavigationEvent.NavigateToDetail::class.java)
                assertThat((event as NavigationEvent.NavigateToDetail).animeId).isEqualTo(expectedId)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `setRefreshing with same value does not cause issues`() = runTest {
        // When
        viewModel.setRefreshing(false)
        viewModel.setRefreshing(false)

        // Then - should not throw or cause issues
        viewModel.isRefreshing.test {
            assertThat(awaitItem()).isFalse()
            cancelAndIgnoreRemainingEvents()
        }
    }
}

package com.seekho.animepilot.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ConnectivityObserverImplTest {

    private lateinit var context: Context
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var connectivityObserver: ConnectivityObserverImpl

    @Before
    fun setup() {
        context = mockk(relaxed = true)
        connectivityManager = mockk(relaxed = true)
        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
        
        connectivityObserver = ConnectivityObserverImpl(context)
    }

    @Test
    fun `isNetworkAvailable returns true when network is available with internet`() = runTest {
        // Given
        val network = mockk<Network>(relaxed = true)
        val capabilities = mockk<NetworkCapabilities>(relaxed = true)
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns capabilities
        every { capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns true
        every { capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) } returns true

        // When
        val result = connectivityObserver.isNetworkAvailable()

        // Then
        assertTrue(result)
    }

    @Test
    fun `isNetworkAvailable returns false when no active network`() = runTest {
        // Given
        every { connectivityManager.activeNetwork } returns null

        // When
        val result = connectivityObserver.isNetworkAvailable()

        // Then
        assertFalse(result)
    }

    @Test
    fun `isNetworkAvailable returns false when network has no internet capability`() = runTest {
        // Given
        val network = mockk<Network>(relaxed = true)
        val capabilities = mockk<NetworkCapabilities>(relaxed = true)
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns capabilities
        every { capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns false

        // When
        val result = connectivityObserver.isNetworkAvailable()

        // Then
        assertFalse(result)
    }

    @Test
    fun `isNetworkAvailable returns false when network is not validated`() = runTest {
        // Given
        val network = mockk<Network>(relaxed = true)
        val capabilities = mockk<NetworkCapabilities>(relaxed = true)
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns capabilities
        every { capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns true
        every { capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) } returns false

        // When
        val result = connectivityObserver.isNetworkAvailable()

        // Then
        assertFalse(result)
    }

    @Test
    fun `observeConnectivity emits initial network state`() = runTest {
        // Given
        val network = mockk<Network>(relaxed = true)
        val capabilities = mockk<NetworkCapabilities>(relaxed = true)
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns capabilities
        every { capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns true
        every { capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) } returns true
        
        val callbackSlot = slot<ConnectivityManager.NetworkCallback>()
        every { connectivityManager.registerNetworkCallback(any(), capture(callbackSlot)) } returns Unit

        // When
        val flow = connectivityObserver.observeConnectivity()
        val firstValue = flow.first()

        // Then
        assertTrue(firstValue)
        verify { connectivityManager.registerNetworkCallback(any<NetworkRequest>(), any<ConnectivityManager.NetworkCallback>()) }
    }

    @Test
    fun `observeConnectivity registers network callback`() = runTest {
        // Given
        val network = mockk<Network>(relaxed = true)
        every { connectivityManager.activeNetwork } returns network
        val capabilities = mockk<NetworkCapabilities>(relaxed = true)
        every { connectivityManager.getNetworkCapabilities(network) } returns capabilities
        every { capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns false
        
        val callbackSlot = slot<ConnectivityManager.NetworkCallback>()
        every { connectivityManager.registerNetworkCallback(any(), capture(callbackSlot)) } returns Unit

        // When
        val flow = connectivityObserver.observeConnectivity()
        flow.first() // Trigger flow collection

        // Then
        verify(exactly = 1) { connectivityManager.registerNetworkCallback(any<NetworkRequest>(), any<ConnectivityManager.NetworkCallback>()) }
    }
}

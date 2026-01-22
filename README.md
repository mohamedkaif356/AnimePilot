# 🎌 AnimePilot

<div align="center">

**A modern Android application for discovering and exploring top anime series**

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.0-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API%2028%2B-3DDC84?logo=android&logoColor=white)](https://developer.android.com/)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.10.00-4285F4?logo=jetpack-compose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

</div>

---

## 📱 Overview

**AnimePilot** is a production-ready Android application built with modern Android development practices. It provides users with a seamless experience to browse top anime series, view detailed information, watch trailers, and access content offline. The app demonstrates enterprise-level architecture, robust error handling, and optimal performance.

### Key Highlights

- 🏗️ **Clean Architecture** with clear separation of concerns
- 🎨 **Material Design** with smooth animations
- 📡 **Offline-First** approach with intelligent caching
- 🔄 **Pagination** for efficient data loading
- 🎬 **YouTube Integration** for trailer playback
- 🧪 **Comprehensive Testing** with unit tests
- ⚡ **Performance Optimized** with Coil image loading

---

## ✨ Features

### Core Functionality

- **📋 Top Anime List**
  - Infinite scroll pagination with Paging 3
  - Pull-to-refresh functionality
  - Shimmer loading effects
  - Empty and error states with retry

- **📖 Anime Details**
  - Comprehensive anime information
  - Synopsis, genres, and cast
  - Rating and episode count
  - High-quality poster images

- **🎬 Trailer Playback**
  - Embedded YouTube player
  - Automatic fallback to poster on errors
  - External YouTube app integration
  - Offline poster display

- **🌐 Offline Support**
  - Intelligent data caching with Room
  - Image caching with Coil
  - Offline mode indicators
  - Seamless online/offline transitions

- **🎯 User Experience**
  - Smooth animations and transitions
  - Material 3 design system
  - Responsive layouts
  - Accessibility support

---

## 🏗️ Architecture

The application follows **Clean Architecture** principles with a clear separation of layers:

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  (Jetpack Compose + ViewModel)          │
├─────────────────────────────────────────┤
│           Domain Layer                  │
│  (Use Cases + Domain Models)            │
├─────────────────────────────────────────┤
│            Data Layer                   │
│  (Repository + Data Sources)            │
│  ├── Remote (Retrofit + API)           │
│  ├── Local (Room Database)             │
│  └── Cache (Paging 3 + RemoteMediator) │
└─────────────────────────────────────────┘
```

### Architecture Components

- **Presentation Layer**: Jetpack Compose UI with MVVM pattern
- **Domain Layer**: Business logic and use cases
- **Data Layer**: Repository pattern with multiple data sources
- **Dependency Injection**: Hilt for dependency management

---

## 🛠️ Tech Stack

### Core Technologies

| Category | Technology | Version |
|----------|-----------|---------|
| **Language** | Kotlin | 2.3.0 |
| **UI Framework** | Jetpack Compose | 2024.10.00 |
| **Architecture** | MVVM + Clean Architecture | - |
| **DI Framework** | Hilt | 2.58 |
| **Async** | Kotlin Coroutines + Flow | 1.10.2 |

### Libraries & Dependencies

#### UI & Design
- **Material 3**: Modern Material Design components
- **Compose Navigation**: Type-safe navigation
- **Shimmer**: Loading skeleton effects
- **Coil**: Image loading and caching

#### Networking
- **Retrofit**: Type-safe HTTP client
- **OkHttp**: HTTP client with logging
- **Gson**: JSON serialization

#### Data Persistence
- **Room**: Local database
- **Paging 3**: Pagination library with RemoteMediator

#### Media
- **YouTube Player**: Embedded video playback

#### Testing
- **JUnit**: Unit testing framework
- **MockK**: Mocking library
- **Turbine**: Flow testing
- **Truth**: Assertion library

---

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Android Studio** Hedgehog (2023.1.1) or later
- **JDK** 11 or higher
- **Android SDK** with API 28+ (Android 9.0+)
- **Gradle** 8.13.2 or compatible version

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd "Seekho assignment"
```

### 2. Open in Android Studio

1. Open Android Studio
2. Select **File → Open**
3. Navigate to the project directory
4. Click **OK**

### 3. Sync Gradle

Android Studio will automatically sync Gradle dependencies. If not:

1. Click **File → Sync Project with Gradle Files**
2. Wait for the sync to complete

### 4. Build and Run

1. Connect an Android device or start an emulator (API 28+)
2. Click **Run** (▶️) or press `Shift + F10`
3. The app will build and install on your device

### 5. Build Variants

- **Debug**: Development build with logging enabled
- **Release**: Production build with optimizations

---

## 📁 Project Structure

```
AnimePilot/
├── app/                          # Application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/seekho/animepilot/
│   │   │   │   ├── di/           # Dependency injection
│   │   │   │   ├── presentation/ # UI layer
│   │   │   │   │   ├── anime_list/
│   │   │   │   │   ├── anime_detail/
│   │   │   │   │   ├── navigation/
│   │   │   │   │   └── ui/component/
│   │   │   │   └── util/
│   │   │   └── res/              # Resources
│   │   └── test/                 # Unit tests
│   └── build.gradle.kts
│
├── core/                          # Core module
│   ├── src/main/java/com/seekho/animepilot/core/
│   │   ├── data/                 # Data layer
│   │   │   ├── api/              # API interfaces & DTOs
│   │   │   ├── db/               # Room database
│   │   │   ├── local/            # Local data source
│   │   │   ├── remote/           # Remote data source
│   │   │   ├── repository/        # Repository implementation
│   │   │   └── mapper/           # Data mappers
│   │   ├── domain/               # Domain layer
│   │   │   ├── model/            # Domain models
│   │   │   ├── repository/      # Repository interfaces
│   │   │   └── usecase/         # Use cases
│   │   └── util/                 # Utilities
│   └── build.gradle.kts
│
├── gradle/                        # Gradle configuration
│   └── libs.versions.toml       # Version catalog
│
└── build.gradle.kts              # Root build file
```

---

## 🔌 API Integration

The application integrates with the **Jikan API** (Unofficial MyAnimeList API):

- **Base URL**: `https://api.jikan.moe/v4/`
- **Endpoints**:
  - `GET /top/anime` - Fetch top anime list
  - `GET /anime/{id}` - Fetch anime details

### API Configuration

The API client is configured in `JikanApiClient` with:
- 15-second timeout
- HTTP logging interceptor (debug builds)
- Gson converter for JSON parsing

---

## 🧪 Testing

### Running Tests

```bash
# Run all unit tests
./gradlew test

# Run tests for a specific module
./gradlew :app:testDebugUnitTest
./gradlew :core:testDebugUnitTest

# Generate test coverage report
./gradlew jacocoTestReport
```

### Test Structure

- **Unit Tests**: ViewModel logic, utilities, mappers
- **Test Location**: `app/src/test/java/`
- **Test Framework**: JUnit 4 with MockK and Turbine

### Test Coverage

The project includes unit tests for:
- ViewModels (AnimeListViewModel, AnimeDetailViewModel)
- Utility functions (YouTubeUtils)
- Data mappers and transformations

---

## 🎨 UI/UX Features

### Design System

- **Material**: Material Design guidelines
- **Dark/Light Theme**: System theme support
- **Typography**: Material typography scale
- **Colors**: Material color system
- **Shapes**: Rounded corners and elevation

### Animations

- **Screen Transitions**: Fade and scale animations
- **State Changes**: Smooth transitions between loading/error/success
- **List Animations**: Item animations on scroll
- **Pull-to-Refresh**: Material pull-to-refresh indicator

### Accessibility

- Content descriptions for screen readers
- Proper semantic roles
- Touch target sizes (48dp minimum)
- Color contrast compliance

---

## 💾 Data Management

### Caching Strategy

- **Room Database**: Persistent local storage
- **Coil Image Cache**: Disk and memory caching
- **Paging 3**: Efficient pagination with RemoteMediator
- **Offline-First**: Always show cached data when available

### Database Schema

- **AnimeEntity**: List items with pagination keys
- **AnimeDetailEntity**: Detailed anime information
- **RemoteKeys**: Paging state management

---

## 🔒 Error Handling

The application implements comprehensive error handling:

- **Network Errors**: Timeout, no internet, HTTP errors
- **Data Errors**: Parse errors, empty responses
- **Cache Errors**: Missing cached data
- **User-Friendly Messages**: Localized error messages
- **Retry Mechanisms**: Automatic and manual retry options

---

## 🚀 Performance Optimizations

- **Image Loading**: Coil with disk and memory caching
- **Pagination**: Efficient data loading with Paging 3
- **Lazy Loading**: Compose LazyColumn for list rendering
- **Coroutines**: Non-blocking async operations
- **Flow**: Reactive data streams
- **Memory Management**: Proper lifecycle handling

---

## 📱 Minimum Requirements

- **Android Version**: Android 9.0 (API 28) or higher
- **Screen Size**: Optimized for phones and tablets
- **Network**: Internet connection required for initial data load
- **Permissions**: 
  - `INTERNET` - Network access
  - `ACCESS_NETWORK_STATE` - Network state monitoring

---

## 🔧 Configuration

### Build Variants

- **Debug**: Development build with logging
- **Release**: Production build

### ProGuard

ProGuard rules are configured in `app/proguard-rules.pro`. For production builds, enable ProGuard in `build.gradle.kts`:

```kotlin
buildTypes {
    release {
        isMinifyEnabled = true
        proguardFiles(...)
    }
}
```

---

### Code Style

- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add comments for complex logic
- Write unit tests for new features

---

## 🙏 Acknowledgments

- **Jikan API** - Unofficial MyAnimeList API
- **Android Jetpack** - Modern Android development tools
- **Open Source Community** - All the amazing libraries used

---

## 📊 Project Statistics

- **Language**: 100% Kotlin
- **Architecture**: Clean Architecture + MVVM
- **UI Framework**: 100% Jetpack Compose
- **Test Coverage**: Unit tests for critical components
- **Min SDK**: API 28 (Android 9.0)
- **Target SDK**: API 36 (Android 15)

---

<div align="center">

**Built with ❤️ using modern Android development practices**

⭐ Star this repo if you find it helpful!

</div>

---

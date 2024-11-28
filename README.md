# Eyoj Video Course Application

Eyoj Video Course is a modular Android application designed to provide users with a seamless video course experience. It integrates modern Android technologies and adheres to Clean Architecture principles for scalability and maintainability.

https://github.com/user-attachments/assets/745fa457-e029-4216-ac86-ef07dbb070e1

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Modules](#modules)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Screenshots](#screenshots)
- [Setup](#setup)

---

## Overview

Eyoj Video Course enables users to:
- Browse, search, and filter courses by category.
- Enroll in courses to unlock video lessons.
- Watch lessons with playback progress tracking.
- Switch between Dark and Light modes.
- Track video progress and see it visually as an overlay.

The project uses modularization to separate responsibilities, ensuring cleaner and more maintainable code.

---

## Features

- **Course Listing**: Users can view courses, search by keywords, and filter by categories.
- **Course Details**: Detailed course descriptions and lesson lists. Enroll in a course to access the content.
- **Video Playback**: Continue watching lessons from where you left off, using ExoPlayer.
- **Progress Tracking**: Video progress is visually displayed on thumbnails.
- **Theme Switching**: Toggle between Light Mode and Dark Mode.
- **Authentication**: Login, registration, and authentication flows with Firebase.
- **Offline Support**: Course data is stored in a local Room database for offline access.

---

## Modules

### App Module
The main entry point of the application, containing the `MainActivity` and BottomNavigation integration.

### Core Modules
1. **`core-common`**: Shared utilities, constants, and base classes used across the app.
2. **`core-data`**: Handles API calls, Room database setup, and data repository implementations.
3. **`core-domain`**: Contains business logic encapsulated in use cases.

### Feature Modules
1. **`feature-main-auth`**: Manages user authentication, including login and sign-up functionality.
2. **`feature-main-coursecommunicator`**: Defines communication interfaces for module interaction.
3. **`feature-main-coursedetail`**: Displays detailed course information and lesson lists.
4. **`feature-main-courselist`**: Manages course listing with search and filter functionality.
5. **`feature-main-coursevideo`**: Video playback and progress tracking using ExoPlayer.
6. **`feature-main-profile`**: User profile management, including logout and theme switching.

### Navigation Module
**`navigationcourseapp`**: Defines dynamic navigation graphs and facilitates inter-module communication using the `NavigationGraph` interface.

---

## Architecture

The application follows the **Clean Architecture** pattern, ensuring a clear separation of concerns:

- **Data Layer**:
  - Fetches data from remote APIs and caches it in Room.
  - Maps API responses to domain models using mappers.

- **Domain Layer**:
  - Encapsulates business logic into use cases.
  - Provides data to the presentation layer.

- **Presentation Layer**:
  - Utilizes ViewModels and `StateFlow` for UI state management.
  - Handles user interactions and updates UI reactively.

---

## Technologies Used

- **Kotlin**: Primary programming language.
- **Hilt**: Dependency Injection framework.
- **Room**: Local database for offline caching.
- **ExoPlayer**: Video playback library.
- **StateFlow**: State management for ViewModels.
- **Firebase Authentication**: User authentication and login.

---

## Screenshots

### Course List Screen
<img src="https://github.com/user-attachments/assets/7e161eb7-48eb-420e-be41-cfc127af25cb" width="200" height="400" />

### Filter by Category
<img src="https://github.com/user-attachments/assets/3892b0a9-d463-45e3-aedf-e8827e8a0e77" width="200" height="400" />

### Search Functionality
<img src="https://github.com/user-attachments/assets/37b2a25e-69d5-49dc-95ab-af0883ccfd3d" width="200" height="400" />

### Course Details - Before Enrollment
<img src="https://github.com/user-attachments/assets/122ca9ec-62a0-404d-8cde-eec0ecd854de" width="200" height="400" />

### Enrollment Popup
<img src="https://github.com/user-attachments/assets/088bbedf-245d-4218-92dd-9ec6794dd76e" width="200" height="400" />

### Course Lessons After Enrollment
<img src="https://github.com/user-attachments/assets/3760c800-03e9-4d13-bf38-aea4e46c4380" width="200" height="400" />

### Video Playback
<img src="https://github.com/user-attachments/assets/0337e040-6c60-43ed-9593-6d7b6419428f" width="200" height="400" />

### Lesson Progress
<img src="https://github.com/user-attachments/assets/db0cb0e5-5e9f-4db4-a971-ad72a857c70e" width="200" height="400" />

### Dark Mode
<img src="https://github.com/user-attachments/assets/b1fd6021-3013-4cf5-8a2e-40faad04065b" width="200" height="400" />

### Login Screen
<img src="https://github.com/user-attachments/assets/ad1fd155-d276-4d3b-a060-b0b856b327bc" width="200" height="400" />

### Push Notification with One Signal
<img src="https://github.com/user-attachments/assets/bd693b77-d582-4f7c-80ab-284a2764f432" width="200" height="400" />

---

## Setup

1. Clone the repository.
   ```bash
   git clone <https://github.com/EyoJneSinE/EyojVideoCourse.git>

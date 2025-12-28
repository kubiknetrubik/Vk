# Android Development Course Portfolio (VK)
This repository contains my coursework and projects completed during the VK Android Mobile Development Course (2025). It showcases my transition from learning basic UI components to building a functional, data-driven application.

🦊 Main Project: prototype of "Pixi" Task Manager
The core of this repository is Pixi, a productivity-focused application with a friendly fox mascot. The app is designed to help users track their daily tasks while providing an engaging and smooth user experience.

Key Features:
Dynamic Task Management: A list-based task tracker where users can add and view their daily goals.

Data Persistence: Uses a repository pattern to manage data. Currently, it handles JSON serialization/deserialization with an automated migration from assets to internal storage for write access.

Responsive & Adaptive UI: Built entirely with Jetpack Compose. The layout dynamically adapts between Portrait and Landscape orientations using weights and orientation-aware components.

State Management: Implemented using MVVM (ViewModel) and StateFlow to ensure a reactive UI and handle configuration changes (like screen rotation) without data loss.

Advanced Navigation: Uses the Compose Navigation component with complex argument passing between screens (e.g., passing user profile data from registration to settings).

Tech Stack:
Language: Kotlin

UI Framework: Jetpack Compose

Navigation: Compose Navigation

Architecture: MVVM (Model-View-ViewModel)

Data: JSON / GSON (with future plans for Room DB)

Asynchrony: Kotlin Coroutines & Flow

📚 Homework Assignments
Besides the main app, this repository includes solutions to specific course tasks:

Layout Exercises: Working with Row, Column, and Box.

Navigation Tasks: Setting up NavHost and passing data between fragments/composables.

Local Storage: Initial implementation of file-based storage.
# 📱 VK Android Development Course (2025)

This repository contains my coursework and projects completed during the **VK Android Mobile Development Course**. It showcases my transition from learning basic UI components to building a functional, data-driven application.

---

## 🦊 Main Project: "Pixi" Task Manager (Prototype)

The core of this repository is **Pixi**, a productivity-focused application with a friendly fox mascot. The app helps users track their daily tasks while providing an engaging and smooth user experience.

### ✨ Key Features
* **Dynamic Task Management:** A list-based task tracker where users can add and view their daily goals.
* **Data Persistence:** Uses a **Repository Pattern**. It handles JSON serialization/deserialization with an automated migration from `assets` to internal storage to allow saving new data.
* **Responsive & Adaptive UI:** Built entirely with **Jetpack Compose**. The layout dynamically adapts between **Portrait** and **Landscape** orientations.
* **State Management:** Implemented using **MVVM (ViewModel)** and **StateFlow** to ensure a reactive UI and handle configuration changes (like screen rotation) without data loss.
* **Advanced Navigation:** Uses the **Compose Navigation** component with argument passing (e.g., transferring user profile data like login/email between screens).

### 🛠 Tech Stack
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose
* **Architecture:** MVVM (Model-View-ViewModel)
* **Navigation:** Compose Navigation
* **Data:** JSON / GSON (Serialization)
* **Concurrency:** Kotlin Coroutines & Flow

---

## 📚 Homework Assignments
Besides the main app, this repository includes solutions to specific course tasks:

* **Layout Exercises:** Mastering `Row`, `Column`, and `Box` for complex UIs.
* **Local Storage:** Working with the Android File System and `assets` folder.

---
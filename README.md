# 🧠 Smart Study Planner: AI-Assisted Learning Management System

> A Java-based Command-Line Interface (CLI) application designed to help students efficiently organize tasks, receive smart recommendations, and track learning progress.

---

## 📌 Overview

The **Smart Study Planner** enhances traditional study management by incorporating a **Smart Suggestion Engine**. This engine analyzes task parameters—such as **due date urgency** and **difficulty**—to recommend the most critical task to tackle next, ensuring students maximize their study time.

This project is built using **Core Java** and demonstrates best practices in **Object-Oriented Programming (OOP)**, **Layered Architecture**, robust **Exception Handling**, and **File Persistence**.

---

## ✨ Features

| Category | Feature | Description |
| :--- | :--- | :--- |
| **📋 Task Management** | CRUD Operations | Add, list, and mark study tasks as completed. |
| **🤖 Smart Suggestion** | Priority Engine | Recommends the single best task based on a weighted urgency score (Due Date + Difficulty). |
| **📊 Analytics Module** | Productivity Tracking | Displays total tasks, completed tasks, and overall completion percentage. |
| **💾 Data Persistence** | Durable Storage | Automatically saves and loads all task data to `tasks.txt` across different sessions. |

---

## 🛠 Tech Stack and Concepts

* **Language:** Java (Core)
* **Interface:** Command-Line Interface (CLI)
* **Design Paradigm:** Object-Oriented Programming (OOP)
* **Key Concepts Demonstrated:**
    * Inheritance, Abstraction, Polymorphism, and Encapsulation.
    * Java Collections (`ArrayList`).
    * Robust File Handling (`ObjectOutputStream`, `ObjectInputStream`) for serialization.
    * Time and Date Management (`java.time.LocalDate`).
    * Structured Exception Handling.

---

## 📂 Project Structure

The code is organized into three distinct layers: 
Data Model (`Task`), Business Logic (`TaskManager`, `SuggestionEngine`), and Presentation (`Main`).

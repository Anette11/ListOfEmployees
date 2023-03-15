package com.example.listofemployees.util

enum class TabType(
    val department: String,
    val tabName: String
) {
    ALL(
        department = "",
        tabName = "Все"
    ),
    DESIGN(
        department = "design",
        tabName = "Дизайн"
    ),
    ANALYTICS(
        department = "analytics",
        tabName = "Аналитика"
    ),
    MANAGEMENT(
        department = "management",
        tabName = "Менеджмент"
    ),
    IOS(
        department = "ios",
        tabName = "iOS"
    ),
    ANDROID(
        department = "android",
        tabName = "Android"
    ),
    QA(
        department = "qa",
        tabName = "QA"
    ),
    BACK_OFFICE(
        department = "back_office",
        tabName = "Бэк-офис"
    ),
    FRONTEND(
        department = "frontend",
        tabName = "Frontend"
    ),
    HR(
        department = "hr",
        tabName = "HR"
    ),
    PR(
        department = "pr",
        tabName = "PR"
    ),
    BACKEND(
        department = "backend",
        tabName = "Backend"
    ),
    SUPPORT(
        department = "support",
        tabName = "Техподдержка"
    )
}
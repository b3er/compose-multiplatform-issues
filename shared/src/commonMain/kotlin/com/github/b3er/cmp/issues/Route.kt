package com.github.b3er.cmp.issues

sealed interface Route {
    data object Main : Route
}

sealed interface Issue : Route {
    val title: String
    val content: IssueContentComposable
    val platforms: List<Platform>
    val introducedIn: String
    val fixedIn: String?
}

package com.github.b3er.cmp.issues

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.b3er.cmp.issues.issue.ContentOverlapsWithKeyboardSuggestions
import com.github.b3er.cmp.issues.issue.NoCopyPopupInSelectionContainer

enum class IssueItem(
    override val title: String,
    override val content: IssueContentComposable,
    override val platforms: List<Platform>,
    override val introducedIn: String,
    override val fixedIn: String? = null
) : Issue {
    ContentOverlapsWithKeyboardSuggestions(
        title = "Content overlaps with keyboard suggestions",
        platforms = listOf(Platform.iOS),
        introducedIn = "1.6.0",
        content = { modifier, onExit ->
            ContentOverlapsWithKeyboardSuggestions(modifier, onExit)
        }
    ),
    NoCopyPopupInSelectionContainer(
        title = "No 'Copy' popup in SelectionContainer",
        platforms = listOf(Platform.iOS),
        introducedIn = "1.6.0",
        content = { modifier, onExit ->
            NoCopyPopupInSelectionContainer(modifier, onExit)
        }
    )
}

sealed interface Issue : Route {
    val title: String
    val content: IssueContentComposable
    val platforms: List<Platform>
    val introducedIn: String
    val fixedIn: String?
}

typealias IssueContentComposable = @Composable Issue.(Modifier, onExit: () -> Unit) -> Unit

enum class Platform {
    Android, iOS
}

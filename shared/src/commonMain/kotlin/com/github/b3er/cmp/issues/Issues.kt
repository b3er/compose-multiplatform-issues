package com.github.b3er.cmp.issues

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.b3er.cmp.issues.issue.*

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
        fixedIn = "1.6.10-dev1583",
        content = { modifier, onExit ->
            ContentOverlapsWithKeyboardSuggestions(modifier, onExit)
        }
    ),
    NoCopyPopupInSelectionContainer(
        title = "No 'Copy' popup in SelectionContainer",
        platforms = listOf(Platform.iOS),
        introducedIn = "1.6.0",
        fixedIn = "1.6.10-beta03",
        content = { modifier, onExit ->
            NoCopyPopupInSelectionContainer(modifier, onExit)
        }
    ),
    BottomSheetOverlapsWithKeyboard(
        title = "Bottom sheet overlaps with keyboard",
        platforms = listOf(Platform.iOS),
        introducedIn = "1.5",
        fixedIn = "1.6.0",
        content = { modifier, onExit ->
            BottomSheetOverlapsWithKeyboard(modifier, onExit)
        }
    ),
    WrongMultilineSnackbarPadding(
        title = "Wrong bottom padding in 3+ line snackbar",
        platforms = listOf(Platform.iOS, Platform.Android),
        introducedIn = "initial",
        content = { modifier, onExit ->
            WrongMultilineSnackbarPadding(modifier, onExit)
        }
    ),
    WrongTextStyleAlignment(
        title = "Wrong text style alignment on Trim.None",
        platforms = listOf(Platform.iOS),
        introducedIn = "initial",
        content = { modifier, onExit ->
            WrongTextStyleAlignment(modifier, onExit)
        },
        fixedIn = "1.8.0-dev1890"
    ),
    NativeCrashAfterResume(
        title = "Native crash on resume",
        platforms = listOf(Platform.iOS),
        introducedIn = "initial",
        content = { modifier, onExit ->
            NativeCrashAfterResume(modifier, onExit)
        }
    );

    companion object {
        val issuesList = entries.sortedBy { !it.fixedIn.isNullOrEmpty() }
    }
}


typealias IssueContentComposable = @Composable Issue.(Modifier, onExit: () -> Unit) -> Unit

enum class Platform {
    Android, iOS
}

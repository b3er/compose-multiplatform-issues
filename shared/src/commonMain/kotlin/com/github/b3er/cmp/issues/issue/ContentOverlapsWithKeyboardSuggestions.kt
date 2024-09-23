@file:OptIn(ExperimentalComposeUiApi::class)

package com.github.b3er.cmp.issues.issue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.b3er.cmp.issues.Issue
import com.github.b3er.cmp.issues.component.IssueScaffold
import kotlinx.coroutines.flow.StateFlow

/**
 * When changing the keyboard type from no suggestions to suggestions,
 * the content will be overlapped *sometimes* by the suggestions.
 * introduced in: 1.6.0
 * @see androidx.compose.ui.window.KeyboardVisibilityListener
 * @see androidx.compose.ui.window.KeyboardVisibilityListenerImpl
 */
@Composable
fun Issue.ContentOverlapsWithKeyboardSuggestions(
    modifier: Modifier = Modifier,
    onExit: () -> Unit
) {
    val keyboardSizeFromNotification by KeyboardHeightTracker.state.collectAsState()
    val insets = WindowInsets.ime.asPaddingValues().calculateBottomPadding()
    IssueScaffold(
        modifier = modifier.imePadding(),
        onExit = onExit,
        bottomBar = {
            BottomAppBar {
                Text("Bottom App Bar will be overlapped by keyboard suggestions.")
            }
        },
    ) {

    }
    IssueScaffold(
        modifier = modifier.imePadding(),
        onExit = onExit,
        bottomBar = {
            BottomAppBar {
                Text("Bottom App Bar will be overlapped by keyboard suggestions.")
            }
        },
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = "",
                onValueChange = { },
                modifier = Modifier.onFocusEvent {
                    if (it.isFocused) {
                        keyboardController?.hide()
                        keyboardController?.show()
                    }
                },
                label = { Text("Tap on this first") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = "",
                onValueChange = { },
                label = { Text("Then tap on this") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Real keyboard size: $keyboardSizeFromNotification")
            Text("Insets size: ${insets.value}")
        }
    }
}


expect object KeyboardHeightTracker {
    val state: StateFlow<Float>
}

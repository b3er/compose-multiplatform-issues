package com.github.b3er.cmp.issues.issue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.github.b3er.cmp.issues.Issue
import com.github.b3er.cmp.issues.component.IssueScaffold
import com.github.b3er.cmp.issues.component.IssueTopBar

/**
 * When changing the keyboard type from no suggestions to suggestions,
 * the content will be overlapped *sometimes* by the suggestions.
 * introduced in: 1.6.0
 */
@Composable
fun Issue.ContentOverlapsWithKeyboardSuggestions(
    modifier: Modifier = Modifier,
    onExit: () -> Unit
) {
    IssueScaffold(
        modifier = modifier.windowInsetsPadding(WindowInsets.ime),
        onExit = onExit,
        bottomBar = {
            BottomAppBar {
                Text("Bottom App Bar will be overlapped by keyboard suggestions.")
            }
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = "",
                onValueChange = { },
                label = { Text("Tap on this first") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = "",
                onValueChange = { },
                label = { Text("Then tap on this") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }
    }
}

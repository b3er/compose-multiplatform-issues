package com.github.b3er.cmp.issues.issue

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.b3er.cmp.issues.Issue
import com.github.b3er.cmp.issues.component.IssueScaffold

/**
 * When selecting text in SelectionContainer, no 'Copy' popup is shown
 * introduced in: 1.6.0
 */
@Composable
fun Issue.NoCopyPopupInSelectionContainer(modifier: Modifier = Modifier, onExit: () -> Unit) {
    IssueScaffold(
        onExit = onExit,
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
            SelectionContainer {
                Text("Tap to select this text. No 'Copy' popup will be shown.")
            }
        }
    }
}

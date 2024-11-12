package com.github.b3er.cmp.issues.issue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.b3er.cmp.issues.Issue
import com.github.b3er.cmp.issues.component.IssueScaffold

/**
 * SIGSEGV crash when changing focus after app restore from recents
 */
@Composable
fun Issue.NativeCrashAfterResume(
    modifier: Modifier = Modifier,
    onExit: () -> Unit
) {
    IssueScaffold(
        modifier = modifier.imePadding(),
        onExit = onExit,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "After restore from recents try to cycle focus with taps" +
                    " on these text fields," +
                    " if you're lucky bastard, app will crash with SIGSEGV.",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            var value1 by rememberSaveable { mutableStateOf("") }
            var value2 by rememberSaveable { mutableStateOf("") }
            TextField(
                value = value1,
                onValueChange = { value1 = it},
                label = { Text("Tap me") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = value2,
                onValueChange = { value2 = it},
                label = { Text("Tap me too") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }
    }
}

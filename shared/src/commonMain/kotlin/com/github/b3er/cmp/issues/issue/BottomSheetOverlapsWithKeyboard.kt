package com.github.b3er.cmp.issues.issue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.b3er.cmp.issues.Issue
import com.github.b3er.cmp.issues.component.AppModalBottomSheet
import com.github.b3er.cmp.issues.component.IssueScaffold

/**
 * Bottom sheet overlaps with keyboard.
 * introduced in: 1.6.0
 */
@Composable
fun Issue.BottomSheetOverlapsWithKeyboard(
    modifier: Modifier = Modifier,
    onExit: () -> Unit
) {
    IssueScaffold(
        modifier = modifier.windowInsetsPadding(WindowInsets.ime),
        onExit = onExit,
    ) {
        var isSheetVisible by remember { mutableStateOf(false) }
        AppModalBottomSheet(
            isVisible = isSheetVisible,
            modifier = Modifier.fillMaxWidth(),
            windowInsets = BottomSheetDefaults.windowInsets.union(WindowInsets.ime),
            onDismissRequest = { isSheetVisible = false }
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = "test",
                    onValueChange = { },
                    modifier = Modifier.fillMaxWidth().padding(top = 32.dp, bottom = 64.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Button(onClick = { isSheetVisible = false }, modifier = Modifier.fillMaxWidth()) {
                    Text("Close")
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                isSheetVisible = true
            }) {
                Text("Show Bottom Sheet")
            }
        }
    }
}

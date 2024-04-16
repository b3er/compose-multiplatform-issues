package com.github.b3er.cmp.issues.issue

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.b3er.cmp.issues.Issue
import com.github.b3er.cmp.issues.component.IssueScaffold
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun Issue.WrongMultilineSnackbarPadding(
    modifier: Modifier = Modifier,
    onExit: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    IssueScaffold(
        modifier = modifier,
        onExit = onExit,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            ) { data ->
                Snackbar(snackbarData = data)
            }
        },
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            val scope = rememberCoroutineScope()
            var snackbarJob by remember { mutableStateOf<Job?>(null) }
            fun showSnackbar(text: String) {
                snackbarJob?.cancel()
                snackbarJob = scope.launch {
                    snackbarHostState.showSnackbar(text)
                }
            }
            Button(onClick = {
                showSnackbar("This is a snackbar")
            }) {
                Text("Show 1 line snackbar")
            }
            Button(onClick = {
                showSnackbar(
                    "This is a 2 snackbar\nthat " +
                        "should have no issues"
                )
            }) {
                Text("Show 2 line snackbar")
            }
            Button(onClick = {
                showSnackbar(
                    "This is a multiline snackbar\nthat " +
                        "should have issues\nwith padding on the bottom"
                )
            }) {
                Text("Show 3 line snackbar")
            }
        }
    }
}


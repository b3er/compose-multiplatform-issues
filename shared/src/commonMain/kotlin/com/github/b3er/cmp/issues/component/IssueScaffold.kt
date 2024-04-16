package com.github.b3er.cmp.issues.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.b3er.cmp.issues.BuildKonfig
import com.github.b3er.cmp.issues.Issue

/**
 * Standard scaffold for an issue screen.
 */
@Composable
fun Issue.IssueScaffold(
    onExit: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHost: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) = Scaffold(
    modifier = modifier,
    snackbarHost = snackbarHost,
    bottomBar = bottomBar,
    topBar = { IssueTopBar(onExit = onExit) },
) { contentPadding ->
    Box(modifier = Modifier.fillMaxSize().padding(contentPadding)) {
        content()
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "kotlin: ${BuildKonfig.kotlinVVersion}",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = "compose: ${BuildKonfig.composeVersion}",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

/**
 * Top bar for an issue screen.
 */
@Composable
fun Issue.IssueTopBar(onExit: () -> Unit) = TopAppBar(
    title = { Text(title) },
    navigationIcon = {
        IconButton(onClick = onExit) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "back"
            )
        }
    },
)

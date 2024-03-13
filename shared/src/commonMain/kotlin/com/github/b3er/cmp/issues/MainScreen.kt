package com.github.b3er.cmp.issues

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.github.b3er.cmp.issues.theme.MyApplicationTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var route by remember { mutableStateOf<Route>(Route.Main) }
    MyApplicationTheme {
        when (val path = route) {
            Route.Main -> IssuesList(modifier) { route = it }

            is Issue -> with(path) { content(modifier) { route = Route.Main } }
        }
    }
}

sealed interface Route {
    data object Main : Route
}

@Composable
private fun IssuesList(modifier: Modifier = Modifier, navigateToIssue: (Issue) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
        LazyColumn(
            modifier = modifier.padding(contentPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(IssueItem.entries) { issue ->
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = { navigateToIssue(issue) }) {
                    Text(issue.title, style = LocalTextStyle.current.let {
                        if (issue.fixedIn != null) it.copy(textDecoration = TextDecoration.LineThrough) else it
                    })
                }
            }
        }
    }
}






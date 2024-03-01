package com.github.b3er.cmp.issues

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController

@Suppress("unused", "FunctionName")
fun MainScreenController() = ComposeUIViewController {
    MainScreen(modifier = Modifier.fillMaxSize())
}

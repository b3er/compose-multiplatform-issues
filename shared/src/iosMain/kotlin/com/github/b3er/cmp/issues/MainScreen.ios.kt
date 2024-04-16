@file:OptIn(ExperimentalForeignApi::class)

package com.github.b3er.cmp.issues

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalComposeApi::class)
@Suppress("unused", "FunctionName")
fun MainScreenController() = ComposeUIViewController(
    configure = {
        platformLayers = true
        onFocusBehavior = OnFocusBehavior.DoNothing
    }
) {
    MainScreen(modifier = Modifier.fillMaxSize())
}



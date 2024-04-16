@file:OptIn(ExperimentalForeignApi::class)

package com.github.b3er.cmp.issues

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSValue
import platform.UIKit.*
import platform.posix.printf

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



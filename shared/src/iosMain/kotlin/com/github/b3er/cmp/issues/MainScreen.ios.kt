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
import platform.UIKit.CGRectValue
import platform.UIKit.UIKeyboardDidHideNotification
import platform.UIKit.UIKeyboardDidShowNotification
import platform.UIKit.UIKeyboardFrameEndUserInfoKey
import platform.posix.printf

@OptIn(ExperimentalComposeApi::class)
@Suppress("unused", "FunctionName")
fun MainScreenController() = ComposeUIViewController(
    configure = {
        platformLayers = false
        onFocusBehavior = OnFocusBehavior.DoNothing
    }
) {
    MainScreen(modifier = Modifier.fillMaxSize())
}

actual object KeyboardHeightTracker {
    val _state = MutableStateFlow(0f)
    actual val state = _state.asStateFlow()

    init {
        NSNotificationCenter.defaultCenter.addObserverForName(
            UIKeyboardDidShowNotification,
            null,
            NSOperationQueue.mainQueue
        ) { notification ->
            (notification?.userInfo?.get(UIKeyboardFrameEndUserInfoKey))?.also { rect ->
                (rect as? NSValue)?.CGRectValue?.useContents {
                    _state.value = size.height.toFloat()
                }
            }
        }
        NSNotificationCenter.defaultCenter.addObserverForName(
            UIKeyboardDidHideNotification,
            null,
            NSOperationQueue.mainQueue
        ) { notification ->
            _state.value = 0f
        }
    }
}

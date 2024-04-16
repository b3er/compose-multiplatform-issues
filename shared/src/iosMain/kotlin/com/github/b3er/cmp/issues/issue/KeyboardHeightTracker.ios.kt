package com.github.b3er.cmp.issues.issue

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSValue
import platform.UIKit.CGRectValue
import platform.UIKit.UIKeyboardFrameEndUserInfoKey
import platform.UIKit.UIKeyboardWillHideNotification
import platform.UIKit.UIKeyboardWillShowNotification

@OptIn(ExperimentalForeignApi::class)
actual object KeyboardHeightTracker {
    private val _state = MutableStateFlow(0f)
    actual val state = _state.asStateFlow()

    init {
        NSNotificationCenter.defaultCenter.addObserverForName(
            UIKeyboardWillShowNotification,
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
            UIKeyboardWillHideNotification,
            null,
            NSOperationQueue.mainQueue
        ) { notification ->
            _state.value = 0f
        }
    }
}

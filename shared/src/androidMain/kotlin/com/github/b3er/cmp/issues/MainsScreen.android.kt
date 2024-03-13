package com.github.b3er.cmp.issues

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

actual object KeyboardHeightTracker {
    val _state = MutableStateFlow(0f)
    actual val state: StateFlow<Float> = _state.asStateFlow()
}

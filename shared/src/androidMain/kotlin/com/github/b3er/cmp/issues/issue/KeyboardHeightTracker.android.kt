package com.github.b3er.cmp.issues.issue

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

actual object KeyboardHeightTracker {
    private val _state = MutableStateFlow(0f)
    actual val state: StateFlow<Float> = _state.asStateFlow()
}

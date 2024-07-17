@file:OptIn(ExperimentalForeignApi::class)

package com.github.b3er.cmp.issues

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import com.github.b3er.cmp.issues.resources.Inter_Black
import com.github.b3er.cmp.issues.resources.Inter_Regular
import com.github.b3er.cmp.issues.resources.Res
import kotlinx.cinterop.ExperimentalForeignApi
import org.jetbrains.compose.resources.Font
import platform.UIKit.UIFont
import platform.UIKit.UIFontDescriptor

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



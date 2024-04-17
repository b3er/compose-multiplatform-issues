package com.github.b3er.cmp.issues.issue

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.b3er.cmp.issues.Issue
import com.github.b3er.cmp.issues.component.IssueScaffold

@Composable
fun Issue.WrongTextStyleAlignment(modifier: Modifier = Modifier, onExit: () -> Unit) {
    IssueScaffold(
        onExit = onExit,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                val textModifier = Modifier.border(
                    BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text("Center\naligned", style = CenterAlignedTextStyle, modifier = textModifier)
                Text("Top\naligned", style = TopAlignedTextStyle, modifier = textModifier)
                Text("Bottom\naligned", style = BottomAlignedTextStyle, modifier = textModifier)
                Text(
                    "Proportional\naligned",
                    style = ProportionalAlignedTextStyle,
                    modifier = textModifier
                )
            }
        }
    }
}

private val IssueTextStyle = TextStyle(
    fontSize = 16.sp,
    lineHeight = 32.sp,
    fontWeight = FontWeight.Normal,
)

private val CenterAlignedTextStyle = IssueTextStyle.copy(
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
private val TopAlignedTextStyle = IssueTextStyle.copy(
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Top,
        trim = LineHeightStyle.Trim.None
    )
)

private val BottomAlignedTextStyle = IssueTextStyle.copy(
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Bottom,
        trim = LineHeightStyle.Trim.None
    )
)
private val ProportionalAlignedTextStyle = IssueTextStyle.copy(
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Proportional,
        trim = LineHeightStyle.Trim.None
    )
)

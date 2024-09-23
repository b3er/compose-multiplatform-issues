package com.github.b3er.cmp.issues.issue

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.util.fastForEach
import com.github.b3er.cmp.issues.Issue
import com.github.b3er.cmp.issues.component.IssueScaffold

/**
 * The [LineHeightStyle.Trim] for iOs is not working properly in compose multiplatform
 * @see <a href="https://issuetracker.google.com/issues/202443559">Issue-202443559</a>
 * @see <a href="https://github.com/JetBrains/compose-multiplatform-core/pull/897">PR-897</a>
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Issue.WrongTextStyleAlignment(modifier: Modifier = Modifier, onExit: () -> Unit) {
    IssueScaffold(
        onExit = onExit,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            trimModes.fastForEach { trim ->
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val textModifier = Modifier.border(
                        BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    alignmentModes.fastForEach { alignment ->
                        Text(
                            alignment
                                .toString()
                                .split(".")
                                .lastOrNull() +
                                "\n" +
                                trim
                                    .toString()
                                    .split(".")
                                    .lastOrNull(),
                            style = IssueTextStyle.copy(
                                lineHeightStyle = LineHeightStyle(
                                    alignment,
                                    trim
                                )
                            ),
                            modifier = textModifier
                        )
                    }
                }
            }
        }
    }
}

private val IssueTextStyle = TextStyle(
    fontSize = 16.sp,
    lineHeight = 32.sp,
    fontWeight = FontWeight.Normal,
)

private val alignmentModes = listOf(
    LineHeightStyle.Alignment.Center,
    LineHeightStyle.Alignment.Top,
    LineHeightStyle.Alignment.Bottom,
    LineHeightStyle.Alignment.Proportional
)
private val trimModes = listOf(
    LineHeightStyle.Trim.None,
    LineHeightStyle.Trim.FirstLineTop,
    LineHeightStyle.Trim.LastLineBottom,
    LineHeightStyle.Trim.Both
)

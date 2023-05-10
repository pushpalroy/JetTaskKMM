package com.jetapps.jettaskboard.carddetailscomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Attachment
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jettaskboard.multiplatform.ui.theme.LabelBlue
import com.jettaskboard.multiplatform.ui.theme.LabelGreen
import com.jettaskboard.multiplatform.ui.theme.LabelPeach
import org.jetbrains.compose.resources.painterResource

@Composable
fun QuickActionsCard(isExpanded: Boolean = false) {
    val showQuickActions = rememberSaveable { mutableStateOf(false) }
    val rotate by animateFloatAsState(
        targetValue = if (showQuickActions.value) 180f else 0f,
        tween(500)
    )

    Column {
        if (!isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("quick_action_card")
                    .clickable { showQuickActions.value = !showQuickActions.value },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Quick Actions",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )

                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .rotate(rotate),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "expand"
                )
            }
        }

        AnimatedVisibility(showQuickActions.value || isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    QuickActionChip(
                        modifier = Modifier.weight(1f)
                            .testTag("quick_action_chip"),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.CheckCircle,
                                contentDescription = "Add Checklist",
                                Modifier.size(16.dp),
                                tint = LabelGreen
                            )
                        },
                        title = "Add CheckList"
                    )

                    QuickActionChip(
                        modifier = Modifier.weight(1f),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Attachment,
                                contentDescription = "Add Attachment",
                                Modifier.size(16.dp),
                                tint = LabelBlue
                            )
                        },
                        title = "Add Attachment"
                    )
                }

                Row {
                    QuickActionChip(
                        modifier = Modifier.weight(1f),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = "Add Members",
                                Modifier.size(16.dp),
                                tint = LabelPeach
                            )
                        },
                        title = "Add Members"
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

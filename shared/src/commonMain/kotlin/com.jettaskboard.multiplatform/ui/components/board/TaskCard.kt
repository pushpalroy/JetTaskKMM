package com.jettaskboard.multiplatform.ui.components.board

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jettaskboard.multiplatform.domain.model.CardModel
import com.jettaskboard.multiplatform.util.asyncimage.AsyncImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    card: CardModel,
    isExpandedScreen: Boolean
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color(0xFF2c2c2e)
    ) {
        Column(
            modifier = Modifier
        ) {
            card.coverImageUrl?.let { safeCoverImageUrl ->
                if (safeCoverImageUrl.isNotEmpty()) {
                    AsyncImage(
                        imageUrl = safeCoverImageUrl,
                        contentDescription = "Cover Image",
                        modifier = Modifier.height(60.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                card.labels.let { cardLabels ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        for (label in cardLabels) {
                            Box(
                                modifier = Modifier
                                    .width(32.dp)
                                    .height(16.dp)
                                    .clip(RoundedCornerShape(10))
                                    .background(color = Color(label.labelColor))
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier,
                    text = card.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                card.description?.let { safeDescription ->
                    if (safeDescription.isNotEmpty() && isExpandedScreen) {
                        Text(
                            modifier = Modifier,
                            text = safeDescription,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 10.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    card.description?.let { safeDescription ->
                        if (safeDescription.isNotEmpty()) {
                            Icon(
                                modifier = Modifier,
                                painter = painterResource("ic_notes.xml"),
                                tint = Color.White,
                                contentDescription = null
                            )
                        }
                    }
                    Icon(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .rotate(130f),
                        painter = painterResource("ic_attachment.xml"),
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

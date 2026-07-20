package com.example.utilities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.movies.ui.theme.AppTypography

@Composable
fun ExpandableText(
    text: String,
    collapsedLines: Int = 6
) {
    var expanded by remember { mutableStateOf(false) }
    var isOverflowing by remember { mutableStateOf(false) }
    Column {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = if (expanded) Int.MAX_VALUE else collapsedLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = {
                isOverflowing = it.hasVisualOverflow
            }
        )
        if (isOverflowing  || expanded) {
            Text(
                text = if (expanded) "Show less" else "Show more",
                color = MaterialTheme.colorScheme.onBackground,
                style = AppTypography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .padding(top = 6.dp)
                    .clickable {
                        expanded = !expanded
                    }
            )
        }
    }
}
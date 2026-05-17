package kz.technopark.edoox.shared.coreui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.technopark.edoox.shared.coreui.icons.ArrowLeft
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme

@Composable
fun NavBar(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true,
    onBackClick: () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Row(
        modifier = modifier.fillMaxWidth().background(containerColor).padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showBackButton) {
            IconButton(onClick = onBackClick) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = ArrowLeft,
                    contentDescription = null,
                    tint = contentColor,
                )
            }
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = contentColor,
        )
    }
}


@Preview
@Composable
fun NavBarPreview() {
    EdooxTheme(darkTheme = true) {
        NavBar(
            title = "Обзор результатов",
            showBackButton = true,
            onBackClick = {},
        )
    }
}
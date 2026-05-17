package kz.technopark.edoox.feature.presentation.subject.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kz.technopark.edoox.feature.presentation.subject.model.SubjectDvo


@Composable
fun SubjectCard(
    subject: SubjectDvo,
    onClick: (SubjectDvo) -> Unit,
) {
    val cardContainerColor = remember(subject) {
        if (subject.isSelected) {
            subject.color
        } else {
            subject.color.copy(alpha = 0.15F)
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth().height(200.dp).clickable { onClick(subject) },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardContainerColor,
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.size(80.dp).background(
                    subject.color, RoundedCornerShape(12.dp)
                ), contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    imageVector = subject.icon,
                    contentDescription = null,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = subject.name,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,

                )
        }
    }
}
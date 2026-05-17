package kz.technopark.edoox.feature.presentation.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edoox.composeapp.generated.resources.Res
import edoox.composeapp.generated.resources.img_flag_kzt
import edoox.composeapp.generated.resources.img_flag_rub
import kz.technopark.edoox.shared.core.AppLanguage
import org.jetbrains.compose.resources.painterResource

@Composable
fun LanguageBottomSheetContent(
    currentLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 48.dp)
    ) {
        Text(
            text = "Выберите язык / Тілді таңдаңыз",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        AppLanguage.entries.forEach { language ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLanguageSelected(language) }
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        when(language) {
                            AppLanguage.RU ->Res.drawable.img_flag_rub
                            AppLanguage.KK -> Res.drawable.img_flag_kzt
                        }
                    ),
                    contentDescription = null,
                )


                Text(
                    text = language.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (currentLanguage == language) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
                
                RadioButton(
                    selected = (currentLanguage == language),
                    onClick = { onLanguageSelected(language) }
                )
            }
        }
    }
}
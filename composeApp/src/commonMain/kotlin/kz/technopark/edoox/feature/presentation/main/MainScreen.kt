package kz.technopark.edoox.feature.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edoox.composeapp.generated.resources.Res
import edoox.composeapp.generated.resources.img_school
import kz.technopark.edoox.feature.presentation.main.view.LanguageBottomSheetContent
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme
import kz.technopark.edoox.shared.coreui.view.DarkModeSwitch
import kz.technopark.edoox.shared.coreui.view.PrimaryButton
import kz.technopark.edoox.shared.coreui.view.SecondaryButton
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import kz.technopark.edoox.shared.core.AppLanguage
import kz.technopark.edoox.shared.coreui.theme.language.AppStrings

@Composable
fun MainScreenEntryPoint() {
    val viewModel: MainViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    MainScreen(
        uiState = uiState,
        controller = viewModel,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainUIState,
    controller: MainController,
) {
    val sheetState = rememberModalBottomSheetState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DarkModeSwitch(
                modifier = Modifier.align(Alignment.End).padding(top = 16.dp, end = 16.dp),
                checked = uiState.isDarkTheme,
                onCheckedChanged = controller::onChangeTheme
            )

            Spacer(modifier = Modifier.weight(1F))
            Image(painterResource(Res.drawable.img_school), null)
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "EdooX.com",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.weight(1F))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                text = AppStrings.current.start,
                onClick = controller::onContinueClick,
            )

            Spacer(modifier = Modifier.height(8.dp))

            SecondaryButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 24.dp),
                text = AppStrings.current.switchLanguage,
                onClick = controller::onChangeLanguageClick,
            )
        }

        if (uiState.showLanguageBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { controller.onDismissLanguageSheet() },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ) {
                LanguageBottomSheetContent(
                    currentLanguage = uiState.currentLanguage,
                    onLanguageSelected = { controller.onLanguageSelected(it) }
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() = EdooxTheme(darkTheme = true) {
    MainScreen(
        uiState = MainUIState(
            isDarkTheme = true,
        ),
        controller = object : MainController {
            override fun onChangeTheme(isDarkTheme: Boolean) = Unit
            override fun onChangeLanguageClick() = Unit
            override fun onContinueClick() = Unit
            override fun onLanguageSelected(language: AppLanguage) = Unit
            override fun onDismissLanguageSheet() = Unit
        },
    )
}
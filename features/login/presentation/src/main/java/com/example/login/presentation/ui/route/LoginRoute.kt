package com.example.login.presentation.ui.route

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicBackground
import com.example.designsystem.resources.figma.colors.basicWhite
import com.example.designsystem.resources.figma.typography.FigmaTypography
import com.example.login.presentation.ui.viewmodel.LoginViewModel
import com.example.login.presentation.ui.viewmodel.SideEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import com.example.core.localization.R as LocalizableResources

@Composable
internal fun LoginRoute(
    viewModel: LoginViewModel,
    showSnackbar: suspend (message: String) -> Unit
) {
    val context = LocalContext.current

    LoginScreen(
        signInWithGoogle = {
            viewModel.onGoogleSignInClicked(context = context)
        }
    )

    HandleSideEffects(
        sideEffect = viewModel.sideEffect,
        showSnackbar = showSnackbar,
        onGoogleSignInResult = { activityResult ->
            activityResult.data?.let { intent ->
                viewModel.onGoogleSignInResult(intent)
            }
        }
    )
}

@Composable
fun LoginScreen(signInWithGoogle: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FigmaColors.basicBackground())
            .padding(horizontal = Dimens.spacingLarge)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(all = Dimens.spacingNormal),
            onClick = signInWithGoogle
        ) {
            Text(
                text = stringResource(id = LocalizableResources.string.button_login_title).uppercase(),
                color = FigmaColors.basicWhite(),
                style = FigmaTypography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(Dimens.spacingExtraSpecial))
    }
}

@Composable
internal fun HandleSideEffects(
    sideEffect: Flow<SideEffect>,
    showSnackbar: suspend (message: String) -> Unit,
    onGoogleSignInResult: (result: ActivityResult) -> Unit
) {
    val googleSignInResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        onGoogleSignInResult(result)
    }

    LaunchedEffect(Unit) {
        sideEffect.collectLatest { sideEffectState ->
            when (sideEffectState) {
                is SideEffect.ShowErrorSnackBar -> {
                    showSnackbar(sideEffectState.message)
                }

                is SideEffect.LaunchGoogleSignInIntent -> {
                    googleSignInResultLauncher.launch(sideEffectState.intent)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        signInWithGoogle = {},
    )
}
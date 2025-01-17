package com.example.login.domain.usecases

import android.content.Context
import android.content.Intent
import com.example.login.domain.constants.LoginConstants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import javax.inject.Inject

class CreateGoogleSignInIntentUseCase @Inject constructor() {
    operator fun invoke(context: Context): Intent {
        val googleSignIn = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .requestIdToken(LoginConstants.GOOGLE_AUTH_ID_TOKEN)
            .build()
        return GoogleSignIn.getClient(context, googleSignIn).signInIntent
    }
}
package com.example.login.domain.usecases

import android.content.Intent
import com.example.common.response.ErrorCodes
import com.example.common.response.ErrorData
import com.example.common.response.Response
import com.example.shared.user.models.domain.UserDomainModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetGoogleSignInDataUseCase @Inject constructor() {
    suspend operator fun invoke(intent: Intent) = try {
        val accountInfo = GoogleSignIn.getSignedInAccountFromIntent(intent).await()
        if (accountInfo != null) {
            Response.Success(accountInfo.toDomainModel())
        } else {
            Response.Error(ErrorData(ErrorCodes.GOOGLE_SIGN_IN_ERROR, null))
        }
    } catch (exception: Exception) {
        if (exception is ApiException && exception.statusCode == GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
            Response.Error(ErrorData(ErrorCodes.GOOGLE_SIGN_IN_CANCELED, null))
        } else {
            Response.Error(ErrorData(ErrorCodes.GOOGLE_SIGN_IN_ERROR, exception.message))
        }
    }

    private fun GoogleSignInAccount.toDomainModel(): UserDomainModel =
        UserDomainModel(
            accessKey = idToken.orEmpty(),
            email = email.orEmpty(),
            userName = displayName,
            profileImageUrl = photoUrl?.toString()
        )
}
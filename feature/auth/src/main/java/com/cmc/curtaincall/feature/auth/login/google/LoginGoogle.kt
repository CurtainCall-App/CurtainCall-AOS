package com.cmc.curtaincall.feature.auth.login.google

import android.app.Activity
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
private fun LoginGoogleButton(
    onNavigateSignUpTerms: () -> Unit = {},
    onNavigateHome: () -> Unit = {}
) {
    val context = LocalContext.current
    val registerGoogleLogin = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { intent ->
                val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                task.result.idToken?.let { idToken ->
                    onNavigateSignUpTerms()
                }
            }
        }
    }

    Image(
        painter = painterResource(R.drawable.ic_google_login),
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .clickable {
                val signInClient = getGoogleSignInClient(context)
                registerGoogleLogin.launch(signInClient.signInIntent)
            }
    )
}

private fun getGoogleSignInClient(context: Context): GoogleSignInClient =
    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(context.getString(com.cmc.curtaincall.feature.auth.R.string.google_web_client_id))
        .build()
        .let { GoogleSignIn.getClient(context, it) }

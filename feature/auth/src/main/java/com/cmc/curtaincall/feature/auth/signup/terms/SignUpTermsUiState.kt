package com.cmc.curtaincall.feature.auth.signup.terms

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.cmc.curtaincall.domain.model.Urls.DEFAULT_URL

@Stable
internal class SignUpTermsUiState {
    var webUrl: String by mutableStateOf(DEFAULT_URL)
    var serviceUseTerms: Boolean by mutableStateOf(false)
    var privacyInfoTerms: Boolean by mutableStateOf(false)
    var userAgreeTerms: Boolean by mutableStateOf(false)
    fun isQualified() = serviceUseTerms && privacyInfoTerms && userAgreeTerms
}

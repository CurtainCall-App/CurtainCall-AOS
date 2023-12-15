package com.cmc.curtaincall.feature.auth.signup.terms

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

internal data class SignUpTermsState(
    val serviceTerms: MutableState<Boolean> = mutableStateOf(false),
    val privacyInfoTerms: MutableState<Boolean> = mutableStateOf(false),
    val ageTerms: MutableState<Boolean> = mutableStateOf(false)
) {
    fun isAllChecked() = serviceTerms.value && privacyInfoTerms.value && ageTerms.value
    fun clickedAllTerms() {
        if (isAllChecked()) {
            disagreeAllTerms()
        } else {
            agreeAllTerms()
        }
    }

    fun clickedServiceTerms() {
        serviceTerms.value = serviceTerms.value.not()
    }

    fun clickedPrivacyInfoTerms() {
        privacyInfoTerms.value = privacyInfoTerms.value.not()
    }

    fun clickedAgeTerms() {
        ageTerms.value = ageTerms.value.not()
    }

    private fun agreeAllTerms() {
        serviceTerms.value = true
        privacyInfoTerms.value = true
        ageTerms.value = true
    }

    private fun disagreeAllTerms() {
        serviceTerms.value = false
        privacyInfoTerms.value = false
        ageTerms.value = false
    }
}

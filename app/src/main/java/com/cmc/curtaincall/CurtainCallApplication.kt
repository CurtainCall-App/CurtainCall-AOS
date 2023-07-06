package com.cmc.curtaincall

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import timber.log.Timber

class CurtainCallApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}

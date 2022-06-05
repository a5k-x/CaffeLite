package com.a5k.caffelite.data.datasourse.account

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AuthenticatorService : Service() {

    override fun onBind(intent: Intent): IBinder {
        val authenticator = CustomAuthenticator(baseContext)
        return authenticator.iBinder
    }
}


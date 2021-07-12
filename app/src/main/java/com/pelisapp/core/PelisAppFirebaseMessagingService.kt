package com.pelisapp.core

import android.content.Intent
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pelisapp.MoviesFilterActivity
import java.util.concurrent.Executor
import java.util.logging.Handler

class PelisAppFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val mainExecutor: Executor = ContextCompat.getMainExecutor(this)
        mainExecutor.execute(Runnable {
            Toast.makeText(applicationContext, remoteMessage.notification?.body + ".\nCompletá tus filtros para continuar!",Toast.LENGTH_LONG).show()
        })

        val intent = Intent(this, MoviesFilterActivity::class.java).apply {}
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent)
    }

    fun getDeviceToken(listener: FirebaseMessagingListener) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            listener.onDeviceTokenReceived(task.result)
        });
    }

    fun getMessageKeyValue(key: String, activity: AppCompatActivity): String? {
        return activity.intent.getStringExtra(key);
    }
}
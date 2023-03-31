package dev.igokoro.moshi_enum

import android.app.Application
import zendesk.android.FailureCallback
import zendesk.android.SuccessCallback
import zendesk.android.Zendesk
import zendesk.logger.Logger
import zendesk.messaging.android.DefaultMessagingFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Logger.setLoggable(true)
        Zendesk.initialize(
            context = this,
            channelKey = "eyJzZXR0aW5nc191cmwiOiJodHRwczovL3o0bm5tdGVzdGFwcC56ZW5kZXNrLmNvbS9tb2JpbGVfc2RrX2FwaS9zZXR0aW5ncy8wMUVXSlRWNzdaWjNZUFpRVkRWNE5XMlc5Uy5qc29uIn0=",
            messagingFactory = DefaultMessagingFactory(),
            successCallback = SuccessCallback {

            },
            failureCallback = FailureCallback {

            }
        )

    }
}
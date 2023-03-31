package dev.igokoro.moshi_enum

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import zendesk.android.FailureCallback
import zendesk.android.SuccessCallback
import zendesk.android.Zendesk
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editTextJWT)
        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            Zendesk.instance.loginUser(
                jwt = editText.text.toString(),
                successCallback = SuccessCallback {
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                },
                failureCallback = FailureCallback {
                    Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                }

            )
        }
        findViewById<Button>(R.id.btnOpenConversation).setOnClickListener {
            Zendesk.instance.messaging.showMessaging(it.context)
        }


    }


}


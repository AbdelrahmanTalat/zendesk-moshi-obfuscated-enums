package dev.igokoro.moshi_enum

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        java()
        // uncomment to see that kotlin suffers from the same issue
//        kotlin()
    }

    private fun java() {
        Log.d("ENUM", "java")
        val enumConstants = DoomedEnum::class.java.enumConstants
        val declaredFields = DoomedEnum::class.java.declaredFields
        // notice that after obfuscation enum constants and field names are no longer the same!
        Log.d("ENUM", "constants: ${Arrays.toString(enumConstants)}")
        Log.d("ENUM", "fields: ${Arrays.toString(declaredFields)}")

        val moshi = Moshi.Builder().build()
        // NoSuchFieldException crash on getting adapter because EnumJsonAdapter is looking up all
        // enums in the type by trying to use enum constants to find field names
        // but the assumption that enum constant == field name does not hold true after obfuscation
        // the biggest problem here is that moshi is ALWAYS failing for EVERY obfuscated enum class
        // and it's not called out anywhere
        // the workaround, of cause, is to add keep rules for enums - but you only learn this the
        // hard way. Calling this out in docs ahead of time would be much better!
        val adapter = moshi.adapter(Model::class.java)
        val model = Model(DoomedEnum.A)
        Log.d("ENUM", "serializing model value: ${model.value}")
        val json = adapter.toJson(model)
        Log.d("ENUM", "json: $json")
    }

    private fun kotlin() {
        Log.d("ENUM", "kotlin")
        val enumConstants = DoomedEnumKt::class.java.enumConstants
        val declaredFields = DoomedEnumKt::class.java.declaredFields
        Log.d("ENUM", "constants: ${Arrays.toString(enumConstants)}")
        Log.d("ENUM", "fields: ${Arrays.toString(declaredFields)}")

        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(ModelKt::class.java)
        val model = ModelKt(DoomedEnumKt.B)
        Log.d("ENUM", "serializing model value: ${model.value}")
        val json = adapter.toJson(model)
        Log.d("ENUM", "json: $json")
    }
}


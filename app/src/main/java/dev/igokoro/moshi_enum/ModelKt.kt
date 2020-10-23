package dev.igokoro.moshi_enum

import com.squareup.moshi.JsonClass

enum class DoomedEnumKt{
    B
}

@JsonClass(generateAdapter = true)
data class ModelKt(
    val value: DoomedEnumKt
)
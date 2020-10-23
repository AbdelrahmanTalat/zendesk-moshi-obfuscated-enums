# moshi-obfuscated-enums
This sample illustrates the problem with javs/kotlin enums, obfuscation and moshi. 

Moshi ALWAYS fails to create adapter for ANY obfuscated enum class. This is because moshi makes an assumption that enum constant == field name which does not hold true for obfuscated enums: enum constants remain untouched by obfuscation while field names are all changed.

The workaround is to exclude any enums that is used with moshi from obfuscation. Or just exclude all enums from obfuscation like this:

```
-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
```

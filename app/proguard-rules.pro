# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.bakhus.rickandmortyapitry.models.* { *; }
-keep class com.bakhus.rickandmortyapitry.ui.main.viewmodel.* { *; }
-keep class com.bakhus.rickandmortyapitry.ui.base.* {*;}
-keep class com.bakhus.rickandmortyapitry.repostiory.* {*;}
-keep class com.bakhus.rickandmortyapitry.api.* {*;}
-keep class com.bakhus.rickandmortyapitry.utils.* {*;}
-keep class com.bakhus.rickandmortyapitry.ui.main.view.* {*;}
-keep class com.bakhus.rickandmortyapitry.ui.main.adapter.* {*;}
-keep class res.layout.*
-keep class com.bakhus.rickandmortyapitry.* {*;}
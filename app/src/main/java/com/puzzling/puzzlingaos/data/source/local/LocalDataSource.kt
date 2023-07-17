package com.puzzling.puzzlingaos.data.source.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object LocalDataSource {
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        val masterKeyAlias = MasterKey
            .Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        preferences = EncryptedSharedPreferences.create(
            context,
            "encrypted_settings",
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    fun setAccessToken(input: String) {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("ACCESS_TOKEN", input)
        editor.commit()
    }

    fun getAccessToken(): String {
        return preferences.getString("ACCESS_TOKEN", "").toString()
    }

    fun clear() {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.clear()
        editor.commit()
    }
}

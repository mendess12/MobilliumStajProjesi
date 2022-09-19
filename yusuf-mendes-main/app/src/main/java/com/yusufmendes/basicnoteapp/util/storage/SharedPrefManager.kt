package com.yusufmendes.basicnoteapp.util.storage

import android.content.Context
import com.yusufmendes.basicnoteapp.model.login.LoginData

class SharedPrefManager private constructor(mCtx: Context) {

    val sharedPreferences =
        mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val isLoggedIn: Boolean
        get() {
            return sharedPreferences.getInt("id", -1) != -1
        }

    val data: LoginData
        get() {
            return LoginData(
                sharedPreferences.getString("accessToken", null) ?: "",
                sharedPreferences.getString("tokenType", null) ?: ""
            )
        }

    fun saveUser(loginData: LoginData) {
        editor.putString("accessToken", loginData.accessToken)
        editor.putString("tokenType", loginData.tokenType)
        editor.apply()
    }

    fun clear() {
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }
}

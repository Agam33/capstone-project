package com.example.sportreservation.userpreferences

import android.content.Context

class UserPreference(context: Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(userModel: UserModel) =
        preferences.edit().apply {
            putString(NAME, userModel.name)
            putString(EMAIL, userModel.email)
            putString(ADDRESS, userModel.address)
            putString(PHONE, userModel.phone)
            putString(IMAGE, userModel.imgUrl)
            putBoolean(REGIS, true)
        }.apply()

    fun getUser(): UserModel =
        UserModel(
            preferences.getString(NAME, ""),
            preferences.getString(EMAIL, ""),
            preferences.getString(ADDRESS, ""),
            preferences.getString(PHONE, ""),
            preferences.getString(IMAGE, null),
        )

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val REGIS = "regis"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val ADDRESS = "address"
        private const val PHONE = "phone"
        private const val IMAGE = "img"
    }
}
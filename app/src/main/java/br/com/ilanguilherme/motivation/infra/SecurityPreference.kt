package br.com.ilanguilherme.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreference(val context: Context) {

    private val mSharedPreferences = context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    fun storeString(ket: String, value: String){
        mSharedPreferences.edit().putString(ket,value).apply()
    }

    fun getString(key: String): String{
        return mSharedPreferences.getString(key, "") ?: ""
    }
}
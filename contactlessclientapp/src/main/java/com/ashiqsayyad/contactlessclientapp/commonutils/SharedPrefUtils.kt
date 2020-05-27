package com.ashiqsayyad.contactlessclientapp.commonutils

import android.content.Context
import android.content.SharedPreferences

const val SHARED_PREF_NAME = "com.mysharedpref.unique"

object SharedPrefUtils {

    fun contains(context: Context, key:String):Boolean {
        val sharedPref= context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)?:return false
        return sharedPref.contains(key)

    }
    fun putString(context: Context,  key:String,value:String) {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)?: return
        with (sharedPref.edit()) {
            putString(key,value)
            commit()
        }
    }

    fun getString(context: Context, key:String,defValue:String):String {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)?: return defValue
        return sharedPref.getString(key,defValue)!!

    }

}
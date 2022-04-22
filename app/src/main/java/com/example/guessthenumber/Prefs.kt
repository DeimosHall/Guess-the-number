package com.example.guessthenumber

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

class Prefs {
    // Keys
    private val LANGUAGE = "language"
    private val LOWER_LIMIT = "lower_limit"
    private val UPPER_LIMIT = "upper_limit"
    private val GUESSING_METHOD = "guessing_method"

    // Values
    private var language = ""
    private var lowerLimit = 0
    private var upperLimit = 0
    private var guessingMethod = ""

    fun getPreferences(preferences: SharedPreferences, context: Context) {
        var error = "Ok"

        language = preferences.getString(LANGUAGE,"")!!

        try {
            lowerLimit = preferences.getInt(LOWER_LIMIT,0)
        } catch (e: Exception) {
            error = "Lower limit"
        }

        try {
            upperLimit = preferences.getInt(UPPER_LIMIT,0)
        } catch (e: Exception) {
            error = "Upper limit"
        }

        guessingMethod = preferences.getString(GUESSING_METHOD,"")!!

        var msg = "Language: ${language}\n" +
                "Lower limit: ${lowerLimit}\n" +
                "Upper limit: ${upperLimit}\n" +
                "Guessing method: ${guessingMethod}"



        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
}
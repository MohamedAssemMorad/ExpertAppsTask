package com.example.myapplication.util.extention

import android.util.Patterns
import com.example.myapplication.util.parsing.ParsingHelper
import java.lang.reflect.Type

fun <T> String.convertToModel(type: Type?): T? {
    return ParsingHelper.gson?.fromJson<T>(this, type)}

fun String?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()




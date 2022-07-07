package com.example.myapplication.repository.local

import com.efg.valu.sales.model.response.CacheEntry
import com.efg.valu.sales.repository.local.BaseLocalRepo
import com.example.myapplication.util.extention.convertToModel
import com.example.myapplication.util.parsing.ParsingHelper
import java.lang.reflect.Type

class BaseLocalRepoImpl : BaseLocalRepo {
    override fun getCachedObject(type: Type): Any? {
        val entry: CacheEntry<Any>? = SecureSharedPref.getObject(type)
        val cachedObject: Any? = entry?.obj
        return if (cachedObject != null) {
            val mCachedObject: Any? =
                ParsingHelper.gson?.toJson(cachedObject)?.convertToModel(type)
            mCachedObject
        } else null
    }

    override fun <T> saveObject(instance: T, type: Type, lastModifiedDate: Long) =
        SecureSharedPref.edit()
            .putObject(CacheEntry(instance, lastModifiedDate), type).apply()

    override fun saveString(key: String, strValue: String) =
        SecureSharedPref.edit()
            .putString(key, strValue).apply()
}
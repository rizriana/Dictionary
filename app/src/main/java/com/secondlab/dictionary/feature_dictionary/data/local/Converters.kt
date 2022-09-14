package com.secondlab.dictionary.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.secondlab.dictionary.feature_dictionary.data.util.JsonParser
import com.secondlab.dictionary.feature_dictionary.domain.model.Meaning
import javax.inject.Inject

@ProvidedTypeConverter
class Converters @Inject constructor(
    private val jsonParser: JsonParser,
) {

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> =
        jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String =
        jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
}
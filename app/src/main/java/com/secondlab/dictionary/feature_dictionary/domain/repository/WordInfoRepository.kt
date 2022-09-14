package com.secondlab.dictionary.feature_dictionary.domain.repository

import com.secondlab.dictionary.core.util.Resource
import com.secondlab.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}
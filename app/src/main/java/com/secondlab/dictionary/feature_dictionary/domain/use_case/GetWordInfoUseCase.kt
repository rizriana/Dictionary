package com.secondlab.dictionary.feature_dictionary.domain.use_case

import com.secondlab.dictionary.core.util.Resource
import com.secondlab.dictionary.feature_dictionary.domain.model.WordInfo
import com.secondlab.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWordInfoUseCase @Inject constructor(
    private val repository: WordInfoRepository,
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        // if the user did not enter the word/clean up our query
        if (word.isBlank()) {
            return flow { }
        }
        return repository.getWordInfo(word)
    }
}
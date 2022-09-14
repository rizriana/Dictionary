package com.secondlab.dictionary.feature_dictionary.presentation

import com.secondlab.dictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItem: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false,
)

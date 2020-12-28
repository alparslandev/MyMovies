package com.implementhing.domain

import kotlinx.coroutines.flow.Flow

interface UseCase<I, O> {
    fun invoke(input: I): Flow<O>
}
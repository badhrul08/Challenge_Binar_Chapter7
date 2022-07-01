package com.example.challenge_seven.domain.usecase.getuserdata

import com.example.challenge_seven.common.Resource
import com.example.challenge_seven.data.local.entity.toUser
import com.example.challenge_seven.domain.model.User
import com.example.challenge_seven.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val user = repository.getUserData(email).toUser()
            emit(Resource.Success(user))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}
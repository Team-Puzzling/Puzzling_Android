package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.entity.User

interface UserRepository {

    suspend fun setUserInfo(userInfo: User)
    suspend fun getUserInfo(): User
}

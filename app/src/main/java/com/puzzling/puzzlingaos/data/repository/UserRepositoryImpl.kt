package com.puzzling.puzzlingaos.data.repository

import androidx.datastore.core.DataStore
import com.puzzling.puzzlingaos.data.entity.User
import com.puzzling.puzzlingaos.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dataStore: DataStore<User>) :
    UserRepository {
    override suspend fun setUserInfo(userInfo: User) {
        dataStore.updateData { User(userInfo.memberId, userInfo.name) }
    }

    override suspend fun getUserInfo(): User = dataStore.data.first()
}

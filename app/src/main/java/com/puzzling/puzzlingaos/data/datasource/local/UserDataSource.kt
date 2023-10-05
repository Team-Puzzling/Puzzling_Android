package com.puzzling.puzzlingaos.data.datasource.local

import androidx.datastore.core.Serializer
import com.puzzling.puzzlingaos.data.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.apache.commons.lang3.SerializationException
import java.io.InputStream
import java.io.OutputStream

object UserDataSource : Serializer<User> {
    override val defaultValue: User
        get() = User()

    override suspend fun readFrom(input: InputStream): User {
        return try {
            Json.decodeFromString(
                deserializer = User.serializer(),
                string = input.readBytes().decodeToString(),
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = User.serializer(),
                    value = t,
                ).encodeToByteArray(),
            )
        }
    }
}

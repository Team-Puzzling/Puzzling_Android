package com.puzzling.puzzlingaos.data.datasource.local

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.Serializer
import com.puzzling.puzzlingaos.data.entity.Token
import com.puzzling.puzzlingaos.data.service.CryptoService
import kotlinx.serialization.json.Json
import org.apache.commons.lang3.SerializationException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
class TokenDataSource @Inject constructor(private val cryptoService: CryptoService) : Serializer<Token> {
    override val defaultValue: Token
        get() = Token()

    override suspend fun readFrom(input: InputStream): Token {
        val decryptedBytes = cryptoService.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = Token.serializer(),
                string = decryptedBytes.decodeToString(),
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: Token, output: OutputStream) {
        cryptoService.encrypt(
            bytes = Json.encodeToString(
                serializer = Token.serializer(),
                value = t,
            ).encodeToByteArray(),
            outputStream = output,
        )
    }
}

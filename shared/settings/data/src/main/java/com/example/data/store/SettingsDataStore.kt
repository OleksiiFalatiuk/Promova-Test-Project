package com.example.data.store

import android.content.Context
import com.example.common.serializer.createDataStoreSerializer
import com.example.local.DataStoreLocalModel
import com.promovatestproject.shared.user.models.local.UserLocalModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.osipxd.security.crypto.encryptedDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SettingsDataStore @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val Context.dataStore by encryptedDataStore(
        DATA_STORE_NAME,
        serializer = createDataStoreSerializer<DataStoreLocalModel?>(
            null
        )
    )

    private suspend fun updateSettingsWithUser(block: suspend (settings: DataStoreLocalModel) -> DataStoreLocalModel) {
        context.dataStore.updateData { localSettingsModel ->
            block(localSettingsModel ?: DataStoreLocalModel())
        }
    }

    suspend fun updateUser(block: suspend (user: UserLocalModel?) -> UserLocalModel?) {
        updateSettingsWithUser { localSettingsModel ->
            localSettingsModel.copy(
                user = block(localSettingsModel.user)
            )
        }
    }

    fun getUserFlow(): Flow<UserLocalModel?> = context.dataStore.data.map { it?.user }

    companion object {
        private const val DATA_STORE_NAME = "datastore.json"
    }
}
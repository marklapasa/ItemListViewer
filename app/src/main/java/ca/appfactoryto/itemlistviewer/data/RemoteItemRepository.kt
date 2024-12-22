package ca.appfactoryto.itemlistviewer.data

import ca.appfactoryto.itemlistviewer.domain.Item
import ca.appfactoryto.itemlistviewer.domain.ItemListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.io.IOException

/**
 * Provide a list of Items from a remote source.
 */
object RemoteItemRepository : ItemListRepository {

    private val client = OkHttpClient()

    // TODO: Move thus into a config file for different environments
    private val URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json"

    override suspend fun fetchAll(): List<Item> = withContext(Dispatchers.IO) {
        val request = okhttp3.Request.Builder().url(URL).build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {

                response.body?.string()?.let {
                    Json.decodeFromString<List<Item>>(it)
                } ?: emptyList()
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
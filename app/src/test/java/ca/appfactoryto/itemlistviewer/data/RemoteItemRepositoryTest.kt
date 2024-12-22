package ca.appfactoryto.itemlistviewer.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

/**
 * Exercise RemoteItemRepository's functionality in isolation
 */
class RemoteItemRepositoryTest {

    @Test
    fun fetchAll() = runBlocking {
        val items = RemoteItemRepository.fetchAll()
        assertTrue(items.isNotEmpty())
        println(items)
    }
}
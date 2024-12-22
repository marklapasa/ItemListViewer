package ca.appfactoryto.itemlistviewer.data

import ca.appfactoryto.itemlistviewer.domain.Item
import kotlinx.coroutines.runBlocking
import org.junit.Test

class EmbeddedItemRepositoryTest {

    /**
     * Basic test to see if the json can be parsed into strongly typed class instances
     */
    @Test
    fun fetchAll() = runBlocking {
        val embeddedItemRepository = EmbeddedItemRepository
        val list: List<Item> = embeddedItemRepository.fetchAll()
        assert(list.isNotEmpty())
        println(list)
    }
}
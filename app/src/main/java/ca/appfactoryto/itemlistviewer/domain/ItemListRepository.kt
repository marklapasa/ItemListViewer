package ca.appfactoryto.itemlistviewer.domain

/**
 * Provider for Item data. Contract between provider of Item data and consumers of that Item data.
 */
interface ItemListRepository {

    /**
     * Return a list of Items
     */
    suspend fun fetchAll(): List<Item>
}
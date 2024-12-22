package ca.appfactoryto.itemlistviewer.ui

import ca.appfactoryto.itemlistviewer.ui.app.SortRule
import ca.appfactoryto.itemlistviewer.domain.Item

object SortUtil {

    private val numbersOnly = Regex("[^0-9]")

    fun prepare(list: List<Item>, sortRule: SortRule): List<Item> {
        return list
            .filter { it.name?.isNotBlank() == true && it.name.isNotEmpty() }
            .sortedWith(
                compareBy(
                    { it.listId },
                    {
                        if (sortRule == SortRule.ALPHABETICAL) {
                            it.name
                        } else {
                            // Extract the number from the string and convert it to an Int
                            // This handles the edge case where "Item 280" is incorrectly
                            // before "Item 29"
                            it.name?.replace(numbersOnly, "")?.toInt()
                        }
                    }
                )
            )
    }

}
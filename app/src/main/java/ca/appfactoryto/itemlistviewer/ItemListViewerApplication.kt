package ca.appfactoryto.itemlistviewer

import android.app.Application

/**
 * Android Application Context provider. Dependency for viewModels being able to resolve
 * string resources.
 */
class ItemListViewerApplication : Application() {

    companion object {
        lateinit var instance: ItemListViewerApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
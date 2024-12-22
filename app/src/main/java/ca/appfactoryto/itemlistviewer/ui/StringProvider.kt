package ca.appfactoryto.itemlistviewer.ui

import android.content.Context
import androidx.annotation.StringRes

/**
 * Utility class that returns a string from a string resource for classes that don't
 * have direct access to Android COntext
 */
class StringProvider(private val context: Context) {

    /***
     * Returns a string from a string resource id
     */
    fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }

}

package app.revanced.manager.ui.viewmodel

import android.app.Application
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import app.revanced.manager.preferences.PreferencesManager
import app.revanced.manager.util.ghOrganization

class SettingsViewModel(
    private val application: Application,
    val prefs: PreferencesManager
) : ViewModel() {
    fun openGitHub() {
        val intent = Intent(Intent.ACTION_VIEW, ghOrganization.toUri()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        application.startActivity(intent)
    }
}
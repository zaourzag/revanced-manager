package app.revanced.manager.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.ui.graphics.vector.ImageVector
import app.revanced.manager.R
import com.xinto.taxi.Destination
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
sealed interface AppDestination : Destination {
    @Parcelize
    object Home : AppDestination

    @Parcelize
    object Settings : AppDestination
}

@Parcelize
enum class HomeDestination(
    val icon: @RawValue ImageVector,
    @StringRes val label: Int
) : Destination {
    Home(Icons.Default.Home, R.string.home),
    Patcher(Icons.Default.Build, R.string.patcher),
    More(Icons.Default.MoreHoriz, R.string.more),
}
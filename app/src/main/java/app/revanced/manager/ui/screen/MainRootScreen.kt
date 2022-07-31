package app.revanced.manager.ui.screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import app.revanced.manager.R
import app.revanced.manager.ui.navigation.AppDestination
import app.revanced.manager.ui.navigation.HomeDestination
import com.xinto.taxi.BackstackNavigator
import com.xinto.taxi.Taxi
import com.xinto.taxi.rememberNavigator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainRootScreen(navigator: BackstackNavigator<AppDestination>) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec = rememberSplineBasedDecay(),
        state = rememberTopAppBarScrollState()
    )
    val mainRootNavigator = rememberNavigator(HomeDestination.Home)
    val currentDestination = mainRootNavigator.currentDestination

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(mainRootNavigator.currentDestination.label)) },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            NavigationBar {
                HomeDestination.values().forEach { destination ->
                    NavigationBarItem(
                        selected = currentDestination == destination,
                        icon = { Icon(destination.icon, stringResource(destination.label)) },
                        label = { Text(stringResource(destination.label)) },
                        onClick = { mainRootNavigator.replace(destination) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier.padding(paddingValues)
        ) {
            Taxi(
                modifier = Modifier.weight(1f, true),
                navigator = mainRootNavigator,
                transitionSpec = { fadeIn() with fadeOut() }
            ) { destination ->
                when (destination) {
                    HomeDestination.Home -> HomeScreen()
                    HomeDestination.Patcher -> HomeScreen()
                    HomeDestination.More -> HomeScreen()
                }
            }
        }

    }
}
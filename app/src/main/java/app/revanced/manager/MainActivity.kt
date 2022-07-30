package app.revanced.manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import app.revanced.manager.ui.theme.ReVancedManagerTheme
import com.xinto.taxi.Destination
import com.xinto.taxi.Taxi
import com.xinto.taxi.rememberNavigator
import kotlinx.parcelize.Parcelize


sealed interface SampleDestination : Destination {
    @Parcelize
    object RegularSample : SampleDestination

    @Parcelize
    object BackstackSample : SampleDestination
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReVancedManagerTheme {
                MainContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainContent() {
    val navigator = rememberNavigator<SampleDestination>(initial = SampleDestination.ChooseScreen)
    val topBarBehaviour = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())

    BackHandler {
        if (navigator.currentDestination is SampleDestination.ChooseScreen) {
            // finishActivity()
            return@BackHandler
        }
        navigator.replace(SampleDestination.ChooseScreen)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                title = { Text("Taxi samples") },
                scrollBehavior = topBarBehaviour
            )
        }
    ) { paddingValues ->
        Taxi(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navigator = navigator,
            transitionSpec = { fadeIn() with fadeOut() },
        ) {
            when (it) {
                is SampleDestination.RegularSample -> {
                    RegularScreen()
                }
                is SampleDestination.BackstackSample -> {
                    BackstackScreen()
                }
            }
        }
    }
}
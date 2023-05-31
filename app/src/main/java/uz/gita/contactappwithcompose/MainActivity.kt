package uz.gita.contactappwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import uz.gita.contactappwithcompose.navigation.NavigationHandler
import uz.gita.contactappwithcompose.ui.screens.home.HomeScreen
import uz.gita.contactappwithcompose.ui.screens.splash.SplashScreen
import uz.gita.contactappwithcompose.ui.theme.ContactAppWithComposeTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationHandler: NavigationHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactAppWithComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Navigator(screen = SplashScreen()){navigator->
                        LaunchedEffect(key1 = navigator){
                            navigationHandler.navBuffer.onEach {
                                it(navigator)
                            }.collect()
                        }
                        CurrentScreen()
                    }
                }
            }
        }
    }
}


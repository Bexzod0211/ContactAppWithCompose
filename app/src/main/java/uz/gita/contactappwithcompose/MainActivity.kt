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
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import uz.gita.contactappwithcompose.domain.repository.AppRepository
import uz.gita.contactappwithcompose.navigation.NavigationHandler
import uz.gita.contactappwithcompose.ui.screens.home.HomeScreen
import uz.gita.contactappwithcompose.ui.screens.splash.SplashScreen
import uz.gita.contactappwithcompose.ui.theme.ContactAppWithComposeTheme
import uz.gita.contactappwithcompose.utils.myLog
import uz.gita.contactappwithcompose.worker.MyWorker
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationHandler: NavigationHandler
  /*  @Inject
    lateinit var repository: AppRepository*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

//        myLog("${repository.getAllContacts()}")

        val oneTime = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraint)
            .addTag("Add contact")
            .build()

        WorkManager.getInstance(this).enqueue(oneTime)

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


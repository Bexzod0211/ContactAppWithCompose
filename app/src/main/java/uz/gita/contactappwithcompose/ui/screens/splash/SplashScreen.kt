package uz.gita.contactappwithcompose.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.contactappwithcompose.R
import uz.gita.contactappwithcompose.ui.theme.ContactAppWithComposeTheme

class SplashScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        SplashScreenContent()
    }
}

@Composable
fun SplashScreenContent() {
    ContactAppWithComposeTheme {
        Surface {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
                Image(painter = painterResource(id = R.drawable.photo_man), contentDescription = null, modifier = Modifier.size(80.dp))
                Text(text = "Contacts", color = Color.Black, fontSize = 32.sp, modifier = Modifier.padding(top = 20.dp),)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}
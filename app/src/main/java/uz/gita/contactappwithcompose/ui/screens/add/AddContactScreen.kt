package uz.gita.contactappwithcompose.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.contactappwithcompose.ui.theme.ContactAppWithComposeTheme

class AddContactScreen : AndroidScreen(){
    @Composable
    override fun Content() {

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenContent() {
    ContactAppWithComposeTheme {
        Surface {
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(value = "", onValueChange = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp), placeholder = {
                    Text(text = "Firstname")
                })
                TextField(value = "", onValueChange = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp), placeholder = {
                    Text(text = "Lastname")
                })

                TextField(value = "", onValueChange = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp), placeholder = {
                    Text(text = "Phone number")
                })

                Button(onClick = {

                }, modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp,top = 16.dp)
                    .fillMaxWidth()
                    .size(0.dp, 60.dp),) {
                    Text(text = "Add contact")
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddScreenPreview() {
    AddScreenContent()
}
package uz.gita.contactappwithcompose.ui.screens.add

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.ui.theme.ContactAppWithComposeTheme
import uz.gita.contactappwithcompose.ui.viewmodels.AddContactContract
import uz.gita.contactappwithcompose.ui.viewmodels.impl.AddViewModelImpl

class AddContactScreen(private val contact:ContactData? = null) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: AddContactContract.ViewModel = getViewModel<AddViewModelImpl>()

        AddScreenContent(viewModel.uiState.collectAsState().value,viewModel::onEventDispatcher,contact = contact?: ContactData(0,"","",""))
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenContent(uiState:AddContactContract.UIState,onEventDispatcher:(AddContactContract.Intent)->Unit,contact:ContactData) {

    var fname by remember {
        mutableStateOf(contact.firstName)
    }

    var lname by remember {
        mutableStateOf(contact.lastName)
    }

    var phone by remember {
        mutableStateOf(contact.phone)
    }
    val navigator = LocalNavigator.currentOrThrow
    if (uiState.message != ""){
        val msg = if (contact.id == 0) "added"
        else "edited"
        Toast.makeText(LocalContext.current, "${uiState.message}$msg", Toast.LENGTH_SHORT).show()
        onEventDispatcher.invoke(AddContactContract.Intent.ClearMessage)
    }

    if (uiState.popScreenState){
        navigator.pop()
        onEventDispatcher.invoke(AddContactContract.Intent.ClearPopScreen)
    }

//    val popScreen = viewModel.popScreenLiveData.observeAsState()
//    popScreen.value?.let {
//        navigator.pop()
//    }

    val submitBtnText = if (contact.id == 0){
        "Add Contact"
    }
    else "Edit Contact"
    ContactAppWithComposeTheme {
        Surface {

            Column(modifier = Modifier.fillMaxSize()) {
                TextField(value = fname, onValueChange = {
                    fname = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp), placeholder = {
                    Text(text = "Firstname")
                })
                TextField(value = lname, onValueChange = {
                    lname = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp), placeholder = {
                    Text(text = "Lastname")
                })

                TextField(value = phone, onValueChange = {
                    phone = it
                },keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp), placeholder = {
                    Text(text = "Phone number")
                })

                Button(
                    onClick = {
                        onEventDispatcher.invoke(AddContactContract.Intent.AddContact(contact.id,fname,lname,phone))

                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .fillMaxWidth()
                        .size(0.dp, 60.dp),
                ) {
                    Text(text = submitBtnText)
                }
            }
        }
    }
}
/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenContent(viewModel: AddViewModel,contact:ContactData) {
    var fname by remember {
        mutableStateOf(contact.firstName)
    }

    var lname by remember {
        mutableStateOf(contact.lastName)
    }

    var phone by remember {
        mutableStateOf(contact.phone)
    }
    val navigator = LocalNavigator.currentOrThrow
    val message = viewModel.messageLiveData.observeAsState()
    message.value?.let {
        Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
    }
//    val popScreen = viewModel.popScreenLiveData.observeAsState()
//    popScreen.value?.let {
//        navigator.pop()
//    }

    val submitBtnText = if (contact.id == 0){
        "Add Contact"
    }
    else "Edit Contact"
    ContactAppWithComposeTheme {
        Surface {

            Column(modifier = Modifier.fillMaxSize()) {
                TextField(value = fname, onValueChange = {
                    fname = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp), placeholder = {
                    Text(text = "Firstname")
                })
                TextField(value = lname, onValueChange = {
                    lname = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp), placeholder = {
                    Text(text = "Lastname")
                })

                TextField(value = phone, onValueChange = {
                    phone = it
                },keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp), placeholder = {
                    Text(text = "Phone number")
                })

                Button(
                    onClick = {
                              if (viewModel.addContact(contact.id, fname, lname, phone)){
                                  navigator.pop()
                              }

                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .fillMaxWidth()
                        .size(0.dp, 60.dp),
                ) {
                    Text(text = submitBtnText)
                }
            }
        }
    }
}*/

@Preview(showSystemUi = true)
@Composable
fun AddScreenPreview() {
//    AddScreenContent()
}
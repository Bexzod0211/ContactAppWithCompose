package uz.gita.contactappwithcompose.ui.screens.home

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.gita.contactappwithcompose.MainActivity
import uz.gita.contactappwithcompose.R
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.ui.screens.ContactItem
import uz.gita.contactappwithcompose.ui.screens.add.AddContactScreen
import uz.gita.contactappwithcompose.ui.theme.ContactAppWithComposeTheme
import uz.gita.contactappwithcompose.ui.viewmodels.HomeViewModel
import uz.gita.contactappwithcompose.ui.viewmodels.impl.HomeViewModelImpl

class HomeScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = getViewModel<HomeViewModelImpl>()
        Log.d("TTT","Content")
        HomeScreenContent(viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(viewModel: HomeViewModel) {

    viewModel.loadAllContacts()
    val navigator = LocalNavigator.currentOrThrow
    val contacts = viewModel.contactsLiveData.observeAsState(listOf())
    val message = viewModel.messageLiveData.observeAsState()

    message.value?.let {
        Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
    }
//    BackHandler(onBack = {
//        MainActivity().finish()
//    })
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(modifier = Modifier
                .fillMaxWidth()
                .size(0.dp, 56.dp), title = {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
                    Text(text = "Contacts", color = Color.Black, fontSize = 24.sp)
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigator.push(AddContactScreen())
                }, modifier = Modifier.size(60.dp), shape = RoundedCornerShape(30.dp), containerColor = Color(0xFF035BF4)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_add), contentDescription = null, colorFilter = ColorFilter.tint(Color.White))
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {
                    LazyColumn {
                        contacts.value.forEach{
                            item {
                                ContactItem(contact = it, modifier = Modifier.fillMaxWidth()
                                    .size(0.dp, 80.dp).combinedClickable(onClick = {},
                                onLongClick = {
                                    Log.d("TTT","onLongClick")
                                    viewModel.deleteContact(it.toEntity())
                                })){
                                    navigator.push(AddContactScreen(it))
                                }
                            }
                        }
                    }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreenContent()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(modifier = Modifier
                .fillMaxWidth()
                .size(0.dp, 56.dp), title = {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
                    Text(text = "Contacts", color = Color.Black, fontSize = 24.sp)
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    navigator.push(AddContactScreen())
                }, modifier = Modifier.size(60.dp), shape = RoundedCornerShape(30.dp), containerColor = Color(0xFF035BF4)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_add), contentDescription = null, colorFilter = ColorFilter.tint(Color.White))
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {

//            LazyColumn {
//                items(contacts.value!!){
//                    ContactItem(contact = it)
//                }
//            }

        }
    }
}

@Composable
fun MyScreen() {

}
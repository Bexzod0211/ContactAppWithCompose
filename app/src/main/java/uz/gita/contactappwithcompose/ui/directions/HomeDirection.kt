package uz.gita.contactappwithcompose.ui.directions

import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.navigation.AppNavigator
import uz.gita.contactappwithcompose.ui.screens.add.AddContactScreen
import uz.gita.contactappwithcompose.ui.viewmodels.HomeContract
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val appNavigator: AppNavigator
): HomeContract.Direction {
    override suspend fun navigateToAddContactScreen() {
        appNavigator.navigateTo(AddContactScreen())
    }

    override suspend fun navigateToAddContactScreen(contact: ContactData) {
        appNavigator.navigateTo(AddContactScreen(contact))
    }

}
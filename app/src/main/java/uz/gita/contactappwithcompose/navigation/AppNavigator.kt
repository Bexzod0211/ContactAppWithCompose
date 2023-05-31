package uz.gita.contactappwithcompose.navigation


interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
}
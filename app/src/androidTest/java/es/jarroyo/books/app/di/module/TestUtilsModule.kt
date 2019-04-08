package es.jarroyo.books.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.books.app.notification.NotificationTDDManager
import es.jarroyo.books.ui.App
import es.jarroyo.books.utils.NetworkSystem
import es.jarroyo.books.utils.NetworkSystemAbstract
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class TestUtilsModule {
    @Provides
    @Singleton
    fun provideNetworkSystem(app: App) =
            NetworkSystem(app) as NetworkSystemAbstract

    @Provides
    @Singleton
    fun provideCorutineContext() =
        Dispatchers.Default as CoroutineContext

    @Provides
    @Singleton
    fun provideNotificationManager(app: App)
            = NotificationTDDManager(app)
}
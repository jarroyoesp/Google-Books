package es.jarroyo.books.app.di


import dagger.Module
import dagger.Provides
import es.jarroyo.books.app.navigator.Navigator
import es.jarroyo.books.ui.App
import es.jarroyo.books.ui.BooksApp
import javax.inject.Singleton


@Module
class TestApplicationModule(val app: BooksApp){
    @Provides @Singleton
    fun provideApp(): App = app

    @Provides @Singleton
    fun provideNavigator(): Navigator = Navigator()


}
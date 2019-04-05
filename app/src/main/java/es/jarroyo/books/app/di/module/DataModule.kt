package es.jarroyo.books.app.di.module

import com.microhealth.lmc.utils.NetworkSystemAbstract
import dagger.Module
import dagger.Provides
import es.jarroyo.books.data.source.network.INetworkDataSource
import es.jarroyo.books.data.source.network.NetworkDataSource
import es.jarroyo.books.ui.App
import javax.inject.Singleton

@Module
class DataModule {

    @Provides @Singleton
    fun provideNetworkDataSource(appContext: App, networkSystemBase: NetworkSystemAbstract) =
            NetworkDataSource(appContext, networkSystemBase) as INetworkDataSource


}

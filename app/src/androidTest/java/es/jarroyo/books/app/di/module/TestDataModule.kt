package es.jarroyo.books.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.books.data.source.network.INetworkDataSource
import es.jarroyo.books.data.source.network.TestNetworkDataSource
import es.jarroyo.books.ui.App
import es.jarroyo.books.utils.NetworkSystemAbstract
import javax.inject.Singleton

@Module
class TestDataModule {

    @Provides @Singleton
    fun provideNetworkDataSource(appContext: App, networkSystemBase: NetworkSystemAbstract) =
            TestNetworkDataSource(networkSystemBase) as INetworkDataSource


}

package es.jarroyo.books.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.books.data.repository.BooksRepository
import es.jarroyo.books.data.source.network.INetworkDataSource
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideBooksRepository(
        networkDataSource: INetworkDataSource
    ) = BooksRepository(networkDataSource)

}
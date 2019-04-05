package es.jarroyo.books.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.books.data.repository.BooksRepository
import es.jarroyo.books.domain.usecase.books.getBooksList.GetBooksListUseCase
import javax.inject.Singleton


@Module
class DomainModule {

    /**
     * BOOKS
     */
    @Provides
    @Singleton
    fun provideGetBooksListUseCase(repository: BooksRepository) = GetBooksListUseCase(repository)

}
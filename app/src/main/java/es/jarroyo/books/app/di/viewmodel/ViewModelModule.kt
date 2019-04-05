package es.jarroyo.books.app.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import es.jarroyo.books.ui.viewmodel.books.BooksViewModel
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    abstract fun bindBooksViewModel(viewModel: BooksViewModel): ViewModel

}
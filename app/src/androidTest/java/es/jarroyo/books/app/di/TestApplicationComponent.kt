package es.jarroyo.books.app.di

import dagger.Component
import es.jarroyo.books.app.di.component.ApplicationComponent
import es.jarroyo.books.app.di.module.DomainModule
import es.jarroyo.books.app.di.module.RepositoryModule
import es.jarroyo.books.app.di.module.TestDataModule
import es.jarroyo.books.app.di.module.TestUtilsModule
import es.jarroyo.books.app.di.viewmodel.ViewModelFactoryModule
import es.jarroyo.books.app.di.viewmodel.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(TestApplicationModule::class,
        TestUtilsModule::class,
        DomainModule::class,
        TestDataModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class)
)
interface TestApplicationComponent: ApplicationComponent {
}
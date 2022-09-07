package id.hwaryun.feature_home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import id.hwaryun.core_data.di.IoDispatcher
import id.hwaryun.core_data.repository.MovieRepository
import id.hwaryun.feature_home.domain.GetBoxOfficeUseCase
import id.hwaryun.feature_home.domain.GetMovieDetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetBoxOfficeUseCase(
        repository: MovieRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = GetBoxOfficeUseCase(repository, dispatcher)

    @Provides
    @ViewModelScoped
    fun provideGetMovieDetailUseCase(
        repository: MovieRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = GetMovieDetailUseCase(repository, dispatcher)
}
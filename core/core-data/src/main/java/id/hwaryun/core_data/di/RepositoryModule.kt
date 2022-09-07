package id.hwaryun.core_data.di

import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import id.hwaryun.core_data.remote.datasource.MovieDataSource
import id.hwaryun.core_data.remote.datasource.TvDataSource
import id.hwaryun.core_data.repository.MovieRepository
import id.hwaryun.core_data.repository.MovieRepositoryImpl
import id.hwaryun.core_data.repository.TvRepository
import id.hwaryun.core_data.repository.TvRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(dataSource: MovieDataSource): MovieRepository {
        return MovieRepositoryImpl(dataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideTvRepository(dataSource: TvDataSource): TvRepository {
        return TvRepositoryImpl(dataSource)
    }
}
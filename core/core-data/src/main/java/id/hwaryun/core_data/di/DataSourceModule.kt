package id.hwaryun.core_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.hwaryun.core_data.remote.datasource.MovieDataSource
import id.hwaryun.core_data.remote.datasource.MovieDataSourceImpl
import id.hwaryun.core_data.remote.datasource.TvDataSource
import id.hwaryun.core_data.remote.datasource.TvDataSourceImpl
import id.hwaryun.core_data.remote.service.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(apiService: ApiService): MovieDataSource {
        return MovieDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideTvDataSource(apiService: ApiService): TvDataSource {
        return TvDataSourceImpl(apiService)
    }
}
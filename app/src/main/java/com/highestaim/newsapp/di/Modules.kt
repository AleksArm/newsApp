package com.highestaim.newsapp.di

import android.app.Application
import androidx.room.Room
import com.highestaim.newsapp.data.local.AppDatabase
import com.highestaim.newsapp.data.local.dao.FavoriteDao
import com.highestaim.newsapp.data.repositoryImpl.NewsRepositoryImpl
import com.highestaim.newsapp.data.remote.service.NewsService
import com.highestaim.newsapp.data.repository.favorite.FavoriteRepository
import com.highestaim.newsapp.data.repositoryImpl.favorite.FavoriteRepositoryImpl
import com.highestaim.newsapp.ui.viewModel.FavoritesViewModel
import com.highestaim.newsapp.ui.viewModel.NewsViewModel
import com.highestaim.newsapp.utils.AppConstants
import com.highestaim.newsapp.utils.AppConstants.DB_NAME
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase
            = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()


    fun provideCountriesDao(database: AppDatabase): FavoriteDao = database.favoriteDao()

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}


val remoteModule = module {


    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().retryOnConnectionFailure(false).addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }).build()
    }

    fun provideNewsFeedService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    single { provideOkHttpClient() }
    single { provideNewsFeedService(get()) }
    single { provideRetrofit(get()) }
}

val appViewModels: Module = module {
    viewModel { NewsViewModel(get())}
    viewModel { FavoritesViewModel(get())}
}

val repository: Module = module {
    single { NewsRepositoryImpl(get(),get()) }
    single { FavoriteRepositoryImpl(get()) }
}


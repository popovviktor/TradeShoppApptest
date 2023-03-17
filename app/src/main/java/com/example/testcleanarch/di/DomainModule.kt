package com.example.testcleanarch.di

import com.example.testcleanarch.domain.repository.UserRepository
import com.example.testcleanarch.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun providesGetUseCase(userRepository: UserRepository):GetUserNameUseCase{
        return GetUserNameUseCase(userRepository = userRepository)
    }
    @Provides
    fun providesSaveUseCase(userRepository: UserRepository):SaveUserNameUseCase{
        return SaveUserNameUseCase(userRepository = userRepository)
    }
    @Provides
    fun providesSaveDb(userRepository: UserRepository):SaveUserDbUseCase{
        return SaveUserDbUseCase(userRepository = userRepository)
    }
    @Provides
    fun providesGetAllUserDb(userRepository: UserRepository):GetAllUserDbUseCase{
        return GetAllUserDbUseCase(userRepository = userRepository)
    }
    @Provides
    fun providesGetAllFlashSale(userRepository: UserRepository):GetAllFlashUseCase{
        return GetAllFlashUseCase(userRepository = userRepository)
    }
    @Provides
    fun providesGetAllLatest(userRepository: UserRepository):GetAllLatestUseCase{
        return GetAllLatestUseCase(userRepository = userRepository)
    }
    @Provides
    fun providesGetItemOnTouch(userRepository: UserRepository):GetItemOnTouchUseCase{
        return GetItemOnTouchUseCase(userRepository = userRepository)
    }
    @Provides
    fun providesGetSearchUseCase(userRepository: UserRepository):GetSearchUseCase{
        return GetSearchUseCase(userRepository=userRepository)
    }

}
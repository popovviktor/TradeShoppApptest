package com.example.testcleanarch.data.repository

import com.example.testcleanarch.data.api.RemoteDataSource
import com.example.testcleanarch.data.api.model.FlashSaleItem
import com.example.testcleanarch.data.api.model.Latest
import com.example.testcleanarch.data.dao.UserDao
import com.example.testcleanarch.data.models.User
import com.example.testcleanarch.data.models.UserInfoTuple
import com.example.testcleanarch.data.storage.UserStorage
import com.example.testcleanarch.data.utils.BaseApiResponse
import com.example.testcleanarch.domain.models.*
import com.example.testcleanarch.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl (
    private val userStorage: UserStorage,
    private val userDao: UserDao,
    private val remoteDataSource: RemoteDataSource):UserRepository,BaseApiResponse() {
    override fun saveUserName(userModelDomain: UserModelDomain):Boolean{
    val user = mapToStorage(userModelDomain)
        userStorage.save(user)

        return true
    }


    override fun getUserName():UserModelDomain{
        return mapToDomain()
    }

    override suspend fun saveDB(userModelDomain: UserModelDomain) {
        withContext(Dispatchers.IO){
            userDao.insert(User(userModelDomain.firstName,userModelDomain.lastName,userModelDomain.email,userModelDomain.password).toUserdbEntity())}
    }

    override suspend fun getAllDbUsers(): List<UserModelDomain> {
        val arr = getAll()
        val arrusname = ArrayList<UserModelDomain>()
        for (elem in arr){
            arrusname.add(UserModelDomain(elem.firstName,elem.lastName,elem.email,elem.password))
        }
        return arrusname
    }

    suspend fun getAll():List<UserInfoTuple>{
        return withContext(Dispatchers.IO){
            return@withContext userDao.getAll()
        }
    }
    override suspend fun getAllFlash():FlashSale{

        val data = safeApiCall { remoteDataSource.getAllFlashSale() }.data?.flashSale
        val flashSale = FlashSale()
        for (elem in data!!){
            flashSale.flashSale.add(flashSaleFromDatatoDomain(elem))
        }
        return flashSale
    }
    override suspend fun getAllLatest():com.example.testcleanarch.domain.models.Latest{
        val data = safeApiCall { remoteDataSource.getAllLatest() }.data?.latest
        val latest = com.example.testcleanarch.domain.models.Latest()
        for (elem in data!!){
            latest.latest.add(latestFromDatatoDomain(elem))
        }
        return latest
    }

    override suspend fun getItemOnTouch(): ItemOnTouch {
        val data = safeApiCall { remoteDataSource.getItemOnTouch() }.data!!
        val item = ItemOnTouch(name = data.name, description = data.description, rating = data.rating,
            numberOfReviews = data.numberOfReviews, price = data.price, colors = data.colors, imageUrls = data.imageUrls)
        return item
    }

    override suspend fun getSearch(): Search {
        val data = safeApiCall { remoteDataSource.getSearch() }.data!!
        val search = Search(words = data.words)
        return search
    }

    fun mapToStorage(userModelDomain: UserModelDomain): User {
        val user = User(userModelDomain.firstName,userModelDomain.lastName,userModelDomain.email,userModelDomain.password)
        return user
    }
    fun mapToDomain():UserModelDomain{
        System.out.println(userStorage.get().firstName)
    return UserModelDomain(firstName = userStorage.get().firstName, lastName = userStorage.get().lastName,userStorage.get().email,userStorage.get().password)
    }
    fun flashSaleFromDatatoDomain(flashSale: FlashSaleItem):com.example.testcleanarch.domain.models.FlashSaleItem{
        var flashdomainItem = com.example.testcleanarch.domain.models.FlashSaleItem()
        flashdomainItem.category = flashSale.category
        flashdomainItem.discount = flashSale.discount
        flashdomainItem.name = flashSale.name
        flashdomainItem.price = flashSale.price
        flashdomainItem.imageUrl = flashSale.imageUrl
        return flashdomainItem
    }
    fun latestFromDatatoDomain(latestit:com.example.testcleanarch.data.api.model.LatestItem):com.example.testcleanarch.domain.models.LatestItem{
        var latestitem = LatestItem()
        latestitem.category = latestit.category
        latestitem.imageUrl = latestit.imageUrl
        latestitem.name = latestit.name
        latestitem.price = latestit.price
        return latestitem
    }
}
package com.example.testcleanarch.presentation

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcleanarch.domain.models.*
import com.example.testcleanarch.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val getSearchUseCase: GetSearchUseCase,
        private val getUserNameUseCase: GetUserNameUseCase,
        private val getItemOnTouchUseCase: GetItemOnTouchUseCase,
        private val saveUserDbUseCase: SaveUserDbUseCase,
        private val getAllUserDbUseCase: GetAllUserDbUseCase,
        private val getAllFlashUseCase: GetAllFlashUseCase,
        private val getAllLatestUseCase: GetAllLatestUseCase):ViewModel() {
    private val mlive =MutableLiveData<UserModelDomain>()
    val live:LiveData<UserModelDomain> = mlive
    private val mlivalatest = MutableLiveData<Latest>()
    val livelatest:LiveData<Latest> = mlivalatest
    private val  mliveflash = MutableLiveData<FlashSale>()
    val liveflash:LiveData<FlashSale> = mliveflash
    private val mliveNewUserForDbStepOne = MutableLiveData<UserModelDomain>()
    val liveNewUserForDbStepOne:LiveData<UserModelDomain> = mliveNewUserForDbStepOne
    private val mliveNewUserForDbStepTwo = MutableLiveData<UserModelDomain>()
    val liveNewUserForDbStepTwo:LiveData<UserModelDomain> = mliveNewUserForDbStepTwo
    private val mliveUserLoginDone = MutableLiveData<UserModelDomain>()
    val liveUserLoginDone:LiveData<UserModelDomain> = mliveUserLoginDone
    //use livedata
    private val mliveBoolLogIn = MutableLiveData<Boolean>()
    val liveBoolLogIn = mliveBoolLogIn
    private val mliveBoolemailCheckInDB = MutableLiveData<Boolean>()
    val liveBoolemailCheckInDB = mliveBoolemailCheckInDB
    private val mliveBitmapProfilePhoto = MutableLiveData<Bitmap>()
    val liveBitMapforPrifilePhoto:LiveData<Bitmap> = mliveBitmapProfilePhoto
    private val mliveGetItemOnTouch = MutableLiveData<ItemOnTouch>()
    val liveGetItemOnTouch:LiveData<ItemOnTouch> = mliveGetItemOnTouch
    private val mliveSearch = MutableLiveData<Search>()
    val liveSearch:LiveData<Search> = mliveSearch
    private val mliveSearchWord = MutableLiveData<String>()
    val liveSearchWord:LiveData<String> = mliveSearchWord
    fun setSearch(word:String){
     mliveSearchWord.value = word
    }
    fun getSearch(){
        var search = Search()
        viewModelScope.launch {
            search = getSearchUseCase.execute()
            mliveSearch.value = search
        }
    }

    fun setBitMapUserPhoto(bitmap: Bitmap){
        mliveBitmapProfilePhoto.value = bitmap
    }
    fun getUserInfo():LiveData<UserModelDomain>{
        return mliveUserLoginDone
    }
    //var live = LiveData<UserName>()

    fun save(userModelDomain:UserModelDomain){
        //var userModelDomain = UserModelDomain(firstName = FirstName, lastName = LastName, email = Email, password = Password)
        //saveUserNameUseCase.execute(userModelDomain)
        mlive.value = userModelDomain
        viewModelScope.launch {
        saveUserDbUseCase.execute(userModelDomain)
        System.out.println("Запись в дб прошла успешно")
        get()}
        //return "Запись прошла "+ userModelDomain.firstName
    }
    fun checkEqualPassword(passwordFirst:String,passwordSecond: String):Boolean{
        if (passwordFirst == passwordSecond && passwordFirst.length>4){
            return true
        }else{return false}
    }
    fun checkCorrectFirstLastName(firstName: String,lastName: String):Boolean{
        if (firstName!="" && lastName!=""){
            return true
        }else{return false}
    }
    fun login(email: String, password: String):Boolean{
        System.out.println("Наши данные")
        System.out.println(email)
        System.out.println(password)
        System.out.println("Сверяем с дб")
        var boolean =false
        viewModelScope.launch(Dispatchers.Main) {
            liveBoolLogIn.value= null
            var arr = get()
            System.out.println(arr.size)
            for (elem in arr){
                System.out.println(elem.email)
                System.out.println(elem.password)
                if (email == elem.email && password == elem.password){
                    boolean = true
                    System.out.println("Совпадение найдено")
                    System.out.println(elem.firstName)
                    System.out.println(elem.lastName)
                    System.out.println("Совпадение найдено")
                    mliveNewUserForDbStepTwo.value = UserModelDomain(firstName = elem.firstName, lastName = elem.lastName,email=elem.email,password =elem.password)
                    mliveUserLoginDone.value = UserModelDomain(firstName = elem.firstName, lastName = elem.lastName,email=elem.email,password= elem.password)
                    mliveBoolLogIn.value = true
                    System.out.println("mliveUser сохр")
                    System.out.println(elem)
                }
            }
            if(boolean == false){
                mliveBoolLogIn.value = false
            }
        }


    return boolean}
    fun newUserDbStepTwo(password: String){
        var stepone = liveNewUserForDbStepOne.value
        var stepTwo = UserModelDomain(firstName = stepone?.firstName!!, lastName = stepone.lastName!!, email = stepone?.email!!,password = password)
        mliveNewUserForDbStepTwo.value = stepTwo
        save(stepTwo)
    }
    fun newUserDbStepOne(firstName:String,lastName:String,email: String){
        var user = UserModelDomain(firstName = firstName,lastName=lastName,email = email, password = "")
        mliveNewUserForDbStepOne.value = user
    }
    suspend fun get():ArrayList<UserModelDomain>{
        val arrModels = ArrayList<UserModelDomain>()
        viewModelScope.launch(Dispatchers.Main) {
        val arr = getAllUserDbUseCase.execute()
            System.out.println(arr.size)
            for (elem in arr){
                arrModels.add(elem)
                System.out.println(elem)
            }
        System.out.println("Чтение всех юзеров прошло усешно")
        }.join()
        System.out.println(arrModels.size)
        return arrModels
    }
    fun emailCheckCorrect(email: String):Boolean{
        if (email.indexOf("@")==-1){
            return false
        }
        if (email.indexOf(".")==-1){
            return false
        }
        return true
    }
    fun emailCheckInDB(email: String): Boolean {
        var boolean =false
        viewModelScope.launch(Dispatchers.Main){
            liveBoolemailCheckInDB.value = null
            var arr = get()
            System.out.println(arr.size)
            for (elem in arr){
                System.out.println(elem.email)
                System.out.println(elem.password)
                if (email == elem.email){
                    boolean = true
                    System.out.println("Совпадение найдено")
                    mliveBoolemailCheckInDB.value = true

                }
            }
            if(boolean == false){
                mliveBoolemailCheckInDB.value = false
            }
        }
        return boolean
    }
    suspend fun emailCheckInDBsus(email: String):Boolean{
        var arr = get()
        for (elem in arr){
            if (email == elem.email){
                return false
            }
        }
    return true}
    fun getAllFlash():FlashSale{
        var flashSale = FlashSale()
        viewModelScope.launch {
            flashSale = getAllFlashUseCase.execute()
            mliveflash.value = flashSale

        }
        return flashSale
    }
    fun getItemOntouch():ItemOnTouch{
        var itemOnTouch = ItemOnTouch()
        viewModelScope.launch {
            itemOnTouch = getItemOnTouchUseCase.execute()
            mliveGetItemOnTouch.value = itemOnTouch
        }
    return itemOnTouch}
    fun getAllLatest():Latest{
        var latest = Latest()
        viewModelScope.launch {
            latest = getAllLatestUseCase.execute()
            mlivalatest.value = latest
        }
        return latest
    }
}
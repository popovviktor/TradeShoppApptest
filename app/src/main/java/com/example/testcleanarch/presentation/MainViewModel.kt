package com.example.testcleanarch.presentation

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcleanarch.domain.models.FlashSale
import com.example.testcleanarch.domain.models.Latest
import com.example.testcleanarch.domain.models.UserModelDomain
import com.example.testcleanarch.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUserNameUseCase: GetUserNameUseCase,
                                        private val saveUserNameUseCase: SaveUserNameUseCase,
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
    private val mliveBitmapProfilePhoto = MutableLiveData<Bitmap>()
    val liveBitMapforPrifilePhoto:LiveData<Bitmap> = mliveBitmapProfilePhoto
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
                    mliveNewUserForDbStepTwo.value = UserModelDomain(elem.firstName,elem.lastName,email=elem.email,elem.password)
                    mliveUserLoginDone.value = UserModelDomain(elem.firstName,elem.lastName,email=elem.email,elem.password)
                    mliveBoolLogIn.value = true
                    System.out.println("mliveUser сохр")
                    System.out.println(elem)

                }
            }
        }


    return boolean}
    fun newUserDbStepTwo(password: String){
        var stepone = liveNewUserForDbStepOne.value
        var stepTwo = UserModelDomain(firstName = stepone?.firstName!!, lastName = stepone?.lastName!!, email = stepone?.email!!,password = password)
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
        var boolean =true
        viewModelScope.launch(Dispatchers.Main){boolean =emailCheckInDBsus(email)}
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
    fun getLoadImage(){

    }
    fun getAllLatest():Latest{
        var latest = Latest()
        viewModelScope.launch {
            latest = getAllLatestUseCase.execute()
            mlivalatest.value = latest
        }
        return latest
    }
}
package com.example.testcleanarch.presentation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testcleanarch.R

import com.example.testcleanarch.databinding.ActivityMainBinding
import com.example.testcleanarch.presentation.screensNav.ChattFragment
import com.example.testcleanarch.presentation.screensNav.SearchFragment

import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm:MainViewModel by viewModels()
    lateinit var bind:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        vm.getSearch()
        vm.live.observe(this, Observer {

        })

        //supportFragmentManager.beginTransaction().replace(R.id.place_holder_login_act,SignFragment()).commit()
        vm.getAllFlash()
        vm.getAllLatest()

        val navController = findNavController(R.id.nav_host)
        val bottomnav = bind.bottomNavMenu
        bottomnav.setupWithNavController(navController)
        System.out.println("FLASSSSSSSSSSSSSSSSSS")
        System.out.println("LAAAAAAAAATEST")
        vm.liveSearchWord.observe(this,Observer{

                supportFragmentManager.beginTransaction().replace(R.id.frameMainHome,
                    SearchFragment()
                ).commit()

        })
        bottomnav.setOnItemSelectedListener {
            if (it.itemId == R.id.nav_home)navController.navigate(R.id.nav_home)
            if (it.itemId == R.id.nav_like)navController.navigate(R.id.nav_like)
            if (it.itemId == R.id.nav_buy)navController.navigate(R.id.nav_buy)
            if (it.itemId == R.id.nav_chat)navController.navigate(R.id.nav_chat)
            if (it.itemId == R.id.nav_profile)navController.navigate(R.id.nav_profile)
            return@setOnItemSelectedListener true}




    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, imageReturnedIntent)

            try {

                //Получаем URI изображения, преобразуем его в Bitmap
                //объект и отображаем в элементе ImageView нашего интерфейса:
                var imageUri: Uri? = imageReturnedIntent!!.getData();
                var imageStream: InputStream = getContentResolver().openInputStream(imageUri!!)!!;
                var selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream);
                var imview = findViewById<ImageView>(R.id.imViewProfile)
                var imviewhome = findViewById<ImageView>(R.id.imViewProfileHome)


                vm.setBitMapUserPhoto(selectedImage)
            } catch (ex:Exception) { ex.printStackTrace()}
        }
    }

}
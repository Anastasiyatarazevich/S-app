package com.example.s_app

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val _localData = MutableLiveData<List<String>>()

    private val _remoteData = MutableLiveData<List<Int>>() // <-- Несоответствие типов

    private val combinedData: LiveData<List<String>> = MutableLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadLocalData()

        loadRemoteData()

        combineData()

    }

    private fun loadLocalData() {
        val sharedPrefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val savedData = sharedPrefs.getStringSet("saved_data", setOf("Local1", "Local2"))?.toList()
        _localData.value = savedData ?: emptyList()
    }

    private fun loadRemoteData() {
        GlobalScope.launch(Dispatchers.IO) {
            val remoteResult = listOf(1, 2, 3)
            _remoteData.postValue(remoteResult)
        }
    }

    private fun combineData() {
        GlobalScope.launch(Dispatchers.Default) {
            val local = _localData.value ?: emptyList()
            val remote = _remoteData.value ?: emptyList()

            val combined = local + remote
            (combinedData as MutableLiveData).postValue(combined)
        }
    }
}
package com.example.roomrxsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val TAG: String = "RoomRxSample.MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SampleDatabase.init(this)

        val user1 = User()
        user1.uid = 1
        user1.firstName = "Nanashi"
        user1.lastName = "001"
        user1.age = 11

        val user2 = User()
        user2.uid = 2
        user2.firstName = "Nanashi"
        user2.lastName = "002"
        user2.age = 22

        val user3 = User()
        user3.uid = 3
        user3.firstName = "Nanashi"
        user3.lastName = "003"
        user3.age = 33

        val list: MutableList<User> = mutableListOf()
        list.add(user1)
        list.add(user2)
        list.add(user3)

            SampleDatabase.getInstance()?.let { sampleDatabase ->
                val userDao = sampleDatabase.userDao()

                userDao
                    .insertiOrUpdateUsers(list)
                    .andThen(userDao.findById(1))
                    .flatMapCompletable { user -> userDao.delete(user) }
                    .andThen(userDao.getAll())
                    .map {
                        list -> list.forEach { Log.d(TAG, "${it.uid} / ${it.firstName} / ${it.lastName} / ${it.age}") }
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, { e -> e.printStackTrace() })
            }
    }
}

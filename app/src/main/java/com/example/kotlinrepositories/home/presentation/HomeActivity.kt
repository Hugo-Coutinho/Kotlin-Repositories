package com.example.kotlinrepositories.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.home.domain.entity.HomeEntity
import com.example.kotlinrepositories.home.domain.useCase.HomeUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity: AppCompatActivity() {

    private val useCase: HomeUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            useCase.getKotlinRepositories(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe({
                    print(it)
                }, {
                    it.printStackTrace()
                })
    }
}
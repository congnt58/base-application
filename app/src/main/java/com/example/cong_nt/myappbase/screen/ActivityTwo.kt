package com.example.cong_nt.myappbase.screen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View.VISIBLE
import com.example.cong_nt.myappbase.R
import com.example.cong_nt.myappbase.base.retrofit.InternetConnectionListener
import com.example.cong_nt.myappbase.base.retrofit.RetrofitRxBuilder
import com.example.cong_nt.myappbase.utils.L
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_two.*

class ActivityTwo : AppCompatActivity(), InternetConnectionListener {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    lateinit var link: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        button.setOnClickListener {
            RetrofitRxBuilder.getGgRequest(this)?.let { it ->

                Log.e("ActivityTwo", "retrofit != null")
                compositeDisposable.add(
                        it.redirectServer("EMDDI", "hanoi", 1)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    link = "https://${it.data.address}:443"
                                    Log.e("ActivityTwo", "$link")
                                    login(link)

                                }, {
                                    Log.e("ActivityTwo", "exception:  ${it.message}")
                                })
                )
            } ?: run {
                Log.e("ActivityTwo", "else if:  ${this}")
            }
        }
        btnCheckPhone.setOnClickListener {
            checkPhone()
        }
    }

    private fun login(link: String) {
        RetrofitRxBuilder.setNewBuilder(RetrofitRxBuilder.Builder().setLanguage("vi").url(link).sslMode(true).showLoging(true))

        compositeDisposable.add(RetrofitRxBuilder.getGgRequest(this).login("+84977005995", "123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ ob ->
                    Log.e("ActivityTwo", "token:  ${ob.data.token}")
                    checkPhone(ob.data.token)
                    btnCheckPhone.visibility = VISIBLE
                }, {
                    Log.e("ActivityTwo", "exception:  ${it.message}")
                })
        )
    }

    private fun checkPhone(token: String?) {
        RetrofitRxBuilder.setNewBuilder(RetrofitRxBuilder.Builder().setLanguage("vi").token(token).url(link).sslMode(true).showLoging(true))

        compositeDisposable.add(RetrofitRxBuilder.getGgRequest(this).checkPhoneNumber("+84977005995")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("ActivityTwo", "check phone message:  ${it.message}")
                }, {
                    Log.e("ActivityTwo", "check phone exception:  ${it.message}")
                })
        )
    }

    private fun checkPhone() {
        compositeDisposable.add(RetrofitRxBuilder.getGgRequest(this).checkPhoneNumber("+84977005995")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("ActivityTwo", "check phone message:  ${it.message}")
                }, {
                    Log.e("ActivityTwo", "check phone exception:  ${it.message}")
                })
        )
        startActivity(Intent(this, ActivityThree::class.java))
    }

    override fun onInternetUnavailbale() {
        Log.e("ActivityTwo", "no internet")
    }

    fun printfVoid(): Int{
        var a = 5
        L.e("var  = $a")
        return a
    }
}

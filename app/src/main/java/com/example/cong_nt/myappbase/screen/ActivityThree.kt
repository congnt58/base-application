package com.example.cong_nt.myappbase.screen

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration
import android.view.WindowManager
import com.example.cong_nt.myappbase.R
import com.example.cong_nt.myappbase.base.retrofit.InternetConnectionListener
import com.example.cong_nt.myappbase.base.retrofit.RetrofitRxBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_three.*


class ActivityThree : AppCompatActivity(), InternetConnectionListener {


    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        button.setOnClickListener {
            checkPhone()
        }
        // clear FLAG_TRANSLUCENT_STATUS flag:
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.dark_nav)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
//            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.statusbar)); //status bar or the time bar at the top
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        getSupportActionBar()?.hide();
//       setPadding(int left, int top, int right, int bottom)
        layoutThree.setPadding(0, 0, 0, getNavBarHeight(this))

    }

//    protected fun fitSystemWindows(insets: Rect): Boolean {
//        var bottomPadding = insets.bottom
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val resources = resources
//            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
//            if (resourceId > 0) {
//                bottomPadding += resources.getDimensionPixelSize(resourceId)
//            }
//        }
//        this.setPadding(viewActivity.getPaddingLeft() + insets.left, viewActivity.getPaddingTop() + insets.top,
//                viewActivity.getPaddingRight() + insets.right, viewActivity.getPaddingBottom() + bottomPadding)
//        insets.bottom = 0
//        insets.right = insets.bottom
//        insets.top = insets.right
//        insets.left = insets.top
//        return true
//    }

    fun getNavBarHeight(c: Context): Int {
        val result = 0
        val hasMenuKey = ViewConfiguration.get(c).hasPermanentMenuKey()
        val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)

        if (!hasMenuKey && !hasBackKey) {
            //The device has a navigation bar
            val resources = c.getResources()

            val orientation = resources.getConfiguration().orientation
            val resourceId: Int
            if (isTablet(c)) {
                resourceId = resources.getIdentifier(if (orientation == Configuration.ORIENTATION_PORTRAIT) "navigation_bar_height" else "navigation_bar_height_landscape", "dimen", "android")
            } else {
                resourceId = resources.getIdentifier(if (orientation == Configuration.ORIENTATION_PORTRAIT) "navigation_bar_height" else "navigation_bar_width", "dimen", "android")
            }

            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId)
            }
        }
        return result
    }

    private fun isTablet(c: Context): Boolean {
        return c.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    private fun checkPhone() {
        compositeDisposable.add(RetrofitRxBuilder.getGgRequest(this).checkPhoneNumber("+84977005995")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("ActivityThree", "check phone message:  ${it.message}")
                }, {
                    Log.e("ActivityThree", "check phone exception:  ${it.message}")
                })
        )
    }

    override fun onInternetUnavailbale() {
        Log.e("ActivityThree", "no internet")
    }
}

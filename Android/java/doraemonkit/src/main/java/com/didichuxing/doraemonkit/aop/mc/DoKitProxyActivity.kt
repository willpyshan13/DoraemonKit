package com.didichuxing.doraemonkit.aop.mc

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import com.didichuxing.doraemonkit.kit.core.DokitServiceEnum
import com.didichuxing.doraemonkit.kit.core.DokitServiceManager


/**
 * ================================================
 * 作    者：jint（金台）
 * 版    本：1.0
 * 创建日期：2020/11/12-16:02
 * 描    述：用于拦截Activity中的所有事件
 * 修订历史：
 * ================================================
 */
public open class DoKitProxyActivity : Activity() {

    companion object {
        private const val TAG = "DoKitProxyActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DokitServiceManager.dispatch(DokitServiceEnum.onCreate, this)
    }


    override fun onStart() {
        super.onStart()
        DokitServiceManager.dispatch(DokitServiceEnum.onStart, this)

    }

    override fun onResume() {
        super.onResume()
        DokitServiceManager.dispatch(DokitServiceEnum.onResume, this)

    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        DokitServiceManager.dispatch(DokitServiceEnum.dispatchTouchEvent, this)


        return super.dispatchTouchEvent(ev)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        DokitServiceManager.dispatch(
            DokitServiceEnum.onConfigurationChanged,
            this
        )
    }


    override fun onBackPressed() {
        super.onBackPressed()
        DokitServiceManager.dispatch(DokitServiceEnum.onBackPressed, this)
    }

    override fun onPause() {
        super.onPause()
        DokitServiceManager.dispatch(DokitServiceEnum.onPause, this)

    }

    override fun onStop() {
        super.onStop()
        DokitServiceManager.dispatch(DokitServiceEnum.onStop, this)

    }

    override fun onDestroy() {
        super.onDestroy()
        DokitServiceManager.dispatch(DokitServiceEnum.onDestroy, this)
    }

    override fun finish() {
        super.finish()
        DokitServiceManager.dispatch(DokitServiceEnum.finish, this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //LogHelper.i(TAG, "===onAttachedToWindow===")

    }


}
package com.didichuxing.doraemonkit.kit.core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.didichuxing.doraemonkit.DoraemonKit
import com.didichuxing.doraemonkit.constant.BundleKey
import com.didichuxing.doraemonkit.constant.FragmentIndex

object SimpleDokitStarter {
    fun startFloating(targetClass: Class<out AbsDokitView?>?) = DokitViewManager.getInstance().attach(DokitIntent(targetClass))

    @JvmStatic
    @JvmOverloads
    fun startFullScreen(targetClass: Class<out SimpleDokitFragment?>, context: Context?, bundle: Bundle? = null) {
        val ctx = context ?: DoraemonKit.APPLICATION!!.applicationContext
        ctx.startActivity(Intent(ctx, UniversalActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra(BundleKey.FRAGMENT_INDEX, FragmentIndex.FRAGMENT_SIMPLE_CUSTOM)
            putExtra(BundleKey.CUSTOM_FRAGMENT_CLASS, targetClass)
            if (bundle != null) {
                putExtras(bundle)
            }
        })
    }
}
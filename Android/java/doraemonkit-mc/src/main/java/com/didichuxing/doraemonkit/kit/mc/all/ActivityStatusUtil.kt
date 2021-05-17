package com.didichuxing.doraemonkit.kit.mc.all

import android.app.Activity
import android.view.View

/**
 * ================================================
 * 作    者：jint（金台）
 * 版    本：1.0
 * 创建日期：2020/12/3-20:14
 * 描    述：
 * 修订历史：
 * ================================================
 */
object ActivityStatusUtil {
    const val ACTIVITY_STATUS_UNKNOWN = -1
    const val ACTIVITY_STATUS_ONCREATE = 0
    const val ACTIVITY_STATUS_ONSTART = 1
    const val ACTIVITY_STATUS_ONRESUME = 2
    const val ACTIVITY_STATUS_ONPAUSE = 3
    const val ACTIVITY_STATUS_ONSTOP = 4
    const val ACTIVITY_STATUS_ONDESTROY = 5

    val activityStatus: MutableMap<String, Int> by lazy {
        mutableMapOf<String, Int>()
    }


    fun isActivityOnResume(view: View): Boolean {
        if (view.context is Activity) {
            val activity = view.context as Activity
            val status = ActivityStatusUtil.activityStatus[activity::class.java.simpleName]
            if (status == ActivityStatusUtil.ACTIVITY_STATUS_ONRESUME) {
                return true
            }

        }
        return false
    }
}
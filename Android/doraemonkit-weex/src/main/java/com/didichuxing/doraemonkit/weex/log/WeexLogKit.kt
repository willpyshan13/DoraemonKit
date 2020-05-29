package com.didichuxing.doraemonkit.weex.log

import android.content.Context
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.didichuxing.doraemonkit.kit.core.DokitIntent
import com.didichuxing.doraemonkit.kit.core.DokitViewManager
import com.didichuxing.doraemonkit.kit.loginfo.LogInfoManager
import com.didichuxing.doraemonkit.weex.R

/**
 * @author haojianglong
 * @date 2019-06-11
 */
class WeexLogKit : AbstractKit() {
    override val name: Int
        get() = R.string.dk_console_log_name

    override val icon: Int
        get() = R.mipmap.dk_log_info

    override fun onClick(context: Context?) {
        kotlinTip()
    }

    override fun onAppInit(context: Context?) {}
    override val isInnerKit: Boolean
        get() = true

    override fun innerKitId(): String {
        return "dokit_sdk_weex_ck_log"
    }
}
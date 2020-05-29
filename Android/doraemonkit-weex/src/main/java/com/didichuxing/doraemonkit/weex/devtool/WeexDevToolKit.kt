package com.didichuxing.doraemonkit.weex.devtool

import android.content.Context
import android.content.Intent
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.didichuxing.doraemonkit.weex.R

/**
 * @author haojianglong
 * @date 2019-06-11
 */
class WeexDevToolKit : AbstractKit() {
    override val name: Int
        get() = R.string.dk_dev_tool_name

    override val icon: Int
        get() = R.mipmap.dk_custom

    override fun onClick(context: Context?) {
        kotlinTip()
    }

    override fun onAppInit(context: Context?) {}
    override val isInnerKit: Boolean
        get() = true

    override fun innerKitId(): String {
        return "dokit_sdk_weex_ck_devtool"
    }
}
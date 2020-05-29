package com.didichuxing.doraemonkit.weex.storage

import android.content.Context
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.didichuxing.doraemonkit.weex.R
import com.didichuxing.doraemonkit.weex.common.DKCommonActivity

/**
 * @author haojianglong
 * @date 2019-06-11
 */
class WeexStorageKit : AbstractKit() {
    override val name: Int
        get() = R.string.dk_storage_cache_name

    override val icon: Int
        get() = R.mipmap.dk_file_explorer

    override fun onClick(context: Context?) {
        kotlinTip()
    }

    override fun onAppInit(context: Context?) {}
    override val isInnerKit: Boolean
        get() = true

    override fun innerKitId(): String {
        return "dokit_sdk_weex_ck_storage"
    }
}
package com.didichuxing.doraemonkit.kit.core

import android.os.Bundle
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import com.didichuxing.doraemonkit.constant.BundleKey
import com.didichuxing.doraemonkit.constant.FragmentIndex


/**
 * Created by wanglikun on 2018/10/26.
 * app基础信息Activity
 */
open class UniversalActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle == null) {
            finish()
            return
        }
        val index = bundle.getInt(BundleKey.FRAGMENT_INDEX)
        if (index == 0) {
            finish()
            return
        }
        var fragmentClass: Class<out BaseFragment?>? = null
//        when (index) {
//            FragmentIndex.FRAGMENT_SYS_INFO -> fragmentClass = SysInfoFragment::class.java
//            FragmentIndex.FRAGMENT_FILE_EXPLORER -> fragmentClass = FileExplorerFragment::class.java
//            FragmentIndex.FRAGMENT_LOG_INFO_SETTING -> fragmentClass = LogInfoSettingFragment::class.java
//            FragmentIndex.FRAGMENT_COLOR_PICKER_SETTING -> fragmentClass = ColorPickerSettingFragment::class.java
//            FragmentIndex.FRAGMENT_DB_DEBUG -> fragmentClass = DbDebugFragment::class.java
//            FragmentIndex.FRAGMENT_GPS_MOCK -> fragmentClass = GpsMockFragment::class.java
//            FragmentIndex.FRAGMENT_ALIGN_RULER_SETTING -> fragmentClass = AlignRulerSettingFragment::class.java
//            FragmentIndex.FRAGMENT_WEB_DOOR -> fragmentClass = WebDoorFragment::class.java
//            FragmentIndex.FRAGMENT_DATA_CLEAN -> fragmentClass = DataCleanFragment::class.java
//            FragmentIndex.FRAGMENT_WEAK_NETWORK -> fragmentClass = WeakNetworkFragment::class.java
//            FragmentIndex.FRAGMENT_FRAME_INFO -> fragmentClass = FrameInfoFragment::class.java
//            FragmentIndex.FRAGMENT_BLOCK_MONITOR -> fragmentClass = BlockMonitorFragment::class.java
//            FragmentIndex.FRAGMENT_CRASH -> fragmentClass = CrashCaptureMainFragment::class.java
//            FragmentIndex.FRAGMENT_NETWORK_MONITOR -> fragmentClass = NetWorkMonitorFragment::class.java
//            FragmentIndex.FRAGMENT_CPU -> fragmentClass = CpuMainPageFragment::class.java
//            FragmentIndex.FRAGMENT_RAM -> fragmentClass = RamMainPageFragment::class.java
//            FragmentIndex.FRAGMENT_TIME_COUNTER -> fragmentClass = TimeCounterFragment::class.java
//            FragmentIndex.FRAGMENT_WEB_DOOR_DEFAULT -> fragmentClass = WebDoorDefaultFragment::class.java
//            FragmentIndex.FRAGMENT_LARGE_PICTURE -> fragmentClass = LargePictureFragment::class.java
//            FragmentIndex.FRAGMENT_METHOD_COST -> fragmentClass = MethodCostFragment::class.java
//            FragmentIndex.FRAGMENT_NETWORK_MOCK -> fragmentClass = NetWorkMockFragment::class.java
//            FragmentIndex.FRAGMENT_MOCK_TEMPLATE_PREVIEW -> fragmentClass = MockTemplatePreviewFragment::class.java
//            FragmentIndex.FRAGMENT_HEALTH -> fragmentClass = HealthFragment::class.java
//            FragmentIndex.FRAGMENT_APP_START -> fragmentClass = AppStartInfoFragment::class.java
//            FragmentIndex.FRAGMENT_DOKIT_SETTING -> fragmentClass = DokitSettingFragment::class.java
//            FragmentIndex.FRAGMENT_DOKIT_MANAGER -> fragmentClass = DokitManagerFragment::class.java
//            else -> {
//            }
//        }
        if (fragmentClass == null) {
            finish()

            ToastUtils.showShort(String.format("fragment index %s not found", index), Toast.LENGTH_SHORT)
            return
        }
        showContent(fragmentClass, bundle)
    }

    override fun onDestroy() {
        try {
            super.onDestroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
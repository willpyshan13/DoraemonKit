package com.didichuxing.doraemonkit.kit.core

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.didichuxing.doraemonkit.R
import com.didichuxing.doraemonkit.kit.health.AppHealthInfoUtil
import com.didichuxing.doraemonkit.widget.recyclerview.AbsRecyclerAdapter
import com.didichuxing.doraemonkit.widget.recyclerview.AbsViewBinder

/**
 * Created by wanglikun on 2018/9/14.
 */
class SettingItemAdapter(context: Context?) : AbsRecyclerAdapter<AbsViewBinder<SettingItem?>?, SettingItem?>(context) {
    private var mOnSettingItemClickListener: OnSettingItemClickListener? = null
    private var mOnSettingItemSwitchListener: OnSettingItemSwitchListener? = null
    override fun createViewHolder(view: View, viewType: Int): AbsViewBinder<SettingItem?>? {
        return SettingItemViewHolder(view)
    }

    override fun createView(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): View {
        return inflater.inflate(R.layout.dk_item_setting, parent, false)
    }

    inner class SettingItemViewHolder(view: View?) : AbsViewBinder<SettingItem?>(view) {
        private lateinit var mDesc: TextView
        private lateinit var mMenuSwitch: CheckBox
        private lateinit var mIcon: ImageView
        private lateinit var mRightDesc: TextView

        override fun getViews() {
            mMenuSwitch = getView(R.id.menu_switch)
            mDesc = getView(R.id.desc)
            mIcon = getView(R.id.right_icon)
            mRightDesc = getView(R.id.right_desc)
        }

        override fun bind(settingItem: SettingItem?) {
            settingItem?.let {
                mDesc.setText(it.desc)
                if (it.canCheck) {
                    mMenuSwitch.visibility = View.VISIBLE
                    mMenuSwitch.isChecked = it.isChecked
                    mMenuSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
                        if (isMatched(it.desc)) {
                            if (AppHealthInfoUtil.getInstance().isAppHealthRunning) {
                                mMenuSwitch.isChecked = true
                                return@OnCheckedChangeListener
                            }
                        }
                        it.isChecked = isChecked
                        mOnSettingItemSwitchListener!!.onSettingItemSwitch(mMenuSwitch, it, isChecked)
                    })
                }
                if (it.icon != 0) {
                    mIcon.visibility = View.VISIBLE
                    mIcon.setImageResource(it.icon)
                }
                if (!TextUtils.isEmpty(it.rightDesc)) {
                    mRightDesc.visibility = View.VISIBLE
                    mRightDesc.text = it.rightDesc
                }
            }


        }

        protected override fun onViewClick(view: View?, data: SettingItem?) {
            super.onViewClick(view, data)
            mOnSettingItemClickListener?.onSettingItemClick(view, data)
        }
    }

    /**
     * 是否命中
     *
     * @return
     */
    private fun isMatched(@StringRes desc: Int): Boolean {
        val resources = intArrayOf(
                R.string.dk_weak_network_switch,
                R.string.dk_item_block_switch,
                R.string.dk_crash_capture_switch,
                R.string.dk_cpu_detection_switch,
                R.string.dk_frameinfo_detection_switch,
                R.string.dk_ram_detection_switch)
        var isMatches = false
        for (res in resources) {
            if (res == desc) {
                isMatches = true
                break
            }
        }
        return isMatches
    }

    fun setOnSettingItemClickListener(onSettingItemClickListener: OnSettingItemClickListener?) {
        mOnSettingItemClickListener = onSettingItemClickListener
    }

    fun setOnSettingItemSwitchListener(onSettingItemSwitchListener: OnSettingItemSwitchListener?) {
        mOnSettingItemSwitchListener = onSettingItemSwitchListener
    }

    interface OnSettingItemClickListener {
        fun onSettingItemClick(view: View?, data: SettingItem?)
    }

    interface OnSettingItemSwitchListener {
        fun onSettingItemSwitch(view: View?, data: SettingItem?, on: Boolean)
    }
}
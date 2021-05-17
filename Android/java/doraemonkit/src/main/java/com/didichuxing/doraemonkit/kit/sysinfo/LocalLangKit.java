package com.didichuxing.doraemonkit.kit.sysinfo;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.didichuxing.doraemonkit.kit.AbstractKit;
import com.didichuxing.doraemonkit.util.DoKitSystemUtil;
import com.google.auto.service.AutoService;

/**
 * 进入本地语言设置页面
 * Created by jint on 2018/6/22.
 */
@AutoService(AbstractKit.class)
public class LocalLangKit extends AbstractKit {


    @Override
    public int getName() {
        return R.string.dk_kit_local_lang;
    }

    @Override
    public int getIcon() {
        return R.mipmap.dk_kit_local_lang;
    }

    @Override
    public void onClick(Context context) {
        DoKitSystemUtil.startLocalActivity(context);
    }

    @Override
    public void onAppInit(Context context) {

    }


    @Override
    public boolean isInnerKit() {
        return true;
    }

    @Override
    public String innerKitId() {
        return "dokit_sdk_comm_ck_local_lang";
    }
}

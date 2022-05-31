package com.zjamss.http;

import com.zjamss.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.awt.*;

/**
 * @Program: AutoImportNote
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-30 21:16
 **/
public abstract class AbstractCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        Utils.displayTray("请求发送失败\n"+throwable.getMessage(), TrayIcon.MessageType.ERROR);
    }
}

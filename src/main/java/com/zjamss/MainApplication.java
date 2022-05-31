package com.zjamss;

import com.zjamss.http.AbstractCallBack;
import com.zjamss.http.Request;
import com.zjamss.http.http;
import com.zjamss.listener.FileListener;
import com.zjamss.model.NoteMap;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.zjamss.util.Utils;

import java.awt.*;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.zjamss.constants.Constants.ADMIN_TOKEN;
import static com.zjamss.constants.Constants.ROOT_DIR;

/**
 * @Program: AutoImportNote
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-28 23:04
 **/
public class MainApplication {

    public static NoteMap noteMap;

    public static void upload(String path) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File file = new File(path);
        if (!file.isFile()) {
            return;
        }
        byte[] bytes = Utils.fileToBin(file);
        RequestBody param = RequestBody.create(MediaType.parse("multipart/form-data"), bytes);
        String slug = file.getName().split("\\.")[0].toLowerCase(Locale.ROOT);
        builder.addFormDataPart("file", slug, param);
        MultipartBody.Part part = builder.build().part(0);

        http.request.upload(part, ADMIN_TOKEN).enqueue(new AbstractCallBack<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    Utils.displayTray("同步成功", TrayIcon.MessageType.INFO);
                } else {
                    Utils.displayTray("同步失败:" + response.code(), TrayIcon.MessageType.WARNING);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                super.onFailure(call, throwable);
            }
        });
    }

    private static void registerListener() throws Exception {
        long interval = TimeUnit.SECONDS.toMillis(1);
        FileAlterationObserver observer = new FileAlterationObserver(new File(ROOT_DIR));
        observer.addListener(new FileListener());
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();
    }


    public static void main(String[] args) throws Exception {
        noteMap = new NoteMap();
        registerListener();
    }
}
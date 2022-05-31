package com.zjamss.listener;

import com.zjamss.MainApplication;
import com.zjamss.constants.Constants;
import com.zjamss.http.AbstractCallBack;
import com.zjamss.http.http;
import com.zjamss.model.dto.PostResponse;
import com.zjamss.model.vo.FrontMatter;
import com.zjamss.util.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sun.applet.Main;

import java.io.File;
import java.util.Locale;

/**
 * @Program: AutoImportNote
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-30 19:40
 **/
public class FileListener extends AbstractFileListener {
    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);
        if(file.getName().equals("Untitled.md")) return;
        System.out.println("创建" + file.getAbsoluteFile());
        final FrontMatter frontMatter = Utils.appendSign(file);
        MainApplication.noteMap.put(frontMatter.getTitle(),frontMatter.getSlug());
    }

    @Override
    public void onFileChange(File file) {
        super.onFileChange(file);
        if(file.getName().equals("Untitled.md") || !file.getName().contains(".md")) return;
        System.out.println("修改" + file.getAbsoluteFile());

        String fileName = file.getName().split("\\.")[0];
        String slug = MainApplication.noteMap.get(fileName);
        if(slug == null){
            MainApplication.noteMap.put(fileName,fileName.toLowerCase(Locale.ROOT));
        }
        http.request.getPostBySlug(Constants.API_ACCESS_KEY,slug)
                .enqueue(new AbstractCallBack<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        if(response.code() == 200){
                            http.request.deletePostById(response.body().getData().getId(),Constants.ADMIN_TOKEN)
                                    .enqueue(new AbstractCallBack<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            if(response.code() == 200){
                                                MainApplication.upload(file.getAbsolutePath());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                                            super.onFailure(call, throwable);
                                        }
                                    });
                        }else if(response.code() == 404){
                            MainApplication.upload(file.getAbsolutePath());
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable throwable) {
                        super.onFailure(call, throwable);
                    }
                });
    }

    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);
        if(file.getName().equals("Untitled.md") || !file.getName().contains(".md")) return;
        MainApplication.noteMap.remove(file.getName().split("\\.")[0]);
    }
}

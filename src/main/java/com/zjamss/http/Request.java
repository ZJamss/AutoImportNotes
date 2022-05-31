package com.zjamss.http;


import com.zjamss.model.dto.PostResponse;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @Program: AutoImportNote
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-28 22:56
 **/
public interface Request {
    @Multipart
    @POST("/api/admin/backups/markdown/import")
    Call<ResponseBody> upload(@Part MultipartBody.Part part, @Query("admin_token") String token);

    @GET("/api/content/posts/slug")
    Call<PostResponse> getPostBySlug(@Query("api_access_key") String key,@Query("slug") String slug);

    @DELETE("/api/admin/posts/{id}")
    Call<ResponseBody> deletePostById(@Path("id") int id,@Query("admin_token") String token);
}


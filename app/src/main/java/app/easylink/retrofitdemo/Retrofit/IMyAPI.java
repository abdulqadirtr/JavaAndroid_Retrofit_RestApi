package app.easylink.retrofitdemo.Retrofit;

import java.util.List;

import app.easylink.retrofitdemo.Model.Post;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyAPI {
    @GET("posts")
    Observable<List<Post>> getPosts();


}

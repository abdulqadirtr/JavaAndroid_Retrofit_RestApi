package app.easylink.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.List;

import app.easylink.retrofitdemo.Adapter.PostAdapteer;
import app.easylink.retrofitdemo.Model.Post;
import app.easylink.retrofitdemo.Retrofit.IMyAPI;
import app.easylink.retrofitdemo.Retrofit.RetrofitClient;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    IMyAPI myAPI;
    RecyclerView recyler_posts;
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calliing Retrofit Api
        Retrofit retrofit=RetrofitClient.getInstance();
        myAPI=retrofit.create(IMyAPI.class);
        recyler_posts=(RecyclerView)findViewById(R.id.recycler_posts);
        recyler_posts.setHasFixedSize(true);
        recyler_posts.setLayoutManager(new LinearLayoutManager( this));

        fetchDat();
    }

    private void fetchDat() {
        compositeDisposable.add(myAPI.getPosts()
               .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        displayData(posts);

                    }
                    }));

    }

    private void displayData(List<Post> posts) {
        PostAdapteer adapteer=new PostAdapteer(this,posts);
        recyler_posts.setAdapter(adapteer);

    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}

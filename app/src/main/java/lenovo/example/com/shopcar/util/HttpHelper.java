package lenovo.example.com.shopcar.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class HttpHelper {

    private BaseService mBaseService;
    private Observable<ResponseBody> mObservable;

    public HttpHelper() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://www.zhaoapi.cn/")
                .build();

        mBaseService = mRetrofit.create(BaseService.class);
    }

    //get请求
    public HttpHelper get(String url, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        mObservable = mBaseService.get(url, map);
        setObservable();
        return this;
    }

    //post请求
    public HttpHelper post(String url, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        mObservable = mBaseService.post(url, map);
        setObservable();
        return this;
    }

    //设置被观察者
    private void setObservable() {
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


  private Observer observer=new Observer<ResponseBody>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(ResponseBody responseBody) {
          try {
              String data=responseBody.string();
              listener.success(data);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }

      @Override
      public void onError(Throwable e) {
         String error=e.getMessage();
         listener.fail(error);
      }

      @Override
      public void onComplete() {

      }
  };

    private HttpListener listener;
    public void result(HttpListener listener){
        this.listener=listener;
    }

}

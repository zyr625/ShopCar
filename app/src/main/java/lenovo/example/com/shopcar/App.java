package lenovo.example.com.shopcar;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * author：shashe
 * 日期：2018/11/16
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);//初始化
    }
}

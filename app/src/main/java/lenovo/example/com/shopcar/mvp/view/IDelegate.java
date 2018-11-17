package lenovo.example.com.shopcar.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public interface IDelegate {

    void initData();//初始化方法，联网请求，数据库操作……

    View getRootView();//获取View,给Activity Fragment设置View

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);//创建布局

    void initContext(Context context);

}

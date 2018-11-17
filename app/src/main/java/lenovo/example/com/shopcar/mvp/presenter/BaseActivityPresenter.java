package lenovo.example.com.shopcar.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import butterknife.ButterKnife;
import lenovo.example.com.shopcar.mvp.view.AppDelegate;


public abstract class BaseActivityPresenter<T extends AppDelegate> extends AppCompatActivity {

    protected   T delegate;

    public abstract Class<T> getClassDelegate();

    public BaseActivityPresenter(){
        try {
            delegate=getClassDelegate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(delegate.getRootView());
        ButterKnife.bind(this);//绑定ButterKnife
        delegate.initContext(this);
        initView();
        delegate.initData();
    }

    public void initView(){}
}

package lenovo.example.com.shopcar.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import lenovo.example.com.shopcar.mvp.view.AppDelegate;

public abstract class BaseFragmentPresenter <T extends AppDelegate> extends Fragment {
    protected   T delegate;

    public abstract Class<T> getClassDelegate();

    public BaseFragmentPresenter(){
        try {
            delegate=getClassDelegate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        delegate.create(inflater,container,savedInstanceState);
        View view=delegate.getRootView();
        ButterKnife.bind(this,view);//绑定ButterKnife
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        delegate.initContext(getActivity());
        initView();
        delegate.initData();
    }

    public void initView(){}
}

package lenovo.example.com.shopcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import butterknife.BindView;
import lenovo.example.com.shopcar.mvp.presenter.BaseActivityPresenter;

public class SearchActivity extends BaseActivityPresenter<SearchActivityPresenter> {


    @BindView(R.id.searchview)
    SearchView searchView;
    @BindView(R.id.self)
    SelfView selfView;
    @BindView(R.id.recy)
    RecyclerView recyclerView;
    @Override
    public Class<SearchActivityPresenter> getClassDelegate() {
        return SearchActivityPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        delegate.initView(searchView,selfView,recyclerView);
    }
}

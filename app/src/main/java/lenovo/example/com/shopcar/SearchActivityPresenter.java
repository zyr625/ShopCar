package lenovo.example.com.shopcar;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lenovo.example.com.shopcar.adapter.SearchAdapter;
import lenovo.example.com.shopcar.model.SearchBean;
import lenovo.example.com.shopcar.mvp.view.AppDelegate;
import lenovo.example.com.shopcar.util.HttpHelper;
import lenovo.example.com.shopcar.util.HttpListener;

/**
 * author：shashe
 * 日期：2018/11/16
 */
public class SearchActivityPresenter extends AppDelegate{
    private Context context;
    private SearchView searchView;
    private SelfView selfView;
    private List<String> list = new ArrayList<>();
    private HashMap<String, String> map = new HashMap<>();
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.length()>0){
                    searchView.setIconified(true);
                }
                Log.i("SearchActivity",s);
                list.add(0,s);
                selfView.setListData(list);
                map.put("keywords",s);
                map.put("page","1");
                searchAdapter = new SearchAdapter(context);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(searchAdapter);
                doHttp(map);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void doHttp(HashMap<String, String> map) {

        new HttpHelper().get("/product/searchProducts",map).result(new HttpListener() {
            @Override
            public void success(String data) {
                SearchBean searchBean = new Gson().fromJson(data, SearchBean.class);
                searchAdapter.setList(searchBean.getData());
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void initContext(Context context) {
        this.context=context;
    }

    public void initView(SearchView searchView, SelfView selfView, RecyclerView recyclerView) {
        this.searchView=searchView;
        this.selfView=selfView;
        this.recyclerView=recyclerView;
    }
}

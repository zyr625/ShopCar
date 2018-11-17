package lenovo.example.com.shopcar.adapter;

import android.content.Context;

import java.util.List;

import lenovo.example.com.shopcar.R;
import lenovo.example.com.shopcar.model.SearchBean;

/**
 * author：shashe
 * 日期：2018/11/16
 */
public class SearchAdapter extends BaseAdapter<SearchBean.DataBean>{
    private Context context;
    public SearchAdapter(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.search_layout;
    }

    @Override
    protected void bindData(MyViewHolder myViewHolder, List<SearchBean.DataBean> list, int i) {
        myViewHolder.setPic(R.id.drawview,list.get(i).getImages().split("\\|")[0]);
        myViewHolder.setText(R.id.name_title,list.get(i).getTitle());
        myViewHolder.setText(R.id.price_txt,list.get(i).getPrice()+"");
    }
}

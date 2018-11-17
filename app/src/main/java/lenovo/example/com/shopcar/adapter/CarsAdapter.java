package lenovo.example.com.shopcar.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import java.util.List;

import lenovo.example.com.shopcar.R;
import lenovo.example.com.shopcar.model.CarsBean;

/**
 * author：shashe
 * 日期：2018/11/16
 */
public class CarsAdapter extends BaseAdapter<CarsBean.DataBean> {

    private Context context;

    public CarsAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cars_layout;
    }

    @Override
    protected void bindData(MyViewHolder myViewHolder, final List<CarsBean.DataBean> listCars, final int i) {
        try {
            final CarsBean.DataBean dataBean = listCars.get(i);
            myViewHolder.setText(R.id.name, dataBean.getSellerName());
            CheckBox checkBox = myViewHolder.get(R.id.check);
            checkBox.setChecked(dataBean.isCheck());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dataBean.isCheck()) {
                        dataBean.setCheck(false);
                    } else {
                        dataBean.setCheck(true);
                    }
                    List<CarsBean.DataBean.ListBean> list = dataBean.getList();
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheck(dataBean.isCheck());
                    }
                    listener.callBack(listCars);
                    notifyItemChanged(i);
                }
            });
            CarsChildAdapter carsChildAdapter = new CarsChildAdapter(context);
            RecyclerView recyclerView = myViewHolder.get(R.id.cars_recycler);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(carsChildAdapter);
            carsChildAdapter.setList(dataBean.getList());
            carsChildAdapter.setCallBackListener(new CarsChildAdapter.CallBackListener() {
                @Override
                public void callBack() {
                    int num=0;
                    for (int i = 0; i <dataBean.getList().size() ; i++) {
                        if (dataBean.getList().get(i).isCheck()){
                            num++;
                        }
                    }
                    if (num==dataBean.getList().size()){
                        dataBean.setCheck(true);
                    }else {
                        dataBean.setCheck(false);
                    }
                    notifyItemChanged(i);
                    listener.callBack(listCars);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CallBackListener listener;

    public void setCallBackListener(CallBackListener listener) {
        this.listener = listener;
    }

    public interface CallBackListener {
        void callBack(List<CarsBean.DataBean> list);
    }

}

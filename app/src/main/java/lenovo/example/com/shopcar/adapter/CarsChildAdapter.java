package lenovo.example.com.shopcar.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import java.util.List;

import lenovo.example.com.shopcar.AddCarView;
import lenovo.example.com.shopcar.R;
import lenovo.example.com.shopcar.model.CarsBean;

/**
 * author：shashe
 * 日期：2018/11/16
 */
public class CarsChildAdapter extends BaseAdapter<CarsBean.DataBean.ListBean> {
    public CarsChildAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cars_child_layout;
    }

    @Override
    protected void bindData(MyViewHolder myViewHolder, List<CarsBean.DataBean.ListBean> list, final int i) {
        final CarsBean.DataBean.ListBean listBean = list.get(i);
        myViewHolder.setPic(R.id.simple, listBean.getImages().split("\\|")[0]);
        myViewHolder.setText(R.id.name, listBean.getTitle());
        AddCarView addCarView=myViewHolder.get(R.id.addcarview);
        addCarView.setCarNum(listBean.getNum());
        addCarView.setOnClickListener(new AddCarView.OnClickListener() {
            @Override
            public void carNum(int num) {
                listBean.setNum(num);
                listener.callBack();
            }
        });
        myViewHolder.setText(R.id.price, listBean.getPrice() + "");
        CheckBox checkBox = myViewHolder.get(R.id.check);
        checkBox.setChecked(listBean.isCheck());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listBean.isCheck()){
                    listBean.setCheck(false);
                }else {
                    listBean.setCheck(true);
                }
                //notifyItemChanged(i);
                listener.callBack();
            }
        });
    }

    private CallBackListener listener;

    public void setCallBackListener(CallBackListener listener) {
        this.listener = listener;
    }

    public interface CallBackListener {
        void callBack();
    }
}

package lenovo.example.com.shopcar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class AddCarView extends RelativeLayout implements View.OnClickListener {
    private EditText editShopCar;

    public AddCarView(Context context) {
        super(context);
        initView(context);
    }

    public AddCarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AddCarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private Context context;
    private void initView(Context context) {
        this.context=context;
        View view=View.inflate(context, R.layout.add_car_layout,null);
        editShopCar=(EditText)view.findViewById(R.id.edit_shop_car);
        view.findViewById(R.id.jian_car).setOnClickListener(this);
        view.findViewById(R.id.add_car).setOnClickListener(this);
        addView(view);
    }

    //设置数量
    public void setCarNum(int num){
        editShopCar.setText(num+"");
        if(listener!=null){
            listener.carNum(num);
        }
    }

    @Override
    public void onClick(View view) {
       int num= Integer.parseInt(editShopCar.getText().toString());
        switch (view.getId()){
            case R.id.add_car://加
                num++;
                setCarNum(num);
                break;
            case R.id.jian_car://减
                if(num==1){
                    Toast.makeText(context,"最少只有一个哦~",Toast.LENGTH_LONG).show();
                }else{
                    num--;
                }
                setCarNum(num);
                break;
        }
    }

    private OnClickListener listener;
    public void setOnClickListener(OnClickListener listener){
        this.listener=listener;
    }

    public interface OnClickListener{
        void carNum(int num);
    }
}

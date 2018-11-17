package lenovo.example.com.shopcar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * author：shashe
 * 日期：2018/11/16
 */
public class SelfView extends RelativeLayout{
    private LinearLayout mLayout;

    public SelfView(Context context) {
        super(context);
        init(context);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;
    private void init(Context context) {
        this.context=context;
        //第一步，创建垂直的LinearLayout
        View view= View.inflate(context, R.layout.self_view,null);
        //垂直方向
        mLayout=(LinearLayout) view.findViewById(R.id.layout_all);
        addView(view);
    }

    private  LinearLayout view1;
    private int position=0;
    public void setList() {
        mLayout.removeAllViews();
        //第二步，创建一个水平的LinearLayout
        view1= (LinearLayout) View.inflate(context,R.layout.self_view_h,null);



        mLayout.addView(view1);
        view1.removeAllViews();
        int len=0;
        for (int a=0;a<listData.size();a++){
            position=a;
            String data= listData.get(a);
            len+=data.length();
            if(len>15){
                view1= (LinearLayout) View.inflate(context,R.layout.self_view_h,null);
                mLayout.addView(view1);
                len=0;
            }
            final View view2=View.inflate(context,R.layout.self_view_text,null);
            final TextView textView=(TextView) view2.findViewById(R.id.tv_txt);
            textView.setText(data);
            view1.addView(view2);

            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) view2.getLayoutParams();
            params.weight=1;
            params.topMargin=10;
            params.rightMargin=10;
            params.leftMargin=10;
            view2.setLayoutParams(params);

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,textView.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });

            //长按删除
            textView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    view1.removeView(view2);
                    listData.remove(position);
                    setList();
                    return true;
                }
            });

        }

    }

    //传递数据
    private List<String> listData;
    public void setListData(List<String> listData) {
        this.listData=listData;
        setList();
    }
}

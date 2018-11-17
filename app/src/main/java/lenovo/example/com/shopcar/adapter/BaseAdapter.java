package lenovo.example.com.shopcar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.shopcar.model.IModel;

/**
 * author：shashe
 * 日期：2018/11/16
 */
public abstract class BaseAdapter<D extends IModel> extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {
    private Context context;
    private List<D> list = new ArrayList<>();
    //传递数据
    public void setList(List<D> list){
        this.list=list;
        notifyDataSetChanged();
    }

    public BaseAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, getLayoutId(), null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    protected abstract int getLayoutId();

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.MyViewHolder myViewHolder, int i) {
        bindData(myViewHolder,list,i);
    }

    protected abstract void bindData(MyViewHolder myViewHolder, List<D> list, int i);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private SparseArray<View> views=new SparseArray<>();
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
        public <T extends View> T get(int id){
            T view= (T) views.get(id);

            if(view==null){
                view=itemView.findViewById(id);
                views.put(id,view);
            }
            return view;
        }
        public void setText(int id,String msg){
           TextView textView= get(id);
           textView.setText(msg);
        }
        public void setPic(int id,String url){
            SimpleDraweeView simpleDraweeView= get(id);
            simpleDraweeView.setImageURI(url);
        }
    }
}

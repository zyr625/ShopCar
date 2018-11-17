package lenovo.example.com.shopcar.mvp.view;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class AppDelegate implements IDelegate{
    private View rootView;



    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
       rootView= inflater.inflate(getLayoutId(),viewGroup,false);
    }

    public abstract int getLayoutId();


    private SparseArray<View> views=new SparseArray<>();


    public <T extends View> T get(int id){
       T view= (T) views.get(id);

       if(view==null){
           view=rootView.findViewById(id);
           views.put(id,view);
       }
        return view;
    }

    public void setClick(View.OnClickListener listener,int... ids){
        if(ids==null){
            return;
        }
        for (int id:ids){
            get(id).setOnClickListener(listener);
        }
    }

}

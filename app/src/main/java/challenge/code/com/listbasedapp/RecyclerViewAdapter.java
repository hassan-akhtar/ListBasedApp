package challenge.code.com.listbasedapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<String> items;
    Context mcontext;
    View mView;
    ViewHolder vHolder;

    public RecyclerViewAdapter(Context context, List<String> items){
        this.items = items;
        mcontext = context;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        mView = LayoutInflater.from(mcontext).inflate(R.layout.recyclerview_items,parent,false);
        vHolder = new ViewHolder(mView);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.tvName.setText(items.get(position));
    }

    @Override
    public int getItemCount(){
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;

        public ViewHolder(View v){
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvItem);
        }
    }

}
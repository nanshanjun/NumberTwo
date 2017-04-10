package com.bwei.newstart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    List<String> list=new ArrayList<>();
    private myAdapter my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rv);

        iditData();

        rv.setLayoutManager(new LinearLayoutManager(this));

        my = new myAdapter();

        my.addrest(list);

        rv.setAdapter(my);

    }

    private void iditData() {

        for (int i = 0; i < 10; i++) {

            list.add("llll"+i);

        }

    }

    class myAdapter extends RecyclerView.Adapter<myAdapter.viewHolder>{

        List<String> llist = new ArrayList<String>();

        public void addrest(List<String> llist){
            this.llist.clear();
            this.llist.addAll(list);
            this.notifyDataSetChanged();
        }

        class viewHolder extends RecyclerView.ViewHolder{

            TextView tv;

            public viewHolder(View itemView) {
                super(itemView);

                tv= (TextView) itemView.findViewById(R.id.tv);

            }
        }

        @Override
        public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            viewHolder holder=new viewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.recycleview,null));

            return holder;
        }

        @Override
        public void onBindViewHolder(viewHolder holder, int position) {

            holder.tv.setText(llist.get(position));

        }

        @Override
        public int getItemCount() {
            return llist.size();
        }
    }
}

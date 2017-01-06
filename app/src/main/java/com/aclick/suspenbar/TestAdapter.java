package com.aclick.suspenbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aclick.suspenbar.bean.User;

import java.util.List;

/**
 * Created by cxw on 2017/1/6.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder>{
    private List<User> userList;
    private Context context;

    public TestAdapter(Context context,List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_test,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        User user = userList.get(position);
        holder.iv_avatar.setImageResource(user.getAvatarResId());
        holder.tv_name.setText(user.getName());
        holder.tv_content.setText("内容内容内容内容内容内容内容内容\n" +
                "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n" +position+
                "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n" +position+
                "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n" +position+
                "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n"+position);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_content;
        private ImageView iv_avatar;
        private TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            iv_avatar= (ImageView) itemView.findViewById(R.id.iv_avatar);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}

package com.aclick.suspenbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aclick.suspenbar.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView rv_test;
    private LinearLayout ll_header;
    private ImageView iv_avatar;
    private TextView tv_name;

    private LinearLayoutManager linearLayoutManager;
    private TestAdapter testAdapter;


    private List<User> userList;
    private int headerHeight;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList =new ArrayList<>();
        userList.add(new User(R.mipmap.bi,"BI"));
        userList.add(new User(R.mipmap.chuchai,"出差"));
        userList.add(new User(R.mipmap.feiyongbaoxiao,"费用报销"));
        userList.add(new User(R.mipmap.gaizhang,"盖章"));
        userList.add(new User(R.mipmap.jiaban,"加班"));

        testAdapter=new TestAdapter(this, userList);

        rv_test= (RecyclerView) findViewById(R.id.rv_test);
        ll_header= (LinearLayout) findViewById(R.id.ll_header);

        iv_avatar= (ImageView) findViewById(R.id.iv_avatar);
        tv_name = (TextView) findViewById(R.id.tv_name);

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        rv_test.setLayoutManager(linearLayoutManager);
        rv_test.setAdapter(testAdapter);



        rv_test.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //该行代码其实只需要运行一次,只是为了获取悬浮条的高度
                headerHeight=ll_header.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                /**
                 * 思路:
                 * 1.判断屏幕中第二个可见的item是否顶到了悬浮条
                 * 1.1.如果顶到了,则将悬浮条向上移动
                 * 1.2.如果没顶到,则将悬浮条固定在原位
                 */
                /**
                 * 与该思路对应的代码:
                 * 1.通过判断第二个可见item的getTop()值与悬浮条的高度作对比,如果top<悬浮条高度,则将悬浮条向上或向下移动(top-悬浮条高度)个位置
                 *
                 */

                int tempFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                View secondView = linearLayoutManager.findViewByPosition( tempFirstVisibleItemPosition + 1);

                int top = secondView.getTop();

                if(top<headerHeight){
                    //注意这里得到是负数,目的是将悬浮条向上移动
                    ll_header.setY(-(headerHeight-top));
                }

                if(currentPosition!=tempFirstVisibleItemPosition){
                    currentPosition=tempFirstVisibleItemPosition;
                    //将刚刚被移出屏幕外的悬浮条恢复原位
                    ll_header.setY(0);
                    //并将悬浮条上的头像和姓名改为第一个可见item对应的数据
                    updateHeader(userList.get(currentPosition));
                }
            }
        });

        updateHeader(userList.get(0));
    }


    private void updateHeader(User user){
        iv_avatar.setImageResource(user.getAvatarResId());
        tv_name.setText(user.getName());
    }



}

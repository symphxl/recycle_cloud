package com.zkhc.recycle_cloud;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zkhc.recycle_cloud.adapter.MyPagerAdapter;
import com.zkhc.recycle_cloud.fragment.ElectronFragment;
import com.zkhc.recycle_cloud.fragment.FabricFragment;
import com.zkhc.recycle_cloud.fragment.GlassFragment;
import com.zkhc.recycle_cloud.fragment.MetalFragment;
import com.zkhc.recycle_cloud.fragment.PaperFragment;
import com.zkhc.recycle_cloud.fragment.PlasticsFragment;
import com.zkhc.recycle_cloud.util.HideButtonUtils;

import java.util.ArrayList;
import java.util.List;

public class RecyclableActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private String[] titles;
    private TitleBar type_recyclab;
    List<Fragment> fragmentList = new ArrayList<>();
    MyPagerAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去除系统顶部任务栏,时间和电量等
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclable);
        HideButtonUtils.hideBottomUIMenu(this);
        initUI();
        initVPager();
    }

    /**
     * 初始化布局和监听
     */
    private void initUI() {
        type_recyclab = findViewById(R.id.type_recyclab);

        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tablayout);

        fragmentList.add(new ElectronFragment());
        fragmentList.add(new FabricFragment());
        fragmentList.add(new GlassFragment());
        fragmentList.add(new MetalFragment());
        fragmentList.add(new PaperFragment());
        fragmentList.add(new PlasticsFragment());

        titles = new String[]{"废纸", "塑料", "玻璃", "金属", "塑料","纺织物"};


        type_recyclab.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                //跳转到首页
                Intent intent = null;
                intent = new Intent(RecyclableActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                //跳转到下一页
                Intent intent = null;
                intent = new Intent(RecyclableActivity.this, NonRecyclableActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化ViewPager并添加监听事件
     */
    private void initVPager() {
        //设置ViewPager切换方向
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        //添加fragment
//        for (int i = 0; i < titles.length; i++) {
//            fragmentList.add(ElectronFragment.newInstance(titles[i]));
//        }

        //设置适配器
        adapter = new MyPagerAdapter(getSupportFragmentManager(), this.getLifecycle(), fragmentList);
        viewPager2.setAdapter(adapter);

        //初始位置为0
        viewPager2.setCurrentItem(0);


        //根据position修改tab的样式和文字等
        new TabLayoutMediator(tabLayout, viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        }).attach();


        /**
         界面滑动监听
         */
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                Toast.makeText(RecyclableActivity.this, position + "被选中", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        /**
         * 设置TabLayout的选中监听
         */
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }


}
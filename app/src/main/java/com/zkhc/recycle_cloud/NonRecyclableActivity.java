package com.zkhc.recycle_cloud;

import static java.lang.Thread.sleep;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zkhc.recycle_cloud.entity.PutRecord;
import com.zkhc.recycle_cloud.mapper.PutRecordMapper;
import com.zkhc.recycle_cloud.util.AlertDialogUtils;
import com.zkhc.recycle_cloud.util.CommonUtils;
import com.zkhc.recycle_cloud.util.HideButtonUtils;

import org.angmarch.views.NiceSpinner;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 不可回收
 */
public class NonRecyclableActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String[] str = {"是", "否"};
    private static final int TAKE_PHOTO = 1;
    private PutRecordMapper putRecordMapper;
    private ArrayAdapter<String> arrayAdapter;
    private NiceSpinner qualified;
    private TextView user_name, address, weight;
    private TitleBar type_non_recyclab;
    private Button bt_takeImg;
    private Uri imageUri;
    private ImageView img_show;
    private Button send;
    private Button back_main;
    private Camera camera;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去除系统顶部任务栏,时间和电量等
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_recyclable);
        HideButtonUtils.hideBottomUIMenu(this);
        init();
        setType();
        dropDownBox();
        putRecordMapper = new PutRecordMapper();

        bt_takeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outPutImg = new File(getExternalCacheDir(), "output_image21.jpg");
                try {
                    if (outPutImg.exists()) {
                        outPutImg.delete();
                    }
                    outPutImg.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(NonRecyclableActivity.this, "com.zkhc.recycle_cloud.fileprovider", outPutImg);
                } else {
                    imageUri = Uri.fromFile(outPutImg);
                }
                //启动相机                           android.media.action.IMAGE_CAPTURE
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });



    }

    public void send(View view) {
        final String userName = user_name.getText().toString().trim();
        final String userAddress = address.getText().toString().trim();
        final Integer weightInfo = Integer.valueOf(weight.getText().toString().trim());
        final int id = qualified.getSelectedIndex();
        final String type = type_non_recyclab.getTitle().toString().trim();
        //封装对象
        PutRecord putRecord = new PutRecord();
        putRecord.setName(userName);
        putRecord.setAddress(userAddress);
        putRecord.setWeight(weightInfo);
        putRecord.setQualified(id);
        putRecord.setType(type);

        //数据提交提示弹窗
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("确定提交吗?")
                .setContentText("提交后无法撤回,谨慎考虑!")
                .setConfirmText("确定")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int i = putRecordMapper.addInfo(putRecord);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (i == 1) {
                                            sDialog
                                                    .setTitleText("已提交!")
                                                    .setConfirmText("确定")
                                                    .setContentText("")
                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            Intent intent = new Intent(NonRecyclableActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    })
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        } else {
                                            sDialog
                                                    .setTitleText("提交失败")
                                                    .setContentText("未知错误!")
                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            sDialog.dismiss();
                                                        }
                                                    }).changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                        }
                                    }
                                });
                            }
                        }
                        ).start();
                    }
                }).show();
    }


    //实例化控件
    private void init() {
        user_name = findViewById(R.id.user_name);
        address = findViewById(R.id.address);
        weight = findViewById(R.id.weight);
        qualified = findViewById(R.id.qualified);
        type_non_recyclab = findViewById(R.id.type_non_recyclab);
        bt_takeImg = findViewById(R.id.bt_takeImg);
        img_show = findViewById(R.id.img_show);
//        back_main = findViewById(R.id.back_main);
//        send = findViewById(R.id.send);
        //获取主线程
        handler = new Handler(getMainLooper());

        type_non_recyclab.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                //跳转到首页
                Intent intent = null;
                intent = new Intent(NonRecyclableActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                send(v);
            }
        });

    }

    private void setType() {
        Intent intent = getIntent();
        String btnName = intent.getStringExtra("typeName");
        type_non_recyclab.setTitle(btnName);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        img_show.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }


    //下拉选框
    public void dropDownBox() {
        NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.qualified);
        List<String> dataset = new LinkedList<>(Arrays.asList("是", "否"));
        niceSpinner.attachDataSource(dataset);

//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, str);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        qualified.setAdapter(arrayAdapter);
//        qualified.setVisibility(View.VISIBLE);
    }


    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.send: //提交数据
//                send(view);
//                break;
//        }
    }
}
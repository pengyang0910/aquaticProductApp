package com.example.huaweiiot_example_funiotxyz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView text_tempValue , text_turbidityValue;
    private Button btn_updateToken , btn_updateValue , btn_feedingInterval , btn_oxygenInterval , btn_tempThreshold;
    private Switch switch_feedingFood , switch_atmosphereLight , switch_oxygenMotor , switch_waterMotor;
    private TextView text_time;
    private EditText edit_feedingIntervalValue , edit_oxygenIntervalValue , edit_tempThresholdValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_ui();

        getTempAndTru();

//        final Thread t = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    HuaweiIOT hwiot = new HuaweiIOT();
//                    String str = "";
//                    str = hwiot.getAtt("tempValue", "shadow");
//                    text_tempValue.setText(str + "°C");
//                    str = "";
//                    str = hwiot.getAtt("turbidityValue", "shadow");
//                    text_turbidityValue.setText(str + "%");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("获取失败：" + e.toString());
//                }
//            }
//        };
//        t.start();

        // Switch
        switch_feedingFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                HuaweiIOT hwiot = new HuaweiIOT();
                                String str = hwiot.getAtt("", "status");
                                str = hwiot.setCom("feedingFood", "ON");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("获取失败：" + e.toString());
                            }
                        }
                    };
                    t.start();
                    Toast.makeText(MainActivity.this, "打开投喂食物开关", Toast.LENGTH_SHORT).show();
                } else {
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                HuaweiIOT hwiot = new HuaweiIOT();
                                String str = hwiot.getAtt("", "status");
                                str = hwiot.setCom("feedingFood", "OFF");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("获取失败：" + e.toString());
                            }
                        }
                    };
                    t.start();
                    Toast.makeText(MainActivity.this, "关闭投喂食物开关", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch_atmosphereLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                HuaweiIOT hwiot = new HuaweiIOT();
                                String str = hwiot.getAtt("", "status");
                                str = hwiot.setCom("atmosphereLight", "ON");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("获取失败：" + e.toString());
                            }
                        }
                    };
                    t.start();
                    Toast.makeText(MainActivity.this, "打开氛围灯", Toast.LENGTH_SHORT).show();
                } else {
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                HuaweiIOT hwiot = new HuaweiIOT();
                                String str = hwiot.getAtt("", "status");
                                str = hwiot.setCom("atmosphereLight", "OFF");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("获取失败：" + e.toString());
                            }
                        }
                    };
                    t.start();
                    Toast.makeText(MainActivity.this, "关闭氛围灯", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch_oxygenMotor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                HuaweiIOT hwiot = new HuaweiIOT();
                                String str = hwiot.getAtt("", "status");
                                str = hwiot.setCom("oxygenMotor", "ON");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("获取失败：" + e.toString());
                            }
                        }
                    };
                    t.start();
                    Toast.makeText(MainActivity.this, "打开充氧电机", Toast.LENGTH_SHORT).show();
                } else {
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                HuaweiIOT hwiot = new HuaweiIOT();
                                String str = hwiot.getAtt("", "status");
                                str = hwiot.setCom("oxygenMotor", "OFF");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("获取失败：" + e.toString());
                            }
                        }
                    };
                    t.start();
                    Toast.makeText(MainActivity.this, "关闭充氧电机", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch_waterMotor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                HuaweiIOT hwiot = new HuaweiIOT();
                                String str = hwiot.getAtt("", "status");
                                str = hwiot.setCom("waterMotor", "ON");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("获取失败：" + e.toString());
                            }
                        }
                    };
                    t.start();
                    Toast.makeText(MainActivity.this, "打开换水电机", Toast.LENGTH_SHORT).show();
                } else {
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                HuaweiIOT hwiot = new HuaweiIOT();
                                String str = hwiot.getAtt("", "status");
                                str = hwiot.setCom("waterMotor", "OFF");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("获取失败：" + e.toString());
                            }
                        }
                    };
                    t.start();
                    Toast.makeText(MainActivity.this, "关闭换水电机", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // display time
        setCurrentTime();

    }

    public void init_ui(){
        btn_updateToken = (Button)findViewById(R.id.btn_updateToken);
        btn_updateValue = (Button)findViewById(R.id.btn_updateValue);
        btn_feedingInterval = (Button)findViewById(R.id.btn_feedingInterval);
        btn_oxygenInterval = (Button)findViewById(R.id.btn_oxygenInterval);
        btn_tempThreshold = (Button)findViewById(R.id.btn_tempThreshold);

        text_tempValue = (TextView)findViewById(R.id.text_tempValue);
        text_turbidityValue = (TextView)findViewById(R.id.text_turbidityValue);

        switch_feedingFood = (Switch) findViewById(R.id.switch_feedingFood);
        switch_atmosphereLight = (Switch) findViewById(R.id.switch_atmosphereLight);
        switch_oxygenMotor = (Switch) findViewById(R.id.switch_oxygenMotor);
        switch_waterMotor = (Switch) findViewById(R.id.switch_waterMotor);

        text_time = findViewById(R.id.text_time);

        edit_feedingIntervalValue = findViewById(R.id.edit_feedingIntervalValue);
        edit_oxygenIntervalValue = findViewById(R.id.edit_oxygenIntervalValue);
        edit_tempThresholdValue = findViewById(R.id.edit_tempThresholdValue);

        btn_updateToken.setOnClickListener(this);
        btn_updateValue.setOnClickListener(this);
        btn_feedingInterval.setOnClickListener(this);
        btn_oxygenInterval.setOnClickListener(this);
        btn_tempThreshold.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_updateToken:
            {
                updateToken();
                Toast.makeText(MainActivity.this, "token 更新成功", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_updateValue:
            {
                getTempAndTru();
                Toast.makeText(MainActivity.this, "数据更新成功", Toast.LENGTH_SHORT).show();

                setCurrentTime();
                break;
            }
            case R.id.btn_feedingInterval:
            {
                String textValue = edit_feedingIntervalValue.getText().toString();
                final Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            HuaweiIOT hwiot = new HuaweiIOT();
                            String str = hwiot.getAtt("", "status");
                            str = hwiot.setCom("feedingInterval", textValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("获取失败：" + e.toString());
                        }
                    }
                };
                t.start();
                String toastStr = "投喂食物间隔设置为：" + textValue + "分钟";
                Toast.makeText(MainActivity.this, toastStr, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_oxygenInterval:
            {
                String textValue = edit_oxygenIntervalValue.getText().toString();
                final Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {

                            HuaweiIOT hwiot = new HuaweiIOT();
                            String str = hwiot.getAtt("", "status");
                            str = hwiot.setCom("oxygenInterval", textValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("获取失败：" + e.toString());
                        }
                    }
                };
                t.start();
                String toastStr = "充氧时间间隔设置为：" + textValue + "分钟";
                Toast.makeText(MainActivity.this, toastStr, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_tempThreshold:
            {
                String textValue = edit_tempThresholdValue.getText().toString();
                final Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {

                            HuaweiIOT hwiot = new HuaweiIOT();
                            String str = hwiot.getAtt("", "status");
                            str = hwiot.setCom("tempThreshold", textValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("获取失败：" + e.toString());
                        }
                    }
                };
                t.start();
                String toastStr = "加热温度上限阈值设置为：" + textValue + "分钟";
                Toast.makeText(MainActivity.this, toastStr, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void setCurrentTime() {
        // 获取当前时间
        Date currentTime = new Date();

        // 设置时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getDefault());
        String formattedTime = dateFormat.format(currentTime);

        // 将当前时间显示在 TextView 中
        text_time.setText("更新时间：" + formattedTime);
    }

    private void getTempAndTru(){
        final Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    HuaweiIOT hwiot = new HuaweiIOT();
                    String str = "";
                    str = hwiot.getAtt("tempValue", "shadow");
                    text_tempValue.setText(str + "°C");
                    str = "";
                    str = hwiot.getAtt("turbidityValue", "shadow");
                    text_turbidityValue.setText(str + "%");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("获取失败：" + e.toString());
                }
            }
        };
        t.start();
    }

    private void updateToken(){
        final Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    HuaweiIOT hwiot = new HuaweiIOT();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("获取失败：" + e.toString());
                }
            }
        };
        t.start();
    }

}
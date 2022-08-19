package com.example.myservice1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtNumA, edtNumB;
    private TextView txtResult;
    private Button btnSum, btnSub;
    private MyBoundService myBoundService;
    private boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumA = findViewById(R.id.edt_num_a);
        edtNumB = findViewById(R.id.edt_num_b);
        txtResult = findViewById(R.id.txt_result);
        btnSub = findViewById(R.id.btn_sub);
        btnSum = findViewById(R.id.btn_sum);

        btnSum.setOnClickListener(view -> {
            sum();
        });

        btnSub.setOnClickListener(view -> {
            sub();
        });
        Log.d(MyBoundService.TAG,"onCreate.....");
    }
    //Service : onCreate -> onBind -> onServiceConnected
    //Khi run app thì nó tự động vào đây
    private ServiceConnection connection = new ServiceConnection() {
        //connect service thành công
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.LocalBinder localBinder = (MyBoundService.LocalBinder) iBinder;
            myBoundService = localBinder.getService();
            isBind = true;
            Log.d(MyBoundService.TAG,"onServiceConnected1");
        }
        //connect service thất bại
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;
            Log.d(MyBoundService.TAG,"onServiceDisConnected2");
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (!isBind){
            Intent intent = new Intent(MainActivity.this, MyBoundService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
            Log.d(MyBoundService.TAG,"onStart.....");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind){
            unbindService(connection);
            Log.d(MyBoundService.TAG,"onDestroy....");
        }
    }

    private void sum(){
        int numA = Integer.parseInt(edtNumA.getText().toString());
        int numB = Integer.parseInt(edtNumB.getText().toString());
        txtResult.setText(""+myBoundService.sum(numA,numB));
    }
    private void sub(){
        int numA = Integer.parseInt(edtNumA.getText().toString());
        int numB = Integer.parseInt(edtNumB.getText().toString());
        txtResult.setText(""+myBoundService.sub(numA,numB));
    }

}
package com.lakshan.iterableintegration;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iterable.iterableapi.IterableApi;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "IterableEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    private void initUi() {
        findViewById(R.id.btn_save).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                saveIterableEmail();
                break;
        }
    }

    private void saveIterableEmail() {
        EditText userEmailEditText = findViewById(R.id.tv_email);
        String userEmail = userEmailEditText.getText().toString().trim();

        if (userEmail.isEmpty()) {
            Log.w(TAG, "saveIterableEmail: Email field is empty");
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG, "Saving email: " + userEmail);
        IterableApi.getInstance().setEmail(userEmail);
        Log.d(TAG, "Email successfully set in Iterable");

        Toast.makeText(this, "Email saved", Toast.LENGTH_LONG).show();
    }
}
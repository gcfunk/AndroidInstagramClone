/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    EditText username;
    EditText password;
    TextView changeSignUpMode;
    Button signUpButton;
    boolean signUpMode;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.changeSignUpMode) {
            if (signUpMode) {
                signUpMode = false;
                signUpButton.setText("Log In");
                changeSignUpMode.setText("Sign Up");
            } else {
                signUpMode = true;
                signUpButton.setText("Sign Up");
                changeSignUpMode.setText("Log In");
            }

        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
            signUpOrLogIn(v);
        }
        return false;
    }

    public void signUpOrLogIn(View view) {
        if (signUpMode) {
            ParseUser user = new ParseUser();
            user.setUsername(String.valueOf(username.getText()));
            user.setPassword(String.valueOf(password.getText()));

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("AppInfo", "sign up success");
                    } else {
                        Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            ParseUser.logInInBackground(String.valueOf(username.getText()), String.valueOf(password.getText()), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Log.i("AppInfo", "sign up success");
                    } else {
                        Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

      username = (EditText) findViewById(R.id.username);
      password = (EditText) findViewById(R.id.password);
      changeSignUpMode = (TextView) findViewById(R.id.changeSignUpMode);
      signUpButton = (Button) findViewById(R.id.signUpButton);
      signUpMode = true;

      changeSignUpMode.setOnClickListener(this);

      password.setOnKeyListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}

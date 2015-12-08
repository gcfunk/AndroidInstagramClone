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
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

      /*ParseObject score = new ParseObject("Score");
      score.put("username", "riley");
      score.put("score", 150);
      score.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {
              if (e == null) {
                  Log.i("SaveInBackground", "Success");
              } else {
                  Log.i("SaveInBackground", "Failed");
                  e.printStackTrace();
              }
          }
      });

      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
      query.getInBackground("V2KPRQYd8c", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {
              if (e == null) {
                  object.put("score", 500);
                  object.saveInBackground();
              } else {
                  Log.i("getInBackground", "Failed");
                  e.printStackTrace();
              }
          }
      });*/


      /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
      query.whereEqualTo("username", "greg");
      query.setLimit(1);
      query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {
              if (e == null) {
                  Log.i("findInBackground", "Retrieved " + objects.size() + " objects");

                  for (ParseObject object : objects) {
                      Log.i("score", String.valueOf(object.get("score")));
                  }
              }
          }
      });*/

      ParseUser user = new ParseUser();
      user.setUsername("greg");
      user.setPassword("password");

      user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
              if (e == null) {
                  Log.i("sign up", "Success");
              } else {
                  Log.i("sign up", "failed");
                  e.printStackTrace();
              }
          }
      });
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

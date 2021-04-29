package edu.pacificu.cs.group7boomshine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity
{
  private BoomshineAndroidView mBoomshineAndroidView;

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);

    mBoomshineAndroidView = new BoomshineAndroidView (this);
    setContentView (mBoomshineAndroidView);
  }
}
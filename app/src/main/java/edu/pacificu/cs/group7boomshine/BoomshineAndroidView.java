package edu.pacificu.cs.group7boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.ColorInt;
import edu.pacificu.cs.group7boomshine.circles.Circle;
import edu.pacificu.cs.group7boomshine.circles.ExpandingCircle;
import edu.pacificu.cs.group7boomshine.circles.MovingCircle;

public class BoomshineAndroidView extends View
{
  private static final String TAG = "TAG";
  private Boomshine mBoomshine;
  private BoomshineTimer mBoomshineTimer;
  private Canvas mCanvas;
  private Context mContext;
  private Display mDisplay;

  public BoomshineAndroidView (Context context, Display display)
  {
    super (context);

    mBoomshine = new Boomshine ();
    mBoomshine.initializeCircles ();
    mBoomshine.createRandomMovingCircles (400, 700);
    mContext = context;
    mDisplay = display;

    //setFocusable (true);
    //setFocusableInTouchMode (true);
  }

  public void drawBoomshine ()
  {
    ArrayList<MovingCircle> aMovingCircles = mBoomshine.getMovingCircles ();
    ArrayList<ExpandingCircle> aExpandingCircles = mBoomshine.getExpandingCircles ();

    for (int i = 0; i < aMovingCircles.size (); ++i)
    {
      drawCircle (aMovingCircles.get (i));
    }
  }

  private void drawCircle (Circle circle)
  {
    Paint paint = new Paint ();
    paint.setColor (Color.BLUE);

    mCanvas.drawCircle (circle.getXCoordinate (), circle.getYCoordinate (), circle.getRadius (), paint);
  }

  @Override
  protected void onDraw (Canvas canvas)
  {
    mCanvas = canvas;

    drawBoomshine ();
  }

  @Override
  protected void onSizeChanged (int newViewWidth, int newViewHeight, int oldViewWidth, int oldViewHeight)
  {
    super.onSizeChanged (newViewWidth, newViewHeight, oldViewWidth, oldViewHeight);
  }

  @Override
  public boolean onTouchEvent (MotionEvent event)
  {
    return true;
  }
}

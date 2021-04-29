package edu.pacificu.cs.group7boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Timer;

import edu.pacificu.cs.group7boomshine.circles.Circle;
import edu.pacificu.cs.group7boomshine.circles.ExpandingCircle;
import edu.pacificu.cs.group7boomshine.circles.MovingCircle;

public class BoomshineAndroidView extends View
{
  private static final String TAG = "TAG";
  private Boomshine mBoomshine;
  private BoomshineTimer mBoomshineTimer;
  private Canvas mCanvas;
  private Display mDisplay;
  private Timer mTimer;
  private boolean mbCirclesInitialized;

  public BoomshineAndroidView (Context context, Display display)
  {
    super (context);

    mBoomshine = new Boomshine ();
    mDisplay = display;
    mTimer = new Timer ();
    mbCirclesInitialized = false;

    setFocusable (true);
    setFocusableInTouchMode (true);
  }

  private void drawCircle (Circle circle)
  {
    Paint paint = new Paint ();
    paint.setColor (circle.getColor ());

    mCanvas.drawCircle (circle.getXCoordinate (), circle.getYCoordinate (), circle.getRadius (), paint);
  }

  public void drawBoomshine ()
  {
    final int TEXT_SIZE = 50;
    final int TEXT_OFFSET_X = getWidth() / 2;
    final int TEXT_OFFSET_Y = 100;
    final int TEXT_COLOR = Color.BLACK;

    Paint foreground = new Paint ();

    ArrayList<MovingCircle> aMovingCircles = mBoomshine.getMovingCircles ();
    ArrayList<ExpandingCircle> aExpandingCircles = mBoomshine.getExpandingCircles ();

    String gameStats = "Hits needed: " + mBoomshine.getHitsNeeded () + " Hits: "
            + mBoomshine.getHits () + " Level: " + mBoomshine.getLevel () + " Score: "
            + mBoomshine.getOverallScore ();

    foreground.setTextSize (TEXT_SIZE);
    foreground.setColor (TEXT_COLOR);
    foreground.setStyle (Paint.Style.FILL);
    foreground.setTextAlign (Paint.Align.CENTER);

    mCanvas.drawText (gameStats, TEXT_OFFSET_X,TEXT_OFFSET_Y, foreground);

    for (int i = 0; i < aMovingCircles.size (); ++i)
    {
      drawCircle (aMovingCircles.get (i));
    }

    for (int j = 0; j < aExpandingCircles.size (); ++j)
    {
      drawCircle (aExpandingCircles.get (j));
    }
  }

  @Override
  protected void onDraw (Canvas canvas)
  {
    super.onDraw (canvas);

    mCanvas = canvas;

    if (!mbCirclesInitialized)
    {
      mBoomshine.initializeCircles ();
      mBoomshine.createRandomMovingCircles (getWidth (), getHeight ());
      mbCirclesInitialized = true;
    }

    mBoomshine.iterateFrame ();
    mBoomshine.processCollisions ();
    mBoomshine.processReflections (getWidth (), getHeight ());

    if (mBoomshine.gameIsDone ())
    {
      if (mBoomshine.getHits () >= mBoomshine.getHitsNeeded ())
      {
        mBoomshine.incrementLevel ();
        mBoomshine.initializeCircles ();
        mBoomshine.createRandomMovingCircles (getWidth (), getHeight ());
      }
      else
      {
        if (mBoomshine.incrementAttempt ())
        {
          mBoomshine.initializeCircles ();
          mBoomshine.createRandomMovingCircles (getWidth (), getHeight ());
        }
        else
        {

        }
      }
    }

    drawBoomshine ();

    this.invalidate ();
  }

  @Override
  protected void onSizeChanged (int newViewWidth, int newViewHeight, int oldViewWidth, int oldViewHeight)
  {
    super.onSizeChanged (newViewWidth, newViewHeight, oldViewWidth, oldViewHeight);
  }

  @Override
  public boolean onTouchEvent (MotionEvent event)
  {
    if (event.getAction () != MotionEvent.ACTION_DOWN)
    {
      return super.onTouchEvent (event);
    }

    if (!mBoomshine.userMadeCircle ())
    {
      mBoomshine.createUserExpandingCircle (event.getX (), event.getY ());
    }

    drawBoomshine ();

    return true;
  }
}

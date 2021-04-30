package edu.pacificu.cs.group7boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;

import edu.pacificu.cs.group7boomshine.circles.Circle;
import edu.pacificu.cs.group7boomshine.circles.ExpandingCircle;
import edu.pacificu.cs.group7boomshine.circles.MovingCircle;

public class BoomshineAndroidView extends View
{
  private Boomshine mBoomshine;
  private BoomshineTimer mBoomshineTimer;
  private Canvas mCanvas;
  private boolean mbFirstDraw;

  public BoomshineAndroidView (Context context)
  {
    super (context);

    mBoomshine = new Boomshine ();
    mBoomshineTimer = new BoomshineTimer ();
    mbFirstDraw = true;

    setFocusable (true);
    setFocusableInTouchMode (true);
  }

  private void drawStats ()
  {
    final int TEXT_SIZE = 50;
    final int TEXT_OFFSET_X = getWidth () / 2;
    final int TEXT_OFFSET_Y = 100;
    final int TEXT_COLOR = Color.BLACK;

    Paint statsPaint = new Paint ();

    String gameStats = "Hits needed: " + mBoomshine.getHitsNeeded () + "  Hits: "
            + mBoomshine.getHits () + "  Level: " + mBoomshine.getLevel () + "  Score: "
            + mBoomshine.getOverallScore ();

    statsPaint.setTextSize (TEXT_SIZE);
    statsPaint.setColor (TEXT_COLOR);
    statsPaint.setStyle (Paint.Style.FILL);
    statsPaint.setTextAlign (Paint.Align.CENTER);

    mCanvas.drawText (gameStats, TEXT_OFFSET_X,TEXT_OFFSET_Y, statsPaint);
  }

  private void drawAttemptsRemaining ()
  {
    final int TEXT_SIZE = 50;
    final int TEXT_OFFSET_X = getWidth () / 2;
    final int TEXT_OFFSET_Y = 200;
    final int TEXT_COLOR = Color.BLACK;

    Paint attemptsRemainingPaint = new Paint ();

    String attemptsRemaining = "Attempts remaining: " + mBoomshine.getAttemptsRemaining ();

    attemptsRemainingPaint.setTextSize (TEXT_SIZE);
    attemptsRemainingPaint.setColor (TEXT_COLOR);
    attemptsRemainingPaint.setStyle (Paint.Style.FILL);
    attemptsRemainingPaint.setTextAlign (Paint.Align.CENTER);

    mCanvas.drawText (attemptsRemaining, TEXT_OFFSET_X,TEXT_OFFSET_Y, attemptsRemainingPaint);
  }

  private void drawTimer ()
  {
    final int TEXT_SIZE = 50;
    final int TEXT_OFFSET_X = getWidth () / 2;
    final int TEXT_OFFSET_Y = 400;
    final int TEXT_COLOR = Color.BLACK;

    Paint timerPaint = new Paint ();

    String timerText = "Seconds remaining: " + mBoomshineTimer.getSecondsRemaining ();

    timerPaint.setTextSize (TEXT_SIZE);
    timerPaint.setColor (TEXT_COLOR);
    timerPaint.setStyle (Paint.Style.FILL);
    timerPaint.setTextAlign (Paint.Align.CENTER);

    mCanvas.drawText (timerText, TEXT_OFFSET_X, TEXT_OFFSET_Y, timerPaint);
  }

  private void drawCircle (Circle circle)
  {
    Paint paint = new Paint ();
    paint.setColor (circle.getColor ());

    mCanvas.drawCircle (circle.getXCoordinate (), circle.getYCoordinate (), circle.getRadius (), paint);
  }

  private void drawBoomshine ()
  {
    ArrayList<MovingCircle> aMovingCircles = mBoomshine.getMovingCircles ();
    ArrayList<ExpandingCircle> aExpandingCircles = mBoomshine.getExpandingCircles ();

    drawStats ();
    drawAttemptsRemaining ();
    drawTimer ();

    for (int i = 0; i < aMovingCircles.size (); ++i)
    {
      drawCircle (aMovingCircles.get (i));
    }

    for (int j = 0; j < aExpandingCircles.size (); ++j)
    {
      drawCircle (aExpandingCircles.get (j));
    }
  }

  private void drawGameOverMessage ()
  {
    final String GAME_OVER_MESSAGE = "Game over";
    final int TEXT_SIZE = 100;
    final int TEXT_OFFSET_X = getWidth () / 2;
    final int TEXT_OFFSET_Y = getHeight () / 2;
    final int TEXT_COLOR = Color.BLACK;

    Paint foreground = new Paint ();
    foreground.setTextSize (TEXT_SIZE);
    foreground.setColor (TEXT_COLOR);
    foreground.setStyle (Paint.Style.FILL);
    foreground.setTextAlign (Paint.Align.CENTER);

    mCanvas.drawText (GAME_OVER_MESSAGE, TEXT_OFFSET_X, TEXT_OFFSET_Y, foreground);
  }

  @Override
  protected void onDraw (Canvas canvas)
  {
    super.onDraw (canvas);

    mCanvas = canvas;

    if (mbFirstDraw)
    {
      mBoomshineTimer.startTimer ();
      mBoomshine.initializeCircles ();
      mBoomshine.createRandomMovingCircles (getWidth (), getHeight ());
      mbFirstDraw = false;
    }

    mBoomshine.iterateFrame ();
    mBoomshine.processCollisions ();
    mBoomshine.processReflections (getWidth (), getHeight ());

    if (mBoomshine.gameIsDone () || (!mBoomshine.userMadeCircle () && mBoomshineTimer.isCountDownComplete ()))
    {
      if (mBoomshine.getHits () >= mBoomshine.getHitsNeeded ())
      {
        mBoomshine.incrementLevel ();
        mBoomshineTimer.stopTimer ();
        mBoomshineTimer.startTimer ();
        mBoomshine.initializeCircles ();
        mBoomshine.createRandomMovingCircles (getWidth (), getHeight ());
      }
      else
      {
        if (mBoomshine.incrementAttempt ())
        {
          mBoomshineTimer.stopTimer ();
          mBoomshineTimer.startTimer ();
          mBoomshine.initializeCircles ();
          mBoomshine.createRandomMovingCircles (getWidth (), getHeight ());
        }
        else
        {
          mBoomshineTimer.stopTimer ();
          drawGameOverMessage ();
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

    return true;
  }
}

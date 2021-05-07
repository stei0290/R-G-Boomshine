package edu.pacificu.cs.group7boomshine;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import edu.pacificu.cs.group7boomshine.circles.Circle;
import edu.pacificu.cs.group7boomshine.circles.ExpandingCircle;
import edu.pacificu.cs.group7boomshine.circles.MovingCircle;

/**
 * BooomshineAndroid View class
 */
public class BoomshineAndroidView extends View
{
  private final Boomshine mBoomshine;
  private final BoomshineTimer mBoomshineTimer;
  private Canvas mCanvas;
  private boolean mbFirstDraw;
  private boolean mbGameActive;
  private int mLevel;
  private final MainActivity mMainActivity;

  /**
   * Default constructor  for BoomshineAndroidView
   * @param context - Context of main activity
   */
  public BoomshineAndroidView (Context context)
  {
    super (context);
    this.mMainActivity = (MainActivity) context;
    mBoomshine = new Boomshine (1);
    mBoomshineTimer = new BoomshineTimer ();
    mbFirstDraw = true;
    mbGameActive = true;
    setFocusable (true);
    setFocusableInTouchMode (true);
  }

  /**
   * DrawStats  - Draws game stats to screen
   */
  private void drawStats ()
  {
    final String SPACE = " ";
    final int TEXT_SIZE = 50;
    final int TEXT_OFFSET_X = getWidth () / 2;
    final int TEXT_OFFSET_Y = 100;
    final int TEXT_COLOR = Color.BLACK;

    Paint statsPaint = new Paint ();

    String gameStats = getResources ().getString (R.string.sHitNeed) +
            SPACE +
            mBoomshine.getHitsNeeded () +
            SPACE +
            getResources ().getString (R.string.sHits) +
            SPACE +
            mBoomshine.getHits () +
            SPACE +
            getResources ().getString (R.string.sLevel) +
            SPACE +
            mBoomshine.getLevel () +
            getResources ().getString (R.string.sNewLine) +
            getResources ().getString (R.string.sScore) +
            SPACE +
            mBoomshine.getOverallScore ();

    statsPaint.setTextSize (TEXT_SIZE);
    statsPaint.setColor (TEXT_COLOR);
    statsPaint.setStyle (Paint.Style.FILL);
    statsPaint.setTextAlign (Paint.Align.CENTER);

    mCanvas.drawText (gameStats, TEXT_OFFSET_X,TEXT_OFFSET_Y, statsPaint);
  }

  /**
   * drawAttemptsRemaining  - Draws the remaining attempts user has to
   *                          pass the level
   */
  private void drawAttemptsRemaining ()
  {
    final String SPACE = " ";
    final int TEXT_SIZE = 50;
    final int TEXT_OFFSET_X = getWidth () / 2;
    final int TEXT_OFFSET_Y = 200;
    final int TEXT_COLOR = Color.BLACK;

    Paint attemptsRemainingPaint = new Paint ();

    String attemptsRemaining =  getResources ().getString (R.string.sAttRemain) +
            SPACE +
            + mBoomshine.getAttemptsRemaining ();

    attemptsRemainingPaint.setTextSize (TEXT_SIZE);
    attemptsRemainingPaint.setColor (TEXT_COLOR);
    attemptsRemainingPaint.setStyle (Paint.Style.FILL);
    attemptsRemainingPaint.setTextAlign (Paint.Align.CENTER);

    mCanvas.drawText (attemptsRemaining, TEXT_OFFSET_X,TEXT_OFFSET_Y, attemptsRemainingPaint);
  }

  /**
   * drawTimer  - Draws timer to screen
   */
  private void drawTimer ()
  {
    final String SPACE = " ";
    final int TEXT_SIZE = 50;
    final int TEXT_OFFSET_X = getWidth () / 2;
    final int TEXT_OFFSET_Y = 400;
    final int TEXT_COLOR = Color.BLACK;

    Paint timerPaint = new Paint ();

    String timerText = getResources ().getString (R.string.sTimer) +
            SPACE +
            mBoomshineTimer.getSecondsRemaining ();

    timerPaint.setTextSize (TEXT_SIZE);
    timerPaint.setColor (TEXT_COLOR);
    timerPaint.setStyle (Paint.Style.FILL);
    timerPaint.setTextAlign (Paint.Align.CENTER);

    mCanvas.drawText (timerText, TEXT_OFFSET_X, TEXT_OFFSET_Y, timerPaint);
  }

  /**
   * drawCircle - Draws a circle to the screen
   * @param circle  - Circle to be drawn
   */
  private void drawCircle (Circle circle)
  {
    Paint paint = new Paint ();
    paint.setColor (circle.getColor ());

    mCanvas.drawCircle (circle.getXCoordinate (),
            circle.getYCoordinate (), circle.getRadius (), paint);
  }

  /**
   * drawBoomshine  - Draws  Boomshine game to screen
   */
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

  /**
   * onDraw - Function to draw game to screen
   * @param canvas  - Canvas to be used when drawing circles
   */
  @Override
  protected void onDraw (Canvas canvas)
  {
    final int MAX_LEVEL = 11;
    super.onDraw (canvas);
    mCanvas = canvas;
    mLevel = mBoomshine.getLevel ();
    if (mbFirstDraw)
    {
      mBoomshineTimer.startTimer (mLevel);
      mBoomshine.initializeCircles ();
      mBoomshine.createRandomMovingCircles (getWidth (), getHeight ());
      mbFirstDraw = false;
    }
    mBoomshine.iterateFrame ();
    mBoomshine.processCollisions ();
    mBoomshine.processReflections (getWidth (), getHeight ());

    if (mLevel == MAX_LEVEL) {
      displayWinner ();
      mBoomshineTimer.stopTimer ();
      mbGameActive = false;
    }

    if (mBoomshine.gameIsDone () || (mBoomshine.userMadeCircle ()
            && mBoomshineTimer.isCountDownComplete ()))
    {
      if (mBoomshine.getHits () >= mBoomshine.getHitsNeeded ())
      {
        mBoomshine.incrementLevel ();
        mBoomshineTimer.stopTimer ();
        mBoomshineTimer.startTimer (mLevel);
        mBoomshine.initializeCircles ();
        mBoomshine.createRandomMovingCircles (getWidth (), getHeight ());
      }
      else
      {
        if (mBoomshine.incrementAttempt ())
        {
          mBoomshineTimer.stopTimer ();
          mBoomshineTimer.startTimer (mLevel);
          mBoomshine.initializeCircles ();
          mBoomshine.createRandomMovingCircles (getWidth (), getHeight ());
        }
        else
        {
          mBoomshineTimer.stopTimer ();
          gameOverAlert ();
          mbGameActive = false;
        }
      }
    }
  if (mbGameActive) {
    drawBoomshine ();
    this.invalidate ();
  }
  }

  /**
   *
   * @param newViewWidth  - New View width
   * @param newViewHeight - New View height
   * @param oldViewWidth  - Old View width
   * @param oldViewHeight - Old View height
   */
  @Override
  protected void onSizeChanged (int newViewWidth, int newViewHeight,
                                int oldViewWidth, int oldViewHeight)
  {
    super.onSizeChanged (newViewWidth, newViewHeight, oldViewWidth, oldViewHeight);
  }

  /**
   * onTouchEvent - Sets user's circle
   * @param event - Event to  be captured
   * @return  true
   */
  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouchEvent (MotionEvent event)
  {
    if (event.getAction () != MotionEvent.ACTION_DOWN)
    {
      return super.onTouchEvent (event);
    }

    if (mBoomshine.userMadeCircle ())
    {
      mBoomshine.createUserExpandingCircle (event.getX (), event.getY ());
    }

    return true;
  }

  /**
   * gameOverAlert  -  Alert displaying game over message
   */
  private void gameOverAlert () {
    final String SPACE = " ";
    AlertDialog.Builder builder = new AlertDialog.Builder
            (this.mMainActivity);
    builder.setTitle
            (getResources ().getString (R.string.sGameOver));
    builder.setMessage
            (getResources ().getString (R.string.sGameOverMsg) +
                    getResources ().getString (R.string.sNewLine) +
                    getResources ().getString (R.string.sYourScore)  +
                    SPACE
                    + mBoomshine.getOverallScore ());
    builder.setPositiveButton
            (getResources ().getString (R.string.sRestart),
                    (dialogInterface, i) -> {
                      restart ();
                      dialogInterface.dismiss ();
                    });
    AlertDialog dialog = builder.create ();
    dialog.setCancelable (false);
    dialog.setCanceledOnTouchOutside (false);
    dialog.show ();
  }

  /**
   * restart  - Restarts the game
   */
  private void restart () {
    this.mMainActivity.restartGame ();
  }

  /**
   * displayWinner  - Displays message if user wins
   */
  private void displayWinner () {
    final String SPACE = " ";
    AlertDialog.Builder builder = new AlertDialog.Builder
            (this.mMainActivity);
    builder.setTitle
            (getResources ().getString (R.string.sGameWin));
    builder.setMessage
            (getResources ().getString (R.string.sGameWinMsg) +
                    getResources ().getString (R.string.sNewLine) +
                    getResources ().getString (R.string.sYourScore) +
                    SPACE
                    + mBoomshine.getOverallScore ());
    builder.setPositiveButton
            (getResources ().getString (R.string.sRestart),
                    (dialogInterface, i) -> {
                      restart ();
                      dialogInterface.dismiss ();
                    });
    AlertDialog dialog = builder.create ();
    dialog.setCancelable (false);
    dialog.setCanceledOnTouchOutside (false);
    dialog.show ();
  }


}

package edu.pacificu.cs.group7boomshine;

/**
 * Shape - Class for Shape
 */
public class Shape {
    private float mXCoordinate;
    private float mYCoordinate;
    private int mColorARGB;

    /**
     * Shape    -    Overridden constructor for Shape
     * @param xCoord    -   X coordinate of shape origin
     * @param yCoord    -   Y coordinate of Shape origin
     * @param color     -   Integer representing color of shape
     */
    public Shape (float xCoord, float yCoord, int color) {
        mXCoordinate = xCoord;
        mYCoordinate = yCoord;
        mColorARGB = color;
    }

    /**
     * Default constructor for Shape class
     */
    public Shape () {}

    /**
     * setXCoordinate   -   Sets x coordinate of Shape
     * @param xCoord    -   X coordinate of Shape
     */
    public void setXCoordinate (float xCoord) {
        mXCoordinate = xCoord;
    }

    /**
     * setYCoord - Sets Y coordinate of Shape
     * @param yCoord - Y coordinate of Shape
     */
    public void setYCoordinate (float yCoord) {
        mYCoordinate = yCoord;
    }

// --Commented out by Roman unused function
//    /**
//     * setColor -   Sets color of Shape
//     * @param color -   Color integer of Shape
//     */
//    public void setColor (int color) {
//        mColorARGB = color;
//    }
// --Commented out by Roman

    public final int getColor () {
        return mColorARGB;
    }

    /**
     * getXCoordinate   -   Gets X coordinate of Shape
     * @return  -   mXCoordinate    -X coordinate of Shape origin
     */
    public final float getXCoordinate () {
        return mXCoordinate;
    }

    public final float getYCoordinate () {
        return mYCoordinate;
    }





}


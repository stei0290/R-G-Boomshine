package edu.pacificu.cs.group7boomshine;

import android.graphics.Color;

public class Shape {
    private float mX;
    private float mY;
    private int mColorARGB;

    public Shape (float xCoord, float yCoord, int color) {
        mX = xCoord;
        mY = yCoord;
        mColorARGB = color;
    }

    public Shape () {}


    public void setXCoordinate (float xCoord) {
        mX = xCoord;
    }

    /**
     * setYCoord - Sets Y coordinate of shape
     * @param yCoord - Y coordinate of shape
     */
    public void setYCoordinate (float yCoord) {
        mY = yCoord;
    }

    public void setColor (int color) {
        mColorARGB = color;
    }

    public final int getColor () {
        return mColorARGB;
    }


    public final float getXCoordinate () {
        return mX;
    }

    public final float getYCoordinate () {
        return mY;
    }





}


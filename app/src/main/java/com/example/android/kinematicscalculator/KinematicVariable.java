package com.example.android.kinematicscalculator;

/**
 * Created by traweeka18 on 5/21/2018.
 */

public class KinematicVariable {
    private double value;
    private boolean getValue;
    private boolean hasValue;
    private boolean hasAltValue;
    private double altValue;

    public KinematicVariable(){
        value=0;
        getValue=false;
        hasValue=false;
        hasAltValue = false;
        altValue = 0;
    }

    public void setValue(double val){
        value = val;
    }

    public void setGetValue(boolean val){
        getValue = val;
    }

    public void setHasValue(boolean val){
        hasValue=val;
    }

    public void setHasAltValue(boolean val){
        hasAltValue=val;
    }

    public void setAltValue(double val){
        altValue = val;
    }

    public double getValue(){
        setHasValue(true);
        return value;
    }

    public boolean getGetValue(){
        return getValue;
    }

    public boolean getHasValue(){
        return hasValue;
    }

    public boolean getHasAltValue(){
        return hasAltValue;
    }

    public double getAltValue(){
        return altValue;
    }
}

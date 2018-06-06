package com.example.android.kinematicscalculator;

public class KinematicVariable2D extends KinematicVariable {
    private double angle;
    private double altAngle;
    private double x;
    private double y;
    private double altX;
    private double altY;

    public KinematicVariable2D(){
        super();
    }

    public void setAngle(double theAngle){
        angle=theAngle*Math.PI/180;
    }

    public double getAngle(){
        return angle;
    }

    public double getAltAngle(){
        return altAngle;
    }

    @Override
    public double getValue(){
        if (CalculatorData.getCalcX()) {
            return x;
        } else {
            return y;
        }
    }

    @Override
    public double getAltValue(){
        if(CalculatorData.getCalcX())
            return altX;
        else
            return altY;
    }

    @Override
    public void setCalculatedValue(double val){
        if(CalculatorData.getCalcX()) {
            x = val;
        }else {
            y = val;
            setHasValue(true);
            calculate();
        }
        CalculatorData.toggleCalcX();
    }

    @Override
    public void setAltValue(double val){
        if(CalculatorData.getCalcX()){
            altX = val;
        }else{
            altY = val;
            altCalculate();
        }
    }

    public void calculate(){
        setValue(Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
        setAngle(Math.atan2(y,x));
    }

    public void altCalculate(){
        setAltValue(Math.sqrt(Math.pow(altX,2)+Math.pow(altY,2)));
        altAngle=Math.atan2(altY,altX);
    }

    public void componentCalculate(){
        x=getValue()*Math.cos(angle);
        y=getValue()*Math.sin(angle);
    }
}
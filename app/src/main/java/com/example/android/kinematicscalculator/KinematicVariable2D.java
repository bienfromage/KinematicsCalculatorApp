package com.example.android.kinematicscalculator;

public class KinematicVariable2D extends KinematicVariable {
    private double angle;
    private boolean hasAngle;
    private double altAngle;
    private boolean hasAltAngle;

    public KinematicVariable2D(){
        super();
        hasAngle = false;
        hasAltAngle=false;
    }

    public void setAngle(double theAngle){
        angle=theAngle*Math.PI/180;
    }

    @Override
    public double getValue(){
        if(CalculatorData.getCalcX()){
            return super.getValue()*Math.cos(angle);
        }else{
            return super.getValue()*Math.sin(angle);
        }
    }
}

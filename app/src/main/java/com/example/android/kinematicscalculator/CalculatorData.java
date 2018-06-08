package com.example.android.kinematicscalculator;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class CalculatorData {
    public final static int ONE_DIMENSIONAL = 0;
    public final static int TWO_DIMENSIONAL_VECTOR = 1;
    private static int entryType;
    private static boolean calcX = true;

    private static HashMap<String, KinematicVariable> hmap = new HashMap<>(5);

    //set up physics variables
    public static void initMap(int type){
        if(type == ONE_DIMENSIONAL) {
            hmap.put("v", new KinematicVariable());
            hmap.put("vo", new KinematicVariable());
            hmap.put("a", new KinematicVariable());
            hmap.put("t", new KinematicVariable());
            hmap.put("s", new KinematicVariable());
        }else{
            hmap.put("v", new KinematicVariable2D());
            hmap.put("vo", new KinematicVariable2D());
            hmap.put("a", new KinematicVariable2D());
            hmap.put("t", new KinematicVariable2D());
            hmap.put("s", new KinematicVariable2D());
        }
    }

    public static KinematicVariable getKinematicVariable(String kinematicVariableId){
        return hmap.get(kinematicVariableId);
    }

    public static String getNextKey(){
        for(Map.Entry<String,KinematicVariable> entry: hmap.entrySet()){
            if(entry.getValue().getGetValue())
                return entry.getKey();
        }
        return null;
    }

    public static void setEntryType(Context context,String choice){
       String[] equationArr = context.getResources().getStringArray(R.array.calculation_type_array);
       if(choice.equals(equationArr[0])){
           entryType = ONE_DIMENSIONAL;
       }else if(choice.equals(equationArr[1])){
           entryType = TWO_DIMENSIONAL_VECTOR;
       }
       initMap(entryType);
    }

    public static int chooseEquation(){
        int[] counters = new int[4];
        for(Map.Entry<String,KinematicVariable> entry: hmap.entrySet()){
            if(entry.getValue().getHasValue()) {
                switch(entry.getKey()){
                    case "vo":
                        counters[0]++;
                        counters[1]++;
                        counters[2]++;
                        counters[3]++;
                        break;
                    case "v":
                        counters[0]++;
                        counters[2]++;
                        counters[3]++;
                        break;
                    case "a":
                        counters[0]++;
                        counters[1]++;
                        counters[2]++;
                        break;
                    case "t":
                        counters[0]++;
                        counters[1]++;
                        counters[3]++;
                        break;
                    case "s":
                        counters[1]++;
                        counters[2]++;
                        counters[3]++;
                        break;
                    default:
                        return -1;
                }
            }
        }

        for(int i = 0; i<counters.length;i++){
            if(counters[i]==3)
                return i;
        }
        return -1;
    }

    public static void solve(int equationId){
        switch(equationId){
            case 0:
                if(!getKinematicVariable("v").getHasValue()){
                    getKinematicVariable("v").setCalculatedValue(getKinematicVariable("vo").getValue()+getKinematicVariable("a").getValue()*getKinematicVariable("t").getValue());
                }else if(!getKinematicVariable("vo").getHasValue()){
                    getKinematicVariable("vo").setCalculatedValue(getKinematicVariable("v").getValue()-getKinematicVariable("a").getValue()*getKinematicVariable("t").getValue());
                }else if(!getKinematicVariable("a").getHasValue()){
                    getKinematicVariable("a").setCalculatedValue((getKinematicVariable("v").getValue()-getKinematicVariable("vo").getValue())/getKinematicVariable("t").getValue());
                }else{
                    getKinematicVariable("t").setCalculatedValue((getKinematicVariable("v").getValue()-getKinematicVariable("vo").getValue())/getKinematicVariable("a").getValue());
                    if(getKinematicVariable("v").getHasAltValue())
                        getKinematicVariable("t").setAltValue((getKinematicVariable("v").getAltValue()-getKinematicVariable("vo").getValue())/getKinematicVariable("a").getValue());
                    else if(getKinematicVariable("vo").getHasAltValue())
                        getKinematicVariable("t").setCalculatedValue((getKinematicVariable("v").getValue()-getKinematicVariable("vo").getAltValue())/getKinematicVariable("a").getValue());
                }
                break;
            case 1:
                if(!getKinematicVariable("s").getHasValue()){
                    getKinematicVariable("s").setCalculatedValue(getKinematicVariable("vo").getValue()*getKinematicVariable("t").getValue()+0.5*getKinematicVariable("a").getValue()*Math.pow(getKinematicVariable("t").getValue(),2));
                }else if(!getKinematicVariable("vo").getHasValue()){
                    getKinematicVariable("vo").setCalculatedValue((getKinematicVariable("s").getValue()-0.5*getKinematicVariable("a").getValue()*Math.pow(getKinematicVariable("a").getValue(),2))/getKinematicVariable("t").getValue());
                }else if(!getKinematicVariable("t").getHasValue()){
                    getKinematicVariable("v").setCalculatedValue(Math.sqrt(Math.pow(getKinematicVariable("vo").getValue(),2)+2*getKinematicVariable("a").getValue()*getKinematicVariable("s").getValue()));
                    getKinematicVariable("v").setAltValue(-Math.sqrt(Math.pow(getKinematicVariable("vo").getValue(),2)+2*getKinematicVariable("a").getValue()*getKinematicVariable("s").getValue()));
                }else{
                    getKinematicVariable("a").setCalculatedValue((getKinematicVariable("s").getValue()-getKinematicVariable("vo").getValue()*getKinematicVariable("t").getValue())/(0.5*Math.pow(getKinematicVariable("t").getValue(),2)));
                }
                break;
            case 2:
                //case v does not have value will never occur this far into the program
                if(!getKinematicVariable("vo").getHasValue()){
                    getKinematicVariable("vo").setCalculatedValue(Math.sqrt(Math.pow(getKinematicVariable("v").getValue(),2)-2*getKinematicVariable("a").getValue()*getKinematicVariable("s").getValue()));
                    getKinematicVariable("vo").setAltValue(-Math.sqrt(Math.pow(getKinematicVariable("v").getValue(),2)-2*getKinematicVariable("a").getValue()*getKinematicVariable("s").getValue()));
                }else if(!getKinematicVariable("a").getHasValue()){
                    getKinematicVariable("a").setCalculatedValue((Math.pow(getKinematicVariable("v").getValue(),2)-Math.pow(getKinematicVariable("vo").getValue(),2))/(2*getKinematicVariable("s").getValue()));
                }
                //case s does not have value will never occur this far into the program
                break;
            case 3://s=(v+vo)/2*t
                //case s does not have value will never occur this far in the program
                //case v does not have value will never occur this far in the program
                //case t does not have value will never occur this far in the program
                if(!getKinematicVariable("vo").getHasValue()){
                    getKinematicVariable("vo").setCalculatedValue(getKinematicVariable("s").getValue()*2/getKinematicVariable("t").getValue()-getKinematicVariable("v").getValue());
                }
                break;
        }
    }

    public static String getOutput(){
        String total = "";

        if(entryType==ONE_DIMENSIONAL) {
            for (Map.Entry<String, KinematicVariable> entry : hmap.entrySet()) {
                total += entry.getKey() + ": " + entry.getValue().getValue();
                if (entry.getValue().getHasAltValue())
                    total += " or " + entry.getValue().getAltValue();
                total += "\n";
            }
        }else{
            for (Map.Entry<String, KinematicVariable> entry : hmap.entrySet()) {
                total += entry.getKey() + ": " + ((KinematicVariable2D)entry.getValue()).getFinalValue()+" at " + (((KinematicVariable2D) entry.getValue()).getAngle()*180/Math.PI)+" degrees";
                if (entry.getValue().getHasAltValue())
                    total += " or " + ((KinematicVariable2D)entry.getValue()).getFinalAltValue()+" at " + (((KinematicVariable2D) entry.getValue()).getAltAngle()*180/Math.PI)+" degrees";
                total += "\n";
            }
        }

        return total;
    }

    public static boolean getCalcX(){
        return calcX;
    }

    public static int getEntryType(){
        return entryType;
    }

    public static int sum(){
        int total = 0;
        for(Map.Entry<String,KinematicVariable> entry: hmap.entrySet()){
            if(entry.getValue().getGetValue()) {
                total++;
            }
        }
        return total;
    }

    public static void toggleCalcX(){
        calcX = !calcX;
    }
}
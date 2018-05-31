package com.example.android.kinematicscalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by traweeka18 on 5/21/2018.
 */

public class CalculatorData {
    private static HashMap<String, KinematicVariable> hmap = new HashMap<>(5);

    //set up physics variables
    public static void initMap(){
        hmap.put("v",new KinematicVariable());
        hmap.put("vo",new KinematicVariable());
        hmap.put("a",new KinematicVariable());
        hmap.put("t",new KinematicVariable());
        hmap.put("s",new KinematicVariable());
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
                    getKinematicVariable("v").setValue(getKinematicVariable("vo").getValue()+getKinematicVariable("a").getValue()*getKinematicVariable("t").getValue());
                }else if(!getKinematicVariable("vo").getHasValue()){
                    getKinematicVariable("vo").setValue(getKinematicVariable("v").getValue()-getKinematicVariable("a").getValue()*getKinematicVariable("t").getValue());
                }else if(!getKinematicVariable("a").getHasValue()){
                    getKinematicVariable("a").setValue((getKinematicVariable("v").getValue()-getKinematicVariable("vo").getValue())/getKinematicVariable("t").getValue());
                }else{
                    getKinematicVariable("t").setValue((getKinematicVariable("v").getValue()-getKinematicVariable("vo").getValue())/getKinematicVariable("a").getValue());
                    if(getKinematicVariable("v").getHasAltValue())
                        getKinematicVariable("t").setAltValue((getKinematicVariable("v").getAltValue()-getKinematicVariable("vo").getValue())/getKinematicVariable("a").getValue());
                }
                break;
            case 1:
                if(!getKinematicVariable("s").getHasValue()){
                    getKinematicVariable("s").setValue(getKinematicVariable("vo").getValue()*getKinematicVariable("t").getValue()+0.5*getKinematicVariable("a").getValue()*Math.pow(getKinematicVariable("t").getValue(),2));
                }else if(!getKinematicVariable("vo").getHasValue()){
                    getKinematicVariable("vo").setValue((getKinematicVariable("s").getValue()-0.5*getKinematicVariable("a").getValue()*Math.pow(getKinematicVariable("a").getValue(),2))/getKinematicVariable("t").getValue());
                }else if(!getKinematicVariable("t").getHasValue()){
                    getKinematicVariable("v").setValue(Math.sqrt(Math.pow(getKinematicVariable("vo").getValue(),2)+2*getKinematicVariable("a").getValue()*getKinematicVariable("s").getValue()));
                    getKinematicVariable("v").setAltValue(-Math.sqrt(Math.pow(getKinematicVariable("vo").getValue(),2)+2*getKinematicVariable("a").getValue()*getKinematicVariable("s").getValue()));
                }else{
                    getKinematicVariable("a").setValue((getKinematicVariable("s").getValue()-getKinematicVariable("vo").getValue()*getKinematicVariable("t").getValue())/(0.5*Math.pow(getKinematicVariable("t").getValue(),2)));
                }
                break;
            case 2:
                //case v does not have value will never occur this far into the program
                if(!getKinematicVariable("vo").getHasValue()){
                    getKinematicVariable("vo").setValue(Math.sqrt(Math.pow(getKinematicVariable("v").getValue(),2)-2*getKinematicVariable("a").getValue()*getKinematicVariable("s").getValue()));
                }else if(!getKinematicVariable("a").getHasValue()){
                    getKinematicVariable("a").setValue((Math.pow(getKinematicVariable("v").getValue(),2)-Math.pow(getKinematicVariable("vo").getValue(),2))/(2*getKinematicVariable("s").getValue()));
                }
                //case s does not have value will never occur this far into the program
                break;
            case 3://s=(v+vo)/2*t
                //case s does not have value will never occur this far in the program
                //case v does not have value will never occur this far in the program
                //case t does not have value will never occur this far in the program
                if(!getKinematicVariable("v").getHasValue()){
                    getKinematicVariable("v").setValue(getKinematicVariable("s").getValue()*2/getKinematicVariable("t").getValue()-getKinematicVariable("vo").getValue());
                }
                break;
        }
    }

    public static String getOutput(){
        String total = "";

        for(Map.Entry<String,KinematicVariable> entry: hmap.entrySet()){
            total+=entry.getKey()+": "+entry.getValue().getValue();
            if(entry.getValue().getHasAltValue())
                total+=" or "+entry.getValue().getAltValue();
            total+="\n";
        }

        return total;
    }
}

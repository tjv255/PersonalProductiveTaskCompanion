package com.example.myapplication;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Model  implements Serializable {

    private int currency;

    private int fitnessLevel;
    private float fitnessProgressPercentage;
    private int fitnessProgress = 0;
    private int fitnessGoal;

    private int informativityLevel;
    private float informativityProgressPercentage;
    private int informativityProgress = 0;
    private int informativityGoal;

    private int productivityLevel;
    private float productivityProgressPercentage;
    private int productivityProgress = 0;
    private int productivityGoal;

    private int restLevel;
    private float restProgressPercentage;
    private int restProgress = 0;
    private int restGoal;

    private LinkedList<Map<String,String>> storeItems;
    public Map<String, String> equippedItems;

    private ArrayList<ModelListener> subscribers;

    public Model() {
        currency = 0;

        fitnessGoal = 4;//3600 * 1; // 1 hour / day
        informativityGoal = 3600 * 4; // 4 hours  / day
        productivityGoal = 3600 * 6; // 10 hours / day
        restGoal = 3600 * 8; // 8 hours / day

        /*ToDo: Convert the initialization to take values form some DB*/
        fitnessProgress = 0;
        fitnessLevel = 1 + fitnessProgress/3600/20; // level for every 20 hours of progress
        fitnessProgressPercentage = (float)fitnessProgress / fitnessGoal * 100;

        informativityProgress = 0;
        informativityLevel = 1 + informativityProgress/3600/20;
        informativityProgressPercentage = (float)informativityProgress / informativityGoal * 100;

        productivityProgress = 0;
        productivityLevel = 1 + productivityProgress/3600/20;
        productivityProgressPercentage = (float)productivityProgress / productivityGoal * 100;

        restProgress = 0;
        restLevel = 1 + restProgress/3600/80;
        restProgressPercentage = (float)restProgress / restGoal * 100;

        Map<String, String> headGear1 = new HashMap<>();
        headGear1.put("type", "Headgear");
        headGear1.put("name", "Hat");
        headGear1.put("note", "It's a nice hat!");
        headGear1.put("price", "500");
        headGear1.put("image","@drawable/hat");

        Map<String, String> glasses1 = new HashMap<>();
        glasses1.put("type", "Glasses");
        glasses1.put("name", "Sunglasses");
        glasses1.put("note", "It's a nice pair of sunglasses!");
        glasses1.put("price", "350");
        glasses1.put("image","@drawable/sunglasses");

        Map<String, String> upperBody1 = new HashMap<>();
        upperBody1.put("type", "Upper Body");
        upperBody1.put("name", "Shirt");
        upperBody1.put("note", "It's a nice shirt!");
        upperBody1.put("price", "750");
        upperBody1.put("image","@drawable/shirt");

        Map<String, String> legWare1 = new HashMap<>();
        legWare1.put("type", "Legwear");
        legWare1.put("name", "Jeans");
        legWare1.put("note", "It's a nice pair of jeans!");
        legWare1.put("price", "750");
        legWare1.put("image","@drawable/pants");

        Map<String, String> footWare1 = new HashMap<>();
        footWare1.put("type", "Footwear");
        footWare1.put("name", "Shoes");
        footWare1.put("note", "It's a nice pair of shoes!");
        footWare1.put("price", "1000");
        footWare1.put("image","@drawable/shoe");

        storeItems = new LinkedList<>();
        storeItems.add(headGear1);
        storeItems.add(upperBody1);
        storeItems.add(glasses1);
        storeItems.add(legWare1);
        storeItems.add(footWare1);

        equippedItems = new HashMap<>();
    }

    public float[] addProgress(String task, int secondsToAdd) {
        switch (task) {
            case "Exercise":
                fitnessProgress += secondsToAdd;
                if (fitnessProgress/3600 >= 20) {
                    fitnessLevel = 1 + fitnessProgress/3600/20;
                }
                fitnessProgressPercentage = (float)fitnessProgress / fitnessGoal * 100;
                return new float[]{fitnessGoal, fitnessProgressPercentage};
            case "Study":
                informativityProgress += secondsToAdd;
                informativityProgressPercentage = (float)informativityProgress / informativityGoal * 100;
                return new float[]{informativityGoal, informativityProgressPercentage};
            case "Productive":
                productivityProgress += secondsToAdd;
                productivityProgressPercentage = (float)productivityProgress / productivityGoal * 100;
                return new float[]{productivityGoal, productivityProgressPercentage};
            case "Rest":
                restProgress += secondsToAdd;
                restProgressPercentage = (float)restProgress / restGoal * 100;
                return new float[]{restGoal, restProgressPercentage};
        }
        return new float[]{-1f, -1f};
    }

    public int getFitnessLevel() {
        return fitnessLevel;
    }

    public int getFitnessProgress() { return fitnessProgress; }

    public float getFitnessProgressPercentage() {
        return fitnessProgressPercentage;
    }

    public int getInformativityLevel() {
        return informativityLevel;
    }

    public int getInformativityProgress() {
        return informativityProgress;
    }

    public float getInformativityProgressPercentage() {
        return informativityProgressPercentage;
    }

    public int getProductivityLevel() {
        return productivityLevel;
    }

    public int getProductivityProgress() {
        return productivityProgress;
    }

    public float getProductivityProgressPercentage() {
        return productivityProgressPercentage;
    }

    public int getRestLevel() {
        return restLevel;
    }

    public int getRestProgress() {
        return restProgress;
    }

    public float getRestProgressPercentage() {
        return restProgressPercentage;
    }

    public int getFitnessGoal() {
        return fitnessGoal;
    }

    public int getInformativityGoal() {
        return informativityGoal;
    }

    public int getProductivityGoal() {
        return productivityGoal;
    }

    public int getRestGoal() {
        return restGoal;
    }

    public LinkedList<Map<String, String>> getStoreItems() {
        return storeItems;
    }

    public void addSubscriber(ModelListener subscriber) { subscribers.add(subscriber); }

    public int getCurrency() {
        return currency;
    }
    public int addCurrency(int amount) {
        if (currency + amount < 0)
            return -1;
        currency += amount;
        return 1;
    }

    public void setFitnessGoal(int fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public void setInformativityGoal(int informativityGoal) {
        this.informativityGoal = informativityGoal;
    }

    public void setProductivityGoal(int productivityGoal) {
        this.productivityGoal = productivityGoal;
    }

    public void setRestGoal(int restGoal) {
        this.restGoal = restGoal;
    }
}

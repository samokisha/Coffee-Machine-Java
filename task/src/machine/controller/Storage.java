package machine.controller;

import machine.model.Recipe;

import java.util.Map;

public interface Storage {

    Storage addWater(int water);

    Storage addMilk(int milk);

    Storage addBeans(int beans);

    Storage addCups(int cups);

    Storage addMoney(int money);

    Storage addRecipe(String key, Recipe recipe);

    Storage takeWater(int water);

    Storage takeMilk(int milk);

    Storage takeBeans(int beans);

    Storage takeCups(int cups);

    Storage takeMoney(int money);

    int takeAllMoney();

    int getWater();

    int getMilk();

    int getBeans();

    int getCups();

    int getMoney();

    Recipe getRecipe(String key);

    Map<String, Recipe> getAllRecipes();

}

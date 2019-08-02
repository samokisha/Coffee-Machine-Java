package machine.app;

import machine.controller.Storage;
import machine.model.Recipe;

import java.util.HashMap;
import java.util.Map;

public class MemoryStorage implements Storage {

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private Map<String, Recipe> recipes;

    public MemoryStorage() {
        recipes = new HashMap<>();
    }

    @Override
    public Storage addWater(int water) {
        this.water += water;
        return this;
    }

    @Override
    public Storage addMilk(int milk) {
        this.milk += milk;
        return this;
    }

    @Override
    public Storage addBeans(int beans) {
        this.beans += beans;
        return this;
    }

    @Override
    public Storage addCups(int cups) {
        this.cups += cups;
        return this;
    }

    @Override
    public Storage addMoney(int money) {
        this.money += money;
        return this;
    }

    @Override
    public Storage addRecipe(String key, Recipe recipe) {
        recipes.put(key, recipe);
        return this;
    }

    @Override
    public Storage takeWater(int water) {
        this.water -= water;
        return this;
    }

    @Override
    public Storage takeMilk(int milk) {
        this.milk -= milk;
        return this;
    }

    @Override
    public Storage takeBeans(int beans) {
        this.beans -= beans;
        return this;
    }

    @Override
    public Storage takeCups(int cups) {
        this.cups -= cups;
        return this;
    }

    @Override
    public Storage takeMoney(int money) {
        this.money -= money;
        return this;
    }

    @Override
    public int takeAllMoney() {
        int money = this.money;
        this.money = 0;
        return money;
    }

    @Override
    public int getWater() {
        return water;
    }

    @Override
    public int getMilk() {
        return milk;
    }

    @Override
    public int getBeans() {
        return beans;
    }

    @Override
    public int getCups() {
        return cups;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public Recipe getRecipe(String key) {
        return recipes.get(key);
    }

    @Override
    public Map<String, Recipe> getAllRecipes() {
        return new HashMap<>(recipes);
    }
}

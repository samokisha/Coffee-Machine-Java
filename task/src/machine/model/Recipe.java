package machine.model;

public class Recipe {

    private final String title;
    private final int water;
    private final int milk;
    private final int beans;
    private final int cups;
    private final int price;

    public Recipe(String title, int water, int milk, int beans, int cups, int price) {
        this.title = title;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getCups() {
        return cups;
    }

    public int getPrice() {
        return price;
    }
}

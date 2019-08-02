package machine.controller;

import machine.model.Recipe;
import machine.model.Resource;
import machine.model.State;
import machine.view.View;

public class Machine {

    private final View view;
    private final Storage storage;
    private State state;

    public Machine(View view, Storage storage) {
        this.view = view;
        this.storage = storage;
        this.state = State.MENU;
        view.setMachine(this);
    }

    public void start() {
        view.start();
    }

    public void onMenu() {
        state = State.MENU;
    }

    public void onBuy() {
        state = State.BUY;
    }

    public void onFill() {
        state = State.FILL;
    }

    public void onTake() {
        state = State.TAKE;
    }

    public void onRemaining() {
        state = State.REMAINING;
    }

    public Storage getStorage() {
        return storage;
    }

    public State getState() {
        return state;
    }

    public void onBuy(Recipe recipe) {
        storage.takeWater(recipe.getWater())
                .takeMilk(recipe.getMilk())
                .takeBeans(recipe.getBeans())
                .takeCups(recipe.getCups())
                .addMoney(recipe.getPrice());
    }

    public void onFill(Resource resource, int amount) {
        switch (resource) {
            case WATER:
                storage.addWater(amount);
                break;
            case MILK:
                storage.addMilk(amount);
                break;
            case BEAN:
                storage.addBeans(amount);
                break;
            case CUP:
                storage.addCups(amount);
                break;
            default:
                break;
        }
    }

    // todo: implement at another file
    public boolean hasResourcesFor(Recipe recipe) {
        return storage.getWater() >= recipe.getWater()
                && storage.getMilk() >= recipe.getMilk()
                && storage.getBeans() >= recipe.getBeans()
                && storage.getCups() >= recipe.getCups();
    }
}

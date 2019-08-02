package machine.app;

import machine.controller.Machine;
import machine.controller.Storage;
import machine.model.Recipe;
import machine.view.View;

public class CoffeeMachine {

    public static void main(String[] args) {
        View view = new TerminalView();
        Storage storage = initStorage();
        Machine machine = new Machine(view, storage);
        machine.start();
    }

    private static Storage initStorage() {
        return new MemoryStorage()
                .addRecipe("1", new Recipe("espresso", 250, 0, 16, 1, 4))
                .addRecipe("2", new Recipe("latte", 350, 75, 20, 1, 7))
                .addRecipe("3", new Recipe("cappuccino", 200, 100, 12, 1, 6))
                .addMoney(550)
                .addWater(400)
                .addMilk(540)
                .addBeans(120)
                .addCups(9);
    }
}

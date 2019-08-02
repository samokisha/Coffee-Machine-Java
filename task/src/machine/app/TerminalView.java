package machine.app;

import machine.controller.Machine;
import machine.controller.Storage;
import machine.model.Recipe;
import machine.model.Resource;
import machine.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TerminalView implements View {

    private static final String SELECT_BUY_ITEM = "buy";
    private static final String SELECT_FILL_ITEM = "fill";
    private static final String SELECT_TAKE_ITEM = "take";
    private static final String SELECT_REMAINING_ITEM = "remaining";
    private static final String SELECT_BACK_ITEM = "back";
    private static final String SELECT_EXIT_ITEM = "exit";

    private static final String SELECT_ACTION_MSG = "Write action (buy, fill, take, remaining, exit):";
    private static final String SELECT_BUY_ACTION_MSG_PATTERN = "What do you want to buy? %s, back - to main menu:";
    private static final String HAVE_ENOUGH_RESOURCES_MSG = "I have enough resources, making you a coffee!";
    private static final String FILL_WATER_MSG = "Write how many ml of water do you want to add:";
    private static final String FILL_MILK_MSG = "Write how many ml of milk do you want to add:";
    private static final String FILL_BEANS_MSG = "Write how many grams of coffee beans do you want to add:";
    private static final String FILL_CUPS_MSG = "Write how many disposable cups of coffee do you want to add:";
    private static final String TAKE_MONEY_MSG = "I gave you $";
    private static final String REMAINING_MSG_PATTERN = "The coffee machine has:\n%d of water\n%d of milk\n%d of coffee beans\n%d of disposable cups\n$%d of money";

    private final Scanner sc;
    private Machine machine;
    private boolean enabled;

    public TerminalView() {
        sc = new Scanner(System.in);
    }

    @Override
    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @Override
    public void start() {
        enabled = true;

        while (enabled) {
            switch (machine.getState()) {
                case MENU:
                    onMenu();
                    break;
                case BUY:
                    onBuy();
                    break;
                case FILL:
                    onFill();
                    break;
                case TAKE:
                    onTake();
                    break;
                case REMAINING:
                    onRemaining();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void exit() {
        enabled = false;
    }

    private void onMenu() {
        String selectedItem = showSelect(SELECT_ACTION_MSG);
        switch (selectedItem.trim().toLowerCase()) {
            case SELECT_BUY_ITEM:
                machine.onBuy();
                break;
            case SELECT_FILL_ITEM:
                machine.onFill();
                break;
            case SELECT_TAKE_ITEM:
                machine.onTake();
                break;
            case SELECT_REMAINING_ITEM:
                machine.onRemaining();
                break;
            case SELECT_EXIT_ITEM:
                exit();
                break;
            default:
                break;
        }
    }

    private void onBuy() {
        Storage storage = machine.getStorage();
        String buyingSelectMsg = getBuyingSelectMessage(storage.getAllRecipes());
        String selectedItem = showSelect(buyingSelectMsg);

        if (selectedItem.equalsIgnoreCase(SELECT_BACK_ITEM)) {
            machine.onMenu();
            return;
        }

        Recipe recipe = storage.getRecipe(selectedItem);

        if (!machine.hasResourcesFor(recipe)) {
            println(HAVE_ENOUGH_RESOURCES_MSG);
            machine.onMenu();
            return;
        }

        machine.onBuy(recipe);
        machine.onMenu();
    }

    private void onFill() {
        // todo: improve
        Map<Resource, String> fillLines = getFillingSelectMessage();
        machine.onFill(Resource.WATER, Integer.parseInt(fillLines.get(Resource.WATER)));
        machine.onFill(Resource.MILK, Integer.parseInt(fillLines.get(Resource.MILK)));
        machine.onFill(Resource.BEAN, Integer.parseInt(fillLines.get(Resource.BEAN)));
        machine.onFill(Resource.CUP, Integer.parseInt(fillLines.get(Resource.CUP)));
        machine.onMenu();
    }

    private void onTake() {
        println(TAKE_MONEY_MSG + machine.getStorage().takeAllMoney());
        machine.onMenu();
    }

    private void onRemaining() {
        Storage storage = machine.getStorage();
        println(String.format(REMAINING_MSG_PATTERN,
                storage.getWater(),
                storage.getMilk(),
                storage.getBeans(),
                storage.getCups(),
                storage.getMoney()));

        machine.onMenu();
    }

    private void println(String msg) {
        System.out.println(msg + "\n");
    }

    private String showSelect(String msg) {
        System.out.print(msg + " ");
        String line = sc.nextLine();
        System.out.println();
        return line;
    }

    private String getBuyingSelectMessage(Map<String, Recipe> recipes) {
        String items = recipes
                .entrySet()
                .stream()
                .map(e -> e.getKey() + " - " + e.getValue().getTitle())
                .collect(Collectors.joining(", "));

        return String.format(SELECT_BUY_ACTION_MSG_PATTERN, items);
    }

    private Map<Resource, String> getFillingSelectMessage() {
        Map<Resource, String> resources = new HashMap<>(4);

        resources.put(Resource.WATER, showSelect(FILL_WATER_MSG));
        resources.put(Resource.MILK, showSelect(FILL_MILK_MSG));
        resources.put(Resource.BEAN, showSelect(FILL_BEANS_MSG));
        resources.put(Resource.CUP, showSelect(FILL_CUPS_MSG));

        return resources;
    }
}

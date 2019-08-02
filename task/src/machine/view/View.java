package machine.view;

import machine.controller.Machine;

public interface View {

    void setMachine(Machine machine);

    void start();

    void exit();
}

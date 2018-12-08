package ua.com.training.controller.commands;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }

    },
    DEFAULT {
        {
            this.command = new DefaultCommand();
        }
    }

    ;
    Command command;
    public Command getCommand() {
        return command;
    }
}

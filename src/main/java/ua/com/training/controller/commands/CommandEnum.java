package ua.com.training.controller.commands;

public enum CommandEnum {
    DEFAULT {
        {
            this.command = new DefaultCommand();
        }
    },
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
    CATALOG {
        {
            this.command = new CatalogCommand();
        }
    },
    CONFERENCE {
        {
            this.command = new ConferenceCommand();
        }
    };
    Command command;

    public Command getCommand() {
        return command;
    }
}

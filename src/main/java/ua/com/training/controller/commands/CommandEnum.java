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

    },
    SUBSCRIBE {
        {
            this.command = new SubscribeCommand();
        }

    },
    UNSUBSCRIBE {
        {
            this.command = new UnsubscribeCommand();
        }

    },
    CHANGELANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }

    },
    CREATECONFERENCE {
        {
            this.command = new CreateConferenceCommand();
        }

    },
    DELETECONFERENCE {
        {
            this.command = new DeleteConferenceCommand();
        }

    },
    CATALOGOFSPEAKERS {
        {
            this.command = new CatalogOfSpeakersCommand();
        }
    },
    EDITCONFERENCE {
        {
            this.command = new EditConference();
        }
    },
    DELETEREPORT {
        {
            this.command = new DeleteReportCommand();
        }
    },
    ADDREPORT {
        {
            this.command = new AddReportCommand();
        }
    };
    Command command;

    public Command getCommand() {
        return command;
    }
}

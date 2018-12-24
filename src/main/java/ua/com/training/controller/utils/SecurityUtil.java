package ua.com.training.controller.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.com.training.model.entity.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SecurityUtil {
    private static final Map<String, List<String>> permittedCommandMap = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        List<String> adminCommand = new LinkedList<>();
        adminCommand.add("catalog");
        adminCommand.add("changeLanguage");
        adminCommand.add("default");
        adminCommand.add("logout");
        adminCommand.add("deleteConference");
        adminCommand.add("editConference");
        adminCommand.add("editReport");
        adminCommand.add("subscribe");
        adminCommand.add("unsubscribe");
        adminCommand.add("createconference");


        List<String> speakerCommand = new LinkedList<>();
        speakerCommand.add("catalog");
        speakerCommand.add("changeLanguage");
        speakerCommand.add("default");
        speakerCommand.add("logout");
        speakerCommand.add("editReport");
        speakerCommand.add("subscribe");
        speakerCommand.add("unsubscribe");



        List<String> userCommand = new LinkedList<>();
        userCommand.add("subscribe");
        userCommand.add("unsubscribe");
        userCommand.add("catalog");
        userCommand.add("logout");
        userCommand.add("changelanguage");
        userCommand.add("default");
        userCommand.add("catalogofspeakers");

        List<String> guestCommand = new LinkedList<>();
        guestCommand.add("login");
        guestCommand.add("registration");
        guestCommand.add("catalog");
        guestCommand.add("changelanguage");
        guestCommand.add("default");

        permittedCommandMap.put("admin", adminCommand);
        permittedCommandMap.put("speaker", speakerCommand);
        permittedCommandMap.put("user", userCommand);
        permittedCommandMap.put("guest", guestCommand);

    }

    public boolean isSecuredPage(String urlPattern) {
        for (User.Role role : User.Role.values()) {
            if (urlPattern.contains(role.getStringRole())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPermission(String urlPattern, String role) {
        if (urlPattern.contains(role)) {
            String command = urlPattern.replaceFirst(".*/", "");
            return permittedCommandMap.get(role).contains(command);
        }
        return false;
    }

    public String hashPassword( String password)  {
        return BCrypt.hashpw(password,BCrypt.gensalt());

    }
}

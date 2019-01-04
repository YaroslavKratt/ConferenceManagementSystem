package ua.com.training.controller;

import org.junit.Test;
import ua.com.training.controller.commands.Command;
import ua.com.training.controller.commands.CommandFactory;
import ua.com.training.model.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CommandFactoryTest {
    @Test
public void whenWrongCommandCameThenReturnOptionalNull(){
        String commandUri = "/user/noSuchCommand";
        final CommandFactory factory = new CommandFactory();
        final HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI()).thenReturn(commandUri);
       Optional<Command>  optionalCommand=  factory.getCommand(request);
       assertEquals(Optional.empty(),optionalCommand);


    }


}

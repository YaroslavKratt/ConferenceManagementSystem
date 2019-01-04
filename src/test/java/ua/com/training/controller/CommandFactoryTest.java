package ua.com.training.controller;

import org.junit.Test;
import ua.com.training.controller.commands.Command;
import ua.com.training.controller.commands.CommandFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandFactoryTest {
    @Test
    public void whenWrongCommandCameThenReturnOptionalNull() {
        String commandUri = "/user/noSuchCommand";
        final CommandFactory factory = new CommandFactory();
        final HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI()).thenReturn(commandUri);
        Optional<Command> optionalCommand = factory.getCommand(request);
        assertEquals(Optional.empty(), optionalCommand);


    }


}

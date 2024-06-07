package main.controller;

import main.presentation.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuControllerTest {

    private MenuController menuController;
    private Presentation mockPresentation;
    private Frame mockFrame;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockFrame = mock(Frame.class);
        menuController = new MenuController(mockFrame, mockPresentation);
    }

    @Test
    void executeCommand() {
        Command mockCommand = mock(Command.class);
        menuController.executeCommand(mockCommand);
        verify(mockCommand, times(1)).execute();
    }

    @Test
    void mkMenuItem() {
        String itemName = "TestItem";
        MenuItem menuItem = menuController.mkMenuItem(itemName);
        assertNotNull(menuItem, "MenuItem should not be null");
        assertEquals(itemName, menuItem.getLabel(), "MenuItem label should match the provided name");
        assertEquals(itemName.charAt(0), menuItem.getShortcut().getKey(), "MenuItem shortcut should match the first character of the name");
    }

    @Test
    void testAddMenuItemAndActionListener() throws Exception {
        Command mockCommand = mock(Command.class);
        Menu mockMenu = new Menu("MockMenu");
        String commandTitle = "TestCommand";

        // Use reflection to access the private addMenuItem method
        java.lang.reflect.Method addMenuItemMethod = MenuController.class.getDeclaredMethod("addMenuItem", Menu.class, String.class, Command.class);
        addMenuItemMethod.setAccessible(true);
        addMenuItemMethod.invoke(menuController, mockMenu, commandTitle, mockCommand);

        MenuItem addedMenuItem = mockMenu.getItem(0);
        assertNotNull(addedMenuItem, "MenuItem should be added to the menu");
        assertEquals(commandTitle, addedMenuItem.getLabel(), "MenuItem label should match the command title");

        // Simulate the menu item being clicked
        for (ActionListener listener : addedMenuItem.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }

        verify(mockCommand, times(1)).execute();
    }
}

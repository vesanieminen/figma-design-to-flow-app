package com.example.application.views;


import com.example.application.views.employees.EmployeesView;
import com.example.application.views.home.HomeView;
import com.example.application.views.settings.SettingsView;
import com.example.application.views.tasks.TasksView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Overflow;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Whitespace;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, Component icon, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontSize.MEDIUM, Whitespace.NOWRAP);

            if (icon != null) {
                link.add(icon);
            }
            link.add(text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

    }

    public MainLayout() {
        addToNavbar(createHeaderContent());
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames(BoxSizing.BORDER, Display.FLEX, Width.FULL);

        Div layout = new Div();
        layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);

        H1 appName = new H1("JobPlanner");
        appName.addClassNames(Margin.Vertical.MEDIUM, Margin.End.AUTO, FontSize.LARGE);
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames(Display.FLEX, Overflow.AUTO, Padding.Horizontal.MEDIUM, Padding.Vertical.XSMALL, AlignItems.CENTER);

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames(Display.FLEX, Gap.SMALL, ListStyleType.NONE, Margin.NONE, Padding.NONE);
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);

        }

        final var searchTextField = new TextField();
        searchTextField.setPlaceholder("Search");
        searchTextField.setSuffixComponent(VaadinIcon.SEARCH.create());
        final var notificationButton = new Button(VaadinIcon.BELL_O.create());
        notificationButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        notificationButton.addClassNames(Width.MEDIUM);
        final var avatar = new Avatar("John Doe", "https://s3-alpha-sig.figma.com/img/1057/7a7c/7ad534e7bf5e3397bde4bc458d14ad59?Expires=1717977600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=h2EvtZFWeE64L5AvDYhWDvBmIaqXzJ8I9gNU4E2wx-oB2xWwJPCtcyDA7Kzk2XBxv61s4fTm8AjDSb7IEXjp85Y7d6sjfRyNUdyC0rV7O7p07LBYz4TMiKMxRJ3EFWl4ilXpU8lvBZXsa58fgyR-F18zM~lAI7-H1nFQwD-v0CeYFVCjhATagz0~8XJk2qTWntt5x4IdyD57OuFbT1DPi1htDrkT8PcvVPE~V0~gvU96-Tc~3LG~dDTvbqShRAii9IJ2BIophh6mzFFH2rq0Ft~NyVrZZxTzPTRsB3PxyEaOSpSQ7f1tZiX1XClBj8YwnvFuef19khWFwM3qkvYkAQ__");
        final var topRightDiv = new Div(searchTextField, notificationButton, avatar);
        topRightDiv.addClassNames(
                Display.FLEX,
                AlignItems.CENTER,
                Margin.Left.AUTO,
                Margin.Right.MEDIUM,
                Gap.MEDIUM
        );

        header.add(layout, nav, topRightDiv);
        return header;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Home", null, HomeView.class), //

                new MenuItemInfo("Tasks", null, TasksView.class), //

                new MenuItemInfo("Employees", null, EmployeesView.class), //

                new MenuItemInfo("Settings", null, SettingsView.class), //

        };
    }

}

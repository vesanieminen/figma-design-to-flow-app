package com.example.application.views.employees;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Employees")
@Route(value = "employees", layout = MainLayout.class)
public class EmployeesView extends Div {

    public EmployeesView() {
        addClassNames(LumoUtility.FlexDirection.COLUMN, Margin.MEDIUM);

        final var newEmployeeH1 = new H1("New Employee");
        final var personalDetailsH3 = new H3("Personal details");
        final var firstnameTextField = new TextField("Firstname");
        final var lastnameTextField = new TextField("Lastname");
        final var emailField = new EmailField("Email");
        final var formLayout = new FormLayout(firstnameTextField, lastnameTextField, emailField);
        formLayout.setColspan(emailField, 2);
        final var leftSideDiv = new Div(newEmployeeH1, personalDetailsH3, formLayout);


        add(leftSideDiv);
    }

}

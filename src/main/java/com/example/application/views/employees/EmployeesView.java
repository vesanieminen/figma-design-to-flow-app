package com.example.application.views.employees;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import static com.example.application.util.Flex.BASIS_40;
import static com.example.application.util.Size.MAX_WIDTH_25_REM;

@PageTitle("Employees")
@Route(value = "employees", layout = MainLayout.class)
public class EmployeesView extends Main {

    public EmployeesView() {
        final var newEmployeeH1 = new H1("New Employee");
        newEmployeeH1.addClassNames(Margin.Vertical.LARGE);

        final var saveButton = new Button("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        final var cancelButton = new Button("Cancel");
        final var buttonDiv = new Div(saveButton, cancelButton);
        buttonDiv.addClassNames(
                LumoUtility.Gap.MEDIUM,
                LumoUtility.JustifyContent.END,
                Margin.Vertical.LARGE,
                LumoUtility.Display.Breakpoint.Large.FLEX
        );

        final var div = new Div(newEmployeeH1, buttonDiv);
        div.setMaxWidth("60rem");
        div.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.Gap.XLARGE,
                Margin.Horizontal.AUTO,
                LumoUtility.JustifyContent.BETWEEN,
                LumoUtility.Padding.Horizontal.XLARGE
        );

        final var leftSideDiv = createLeftSideDiv();
        final var rightSideDiv = createRightSideDiv();

        final var horizontalSection = new Section(leftSideDiv, rightSideDiv);
        horizontalSection.setMaxWidth("60rem");
        horizontalSection.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Padding.Horizontal.XLARGE,
                LumoUtility.Gap.XLARGE,
                LumoUtility.JustifyContent.CENTER,
                Margin.Horizontal.AUTO,
                LumoUtility.Grid.Breakpoint.Large.COLUMNS_2,
                "lg:grid"
        );
        add(div, horizontalSection);
    }

    private static Div createLeftSideDiv() {
        final var personalDetailsH3 = new H3("Personal details");
        personalDetailsH3.addClassNames(Margin.Top.MEDIUM);
        final var firstnameTextField = new TextField("First name");
        final var lastnameTextField = new TextField("Last name");
        final var emailField = new EmailField("Email");
        final var personalDetailsForm = createForm(
                firstnameTextField,
                lastnameTextField,
                emailField
        );
        personalDetailsForm.setColspan(emailField, 2);

        final var jobDetailsH3 = new H3("Job details");
        jobDetailsH3.addClassNames(Margin.Top.XLARGE);
        final var startDate = new DatePicker("Start date");
        startDate.setPlaceholder("Pick a date");
        final var needsOnboardingCheckbox = new Checkbox("Needs onboarding");
        needsOnboardingCheckbox.setValue(true);
        final var jobTitleField = new TextField("Job title");
        final var teamComboBox = new ComboBox<>("Team");
        final var supervisorComboBox = new ComboBox<>("Supervisor");
        supervisorComboBox.setInvalid(true);
        supervisorComboBox.setErrorMessage("Please select a person");
        final var jobDetailsForm = createForm(
                startDate,
                needsOnboardingCheckbox,
                jobTitleField,
                teamComboBox,
                supervisorComboBox
        );
        jobDetailsForm.setColspan(jobTitleField, 2);
        jobDetailsForm.setColspan(teamComboBox, 2);
        jobDetailsForm.setColspan(supervisorComboBox, 2);

        final var leftSideDiv = new Div(
                personalDetailsH3,
                personalDetailsForm,
                jobDetailsH3,
                jobDetailsForm
        );

        leftSideDiv.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN
        );
        return leftSideDiv;
    }

    private static FormLayout createForm(AbstractField... fields) {
        final var formLayout = new FormLayout(fields);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("20rem", 2)
        );
        return formLayout;
    }

    private static Div createRightSideDiv() {
        final var profilePictureSpan = new Span("Profile picture");
        final var upload = new Upload();
        upload.setHeight("16.25rem");
        final var tasksForNewEmployeeH3 = new H3("Tasks for new employee");
        tasksForNewEmployeeH3.addClassNames(
                Margin.Top.LARGE,
                Margin.Bottom.SMALL
        );
        final var taskListBox = new MultiSelectListBox<Task>();
        taskListBox.setItemLabelGenerator(item -> item.caption);
        taskListBox.setItems(Task.values());
        taskListBox.select(Task.HEALTH_CHECK, Task.MEETING_WITH_CEO);
        final var cardDiv = new Div(
                profilePictureSpan,
                upload,
                tasksForNewEmployeeH3,
                taskListBox
        );
        cardDiv.addClassNames(
                "card",
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Padding.LARGE,
                LumoUtility.BorderRadius.LARGE,
                Margin.Top.XLARGE
        );

        final var rightSideDiv = new Div(cardDiv);
        return rightSideDiv;
    }

    enum Task {
        HEALTH_CHECK("Health check"),
        MEETING_WITH_HR("Meeting with HR"),
        MEETING_WITH_CEO("Meeting with CEO"),
        ITEM("Item");

        public final String caption;

        Task(String caption) {
            this.caption = caption;
        }

    }

}

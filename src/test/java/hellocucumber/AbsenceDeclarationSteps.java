package hellocucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbsenceDeclarationSteps {
    private Employee employee;
    private TeamLeader teamLeader;
    private HRManager hrManager;
    private AbsenceForm absenceForm;

    @Before
    public void setUp() {
        employee = new Employee();
        teamLeader = new TeamLeader();
        hrManager = new HRManager();
        absenceForm = new AbsenceForm();
    }

    @Given("l'employé a besoin de déclarer une absence")
    public void employeeNeedsToDeclareAbsence() {
        // Logique pour indiquer que l'employé a besoin de déclarer une absence
    }

    @When("l'employé remplit le formulaire de déclaration d'absence")
    public void employeeFillsAbsenceForm() {
        absenceForm.fill("Raison de l'absence", "2023-01-01", "2023-01-05");
    }

    @When("soumet la déclaration")
    public void employeeSubmitsDeclaration() {
        teamLeader.receiveRequest(absenceForm);
    }

    @Then("le Team Leader reçoit l'email de demande et valide")
    public void teamLeaderReceivesAndValidatesRequest() {
        teamLeader.validateRequest(absenceForm);
    }

    @Then("la déclaration d'absence est validée")
    public void absenceDeclarationIsValidated() {
        assertTrue(absenceForm.isValidated());
    }

    @Then("le RRH reçoit l'email de déclaration d'absence")
    public void hrManagerReceivesAbsenceEmail() {
        hrManager.receiveAbsenceEmail(absenceForm);
    }
}

class AbsenceForm {
    private String reason;
    private String startDate;
    private String endDate;
    private boolean validated;

    public void fill(String reason, String startDate, String endDate) {
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.validated = false; // Initialisation
    }

    public void validate() {
        this.validated = true; // Validation de la demande
    }

    public boolean isValidated() {
        return validated;
    }
}

class Employee {
    private String name;
    private String email;
    private String position;

    public Employee() {
        this.name = name;
        this.email = email;
        this.position = position;
    }

    // Méthodes pour déclarer une absence
    public void declareAbsence(AbsenceForm absenceForm, TeamLeader teamLeader) {
        // Remplir le formulaire d'absence
        // On suppose que fill() a été appelé pour remplir les détails nécessaires dans le formulaire
        teamLeader.receiveRequest(absenceForm); // Envoyer la demande au TeamLeader
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }
}


class TeamLeader {
    public void receiveRequest(AbsenceForm form) {
        // Logique pour recevoir la demande
    }

    public void validateRequest(AbsenceForm form) {
        form.validate(); // Valide le formulaire
    }
}


class HRManager {
    public void receiveAbsenceEmail(AbsenceForm form) {
        // Logique pour recevoir l'email de déclaration d'absence
    }
}
package hellocucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestionActionListSteps {
    private Task task;
    private TeamManager teamManager;

    @Before
    public void setUp() {
        teamManager = new TeamManager();
        task = new Task("Projet X", "Livrable Y", "Haute", "2023-10-01", "2023-10-05");
    }

    @Given("le TL\\/TLA reçoit une tâche du management")
    public void le_tl_tla_reçoit_une_tâche_du_management() {
        teamManager.receiveTask(task);
        System.out.println("Tâche reçue par le TL/TLA: " + task);
    }

    @When("le TL\\/TLA note toutes les informations liées à la tâche")
    public void le_tl_tla_note_toutes_les_informations_liées_à_la_tâche() {
        teamManager.noteTaskInformation(task);
        System.out.println("Informations notées pour la tâche: " + task);
    }

    @When("la tâche dépasse le délai prévu")
    public void taskExceedsDeadline() {
        task.setEndDate("2023-10-10"); // Simuler un dépassement de délai
    }

    @Then("une notification est envoyée")
    public void notificationIsSent() {
        teamManager.sendNotification(task);
    }

    @Then("le statut de la tâche est actualisé")
    public void taskStatusIsUpdated() {
        task.updateStatus("En retard");
        assertEquals("En retard", task.getStatus());
    }
}

class Task {
    private String project;
    private String deliverable;
    private String priority;
    private String startDate;
    private String endDate;
    private String status;

    public Task(String project, String deliverable, String priority, String startDate, String endDate) {
        this.project = project;
        this.deliverable = deliverable;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "En cours"; // Statut initial
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}


class TeamManager {
    public void receiveTask(Task task) {
        // Logique pour recevoir la tâche
    }

    public void noteTaskInformation(Task task) {
        // Logique pour noter les informations de la tâche
    }

    public void sendNotification(Task task) {
        // Logique pour envoyer une notification
        System.out.println("Notification: La tâche " + task.getStatus() + " est en retard.");
    }
}



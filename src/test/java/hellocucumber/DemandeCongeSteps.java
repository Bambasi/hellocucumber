package hellocucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;

public class DemandeCongeSteps {
    private Employe employe;
    private TeamLead teamLead;
    private ProjectManager projectManager;
    private TechnicalDirector technicalDirector;

    private boolean demandeValide;
    private String commentaires;

    @Before
    public void setUp() {
        // Initialisez toutes les instances ici
        employe = new Employe("Nom de l'employé");
        teamLead = new TeamLead("Team Leader");
        projectManager = new ProjectManager("Project Manager");
        technicalDirector = new TechnicalDirector("Technical Director");
    }

    @Given("l'employé a besoin d'un congé")
    public void employeBesoinConge() {

        employe.setNeedsLeave(true);

    }

    @When("l'employé remplit le formulaire de demande")
    public void employeRemplitFormulaire() {
        employe.fillLeaveRequestForm();
    }

    @When("l'employé transmet la demande au team leader et au chef de projet")
    public void employeTransmetDemande() {
        if (teamLead != null) {
            teamLead.receiveLeaveRequest(employe);
        }
        if (projectManager != null) {
            projectManager.receiveLeaveRequest(employe);
        }
    }

    @Then("le team leader reçoit le mail de demande")
    public void teamLeadReçoitMailDemande() {
        assert teamLead.hasReceivedRequest(employe);
    }

    @When("le team leader fait l'analyse de la demande")
    public void teamLeadAnalyseDemande() {
        teamLead.analyzeRequest(employe);
    }

    @When("le team leader vérifie le nombre de congés restants")
    public void teamLeadVerifieCongesRestants() {
        demandeValide = teamLead.checkRemainingLeaves();
    }

    @Then("si le team leader refuse la demande")
    public void teamLeadRefuseDemande() {
        if (!demandeValide) {
            commentaires = teamLead.createRefusalComments();
            teamLead.sendRefusalToEmployee(employe, commentaires);
        }
    }

    @When("l'employé reçoit la notification de refus de sa demande")
    public void employeRecevoitNotificationRefus() {
        employe.receiveRefusalNotification(commentaires);
    }

    @Then("si le team leader passe à la vérification du planning")
    public void teamLeadVerifiePlanning() {
        // Implémentez la logique pour vérifier le planning
        // Supposons que la logique pour valider ou refuser se produit ici également
    }

    @When("le team leader valide la demande")
    public void teamLeadValideDemande() {
        if (demandeValide) {
            teamLead.validateRequest(employe);
        }
    }

    @Then("il transmet la demande au chef de projet")
    public void transmetDemandeChefProjet() {
        projectManager.receiveLeaveRequest(employe);
    }

    @Given("le chef de projet reçoit la validation de la demande")
    public void chefProjetReçoitValidation() {
        // Cette étape est ici pour l'exemple
    }

    @When("le chef de projet fait l'analyse et le traitement")
    public void chefProjetAnalyseTraitement() {
        projectManager.analyzeRequest(employe);
    }

    @Then("si le chef de projet refuse la demande")
    public void chefProjetRefuseDemande() {
        if (!projectManager.checkIfRequestIsValid()) {
            commentaires = projectManager.createRefusalComments();
            projectManager.sendRefusalToEmployee(employe, commentaires);
        }
    }

    @When("le chef de projet valide la demande")
    public void chefProjetValideDemande() {
        projectManager.validateRequest(employe);
    }

    @Then("il transmet la demande au directeur technique")
    public void transmetDemandeDirecteurTechnique() {
        technicalDirector.receiveLeaveRequest(employe);
    }

    @Given("le directeur technique reçoit la notification de validation")
    public void directeurTechniqueReçoitNotification() {
        // Détail pour permettre la vérification
    }

    @When("le directeur technique fait l'analyse et le traitement")
    public void directeurTechniqueAnalyseTraitement() {
        technicalDirector.analyzeRequest(employe);
    }

    @Then("si le directeur technique valide la demande")
    public void directeurTechniqueValideDemande() {
        if (technicalDirector.approveRequest(employe)) {
            technicalDirector.notifyEmployeeAndHR(employe);
        } else {
            // Logique pour le refus
            String refusalComments = technicalDirector.createRefusalComments();
            technicalDirector.sendRefusalToEmploye(employe, refusalComments);
        }
    }

    @When("l'employé reçoit la notification de validation de la demande par mail")
    public void employeRecevoitNotificationValidation() {
        employe.receiveApprovalNotification();
    }

    @When("le responsable des ressources humaines reçoit la notification de validation de la demande par mail")
    public void rhRecevoitNotificationValidation() {
        // Logique pour vérifier que le RH reçoit bien la notification
    }

    @Then("l'employé reçoit la notification de refus de la demande et les commentaires")
    public void employeRecevoitNotificationRefusAvecCommentaires() {
        employe.receiveRefusalNotification(commentaires);
    }

    @When("le directeur technique élabore les commentaires de refus de la demande")
    public void le_directeur_technique_élabore_les_commentaires_de_refus_de_la_demande() {
        // Write code here that turns the phrase above into concrete actions
        commentaires = technicalDirector.createRefusalComments();
    }
    @Then("il transmet à l'employé")
    public void il_transmet_à_l_employé() {
        // Write code here that turns the phrase above into concrete actions
        technicalDirector.sendRefusalToEmployee(employe, commentaires);
    }
    @When("l'employé reçoit la notification de refus de la demande")
    public void l_employé_reçoit_la_notification_de_refus_de_la_demande() {
        // Write code here that turns the phrase above into concrete actions
        employe.receiveRefusalNotification(commentaires);
    }
    @Then("avec les commentaires")
    public void avec_les_commentaires() {
        // Write code here that turns the phrase above into concrete actions
        assert commentaires != null && !commentaires.isEmpty();
    }


    @Then("il élabore les commentaires de refus")
    public void ilÉlaboreLesCommentairesDeRefus() {
    }

    @And("les transmet à l'employé")
    public void lesTransmetÀLEmployé() {
    }

    @Then("il transmet à l'employé et au responsable des ressources humaines")
    public void ilTransmetÀLEmployéEtAuResponsableDesRessourcesHumaines() {
    }
}

class Employe {
    private String name;
    private boolean needsLeave;
    private String leaveRequest;

    public Employe(String name) {
        this.name = name;
    }

    public void setNeedsLeave(boolean needsLeave) {
        this.needsLeave = needsLeave;
    }

    public void fillLeaveRequestForm() {
        if (needsLeave) {
            leaveRequest = "Demande de congé remplie";
        }
    }

    public void receiveRefusalNotification(String comments) {
        System.out.println("Notification de refus reçue. Commentaires : " + comments);
    }

    public void receiveApprovalNotification() {
        System.out.println("Notification de validation reçue.");
    }

    public String getName() {
        return name;
    }

    // Getters et setters
}

class TeamLead {
    private String name;
    private boolean hasReceivedRequest;

    public TeamLead(String name) {
        this.name = name;
    }

    public void receiveLeaveRequest(Employe employe) {
        hasReceivedRequest = true;
        System.out.println("Le team leader " + name + " a reçu la demande de " + employe.getName());
    }

    public void analyzeRequest(Employe employe) {
        // Logique d'analyse (par exemple, vérification du planning)
        System.out.println("Analyse de la demande de " + employe.getName());
    }

    public boolean checkRemainingLeaves() {
        // Simule la vérification des congés restants
        return true; // ou false selon la logique
    }

    public String createRefusalComments() {
        return "Demande de congé refusée en raison de..." ;
    }

    public void sendRefusalToEmployee(Employe employe, String comments) {
        employe.receiveRefusalNotification(comments);
    }

    public void validateRequest(Employe employe) {
        System.out.println("Demande de " + employe.getName() + " validée par le team leader " + name);
    }

    public boolean hasReceivedRequest(Employe employe) {
        return hasReceivedRequest;
    }

    // Getters et setters
}

class ProjectManager {
    private String name;

    public ProjectManager(String name) {
        this.name = name;
    }

    public void receiveLeaveRequest(Employe employe) {
        System.out.println("Le chef de projet " + name + " a reçu la demande de " + employe.getName());
    }

    public void analyzeRequest(Employe employe) {
        // Logique d'analyse
        System.out.println("Analyse de la demande de " + employe.getName());
    }

    public boolean checkIfRequestIsValid() {
        // Vérifiez si la demande est valide
        return true; // ou false selon la logique
    }

    public String createRefusalComments() {
        return "Demande de congé refusée par le chef de projet." ;
    }

    public void sendRefusalToEmployee(Employe employe, String comments) {
        employe.receiveRefusalNotification(comments);
    }

    public void validateRequest(Employe employe) {
        System.out.println("Demande de " + employe.getName() + " validée par le chef de projet " + name);
    }

    // Getters et setters
}

class TechnicalDirector {
    private String name;

    public TechnicalDirector(String name) {
        this.name = name;
    }

    public void receiveLeaveRequest(Employe employe) {
        System.out.println("Le directeur technique " + name + " a reçu la demande de " + employe.getName());
    }

    public void analyzeRequest(Employe employe) {
        // Logique d'analyse
        System.out.println("Analyse de la demande de " + employe.getName());
    }

    public boolean approveRequest(Employe employe) {
        // Logique pour approuver ou refuser
        return true; // ou false selon la logique
    }

    public String createRefusalComments() {
        return "Demande refusée par le directeur technique.";
    }

    public void sendRefusalToEmployee(Employe employe, String comments) {
        employe.receiveRefusalNotification(comments);
    }

    public void notifyEmployeeAndHR(Employe employe) {
        System.out.println("Notification de validation envoyée à " + employe.getName());
    }

    public void sendRefusalToEmploye(Employe employe, String refusalComments) {
        employe.receiveRefusalNotification(refusalComments);
        System.out.println("Le team leader " + name + " a envoyé un refus à " + employe.getName() + " avec les commentaires : " + refusalComments);
    }

    // Getters et setters
}
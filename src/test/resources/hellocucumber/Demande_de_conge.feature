Feature: Processus de demande de congé

  Scenario: Demande de congé par un employé
    Given l'employé a besoin d'un congé
    When l'employé remplit le formulaire de demande
    And l'employé transmet la demande au team leader et au chef de projet
    Then le team leader reçoit le mail de demande

  Scenario: Analyse de la demande par le team leader
    When le team leader fait l'analyse de la demande
    And le team leader vérifie le nombre de congés restants
    Then si le team leader refuse la demande
    Then il élabore les commentaires de refus
    And les transmet à l'employé

  Scenario: Notification de refus à l'employé
    When l'employé reçoit la notification de refus de sa demande
    Then si le team leader passe à la vérification du planning
    Then si le team leader refuse la demande
    Then il élabore les commentaires de refus
    And les transmet à l'employé

  Scenario: Validation de la demande par le team leader
    When le team leader valide la demande
    Then il transmet la demande au chef de projet

  Scenario: Analyse par le chef de projet
    Given le chef de projet reçoit la validation de la demande
    When le chef de projet fait l'analyse et le traitement
    Then si le chef de projet refuse la demande
    Then il élabore les commentaires de refus
    And les transmet à l'employé

  Scenario: Validation par le chef de projet
    When le chef de projet valide la demande
    Then il transmet la demande au directeur technique

  Scenario: Analyse par le directeur technique
    Given le directeur technique reçoit la notification de validation
    When le directeur technique fait l'analyse et le traitement
    Then si le directeur technique valide la demande
    Then il transmet à l'employé et au responsable des ressources humaines

  Scenario: Notifications finales
    When l'employé reçoit la notification de validation de la demande par mail
    And le responsable des ressources humaines reçoit la notification de validation de la demande par mail

  Scenario: Refus final par le directeur technique
    When le directeur technique élabore les commentaires de refus de la demande
    Then il transmet à l'employé
    When l'employé reçoit la notification de refus de la demande
    Then avec les commentaires
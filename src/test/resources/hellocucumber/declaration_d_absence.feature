Feature: Processus de déclaration d'absence

  Scenario: Déclaration d'une absence par un employé
    Given l'employé a besoin de déclarer une absence
    When l'employé remplit le formulaire de déclaration d'absence
    And soumet la déclaration
    Then le Team Leader reçoit l'email de demande et valide
    And la déclaration d'absence est validée
    And le RRH reçoit l'email de déclaration d'absence
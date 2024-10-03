Feature: Gestion d'action-list

  Scenario: Réception et gestion d'une tâche
    Given le TL/TLA reçoit une tâche du management
    When le TL/TLA note toutes les informations liées à la tâche
    And la tâche dépasse le délai prévu
    Then une notification est envoyée
    And le statut de la tâche est actualisé
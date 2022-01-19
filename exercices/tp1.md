# TP1

## Conventions de commit

### Comment nommer son commit

Lors d'un commit, le message devrait e3tre structuré ainsi:

`<type>[# issue]: <description du commit>`

Il y a différents types de commit : 
1. `feat` : Introduit une nouvelle feature au codebase
2. `fix` : Fixer un bug qui se trouve dans l'application
3. `rework` : Rework une feature ou un bug fix qui n'est pas au standard
4. `refactor` : Refactor du code pour clean up le codebase
5. `docs` : Ajouter de la documentation au repository

### Quoi et quand faire un commit
Un commit doit être effectué lorsqu'une bonne partie de la feature/bug est complété et lorsque le code se compile.
De plus, lorsqu'un membre de l'équipe arrête de travailler sur une feature/bug fix, il est important que celui-ci commit son avancement afin
de permettre à ses coéquipiers de continuer son travail.




### Branches principales
La seule branche principale est la branche `main`. Celle-ci contient du code qui passe tous les tests de l'application et qui a été
"reviewé" par les membres de l'équipe afin d'assurer sa qualité.


### Quand créer une nouvelle branche ?
Il est important de créer une nouvelle branche lorsqu'il est question de travailler sur le code, donc lors de l'ajout d'une nouvelle
feature ou lors d'un bug fix. Une fois le code terminé, la branche créée permettra de créer une Pull Request afin de faire reviewer le code
par les autres membres de l'équipe et ainsi assurer sa qualité.


### Quand faire une demande de changement / d'intégration (pull request / merge request) et sur quelle branche la faire?
Lorsqu'on désire merger sa pull request, il suffit de faire une demande de changement vers la branche principale, soit la branche 
`main`.




# Floppa

Le meilleur site de vente anonyme au Québec!

## Requis

- Java 11
- Maven

## Setup

### Compilation

```
mvn clean install
```

### Exécution

```
mvn exec:java
```

### Notre équipe de développement ###


  Nom  | Github
  ------------- | -------------
  Philippe Vincent  | Philrobots
  Maxime Miville-Deschênes  | maximemvd
  Marin Beauchesne  | marinoboi
  Charles-Émile Robitaille | Charlem33
  Vincent Fournier | GoalCaufield

### Information sur les technologies et les techniques ###

<details>
<summary> Test </summary>
<p>Librairie junit 5.7.2 et Mockito 2.28.2</p>
</details>

<details>
<summary> CI  </summary>
<p> Github action Java CI with Maven </p>
</details>

### Nos règles d'équipes ###

* Notre branche main est la branche principal de notre projet, il est strictement interdit de push directement sur cet branche
* Chaque pull request doit être révisé et accepté par au minimum 2 membres de l'équipe avant d'être transferé sur main
* Chaque membre de l'équipe s'engage à écrire du code de qualités respectant les principes SOLID-T ainsi que les guidelines du livre Clean code
* Chaque fonctionnalité de l'application doit être tester unitairement sur chacun de ses comportements
* Nom de convetion des branches
  1. Pour les features : feat-(nom de l'issue)-(courte description)
  2. Pour les corrections : bugfix-(description)





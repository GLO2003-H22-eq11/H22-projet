# TP3

## Planification du travail sur Github

### 1 pour le Project comprenant les colonnes et les issues associées

![image](https://user-images.githubusercontent.com/47373969/161623400-b0be5d05-88ff-4878-bfa5-f75f9ec141f9.png)


### 1 pour le milestone comprenant le titre, la description et les issues associées

![image](https://user-images.githubusercontent.com/47373969/161582278-0590ff76-8ba4-44d8-a035-714e57e1cb72.png)


### 3 pour les issues avec tous les éléments demandés visibles

1. Obtention vendeur courant 
![image](https://user-images.githubusercontent.com/47373969/161582775-c3f58a1f-7f01-4dc4-9d1c-78de5da57ad5.png)
2. Création d'offre sur un produit 
![image](https://user-images.githubusercontent.com/47373969/161582934-6049f9d8-a83a-44ff-859a-028cdf4f70dc.png)
3. Get health (v2)
![image](https://user-images.githubusercontent.com/47373969/161583042-fe916905-e9ce-4825-84e7-781b0e66142d.png)



### 3 pour les PR avec tous les éléments demandés visibles

1. Obtention vendeur courant
![image](https://user-images.githubusercontent.com/47373969/161583667-fb6e58bc-8cea-426e-be37-5cfb3863c89c.png)
![image](https://user-images.githubusercontent.com/47373969/161583800-b8ef00ad-615a-4fc6-a6df-4e7b239536fd.png)
2. Création d'offre sur un produit
![image](https://user-images.githubusercontent.com/47373969/161623548-3c6d8d5f-2080-446a-b3d2-dc9410f65e8b.png)
![image](https://user-images.githubusercontent.com/47373969/161584761-75cb1bcc-d5b5-4ce9-ace7-f8ed60881887.png)

3. Get health (v2)
![image](https://user-images.githubusercontent.com/47373969/161584152-d3ef5e61-3386-4c4f-8b82-b957c78ad4e9.png)



### 1 pour votre arbre de commits et de branches (au moins 3 branches et/ou 10 commits visibles)

Commit :

![image](https://user-images.githubusercontent.com/47373969/161583351-a12ae56f-0e1a-4c92-9d68-10f4fd5fc1b1.png)

Branche : 

![image](https://user-images.githubusercontent.com/47373969/161616992-79064dd3-8570-413f-88e9-64c0d9ffb04f.png)



## Pipeline CI

### Combien de temps passiez-vous à vérifier et tester manuellement le code lors des intégrations et des remises avant l'implantation du pipeline de tests automatisés?

Nous avons implementé le pipeline de tests automatisés dès la première remise. Nous ne pouvons donc pas 
précisé combien de temps nous avons passé à vérifiez et tester manuellement le code. 

Cependant, nous n'avions pas les tests End to End avant.
On passait donc en conséquence toujours une journée avant la remise à tester manuellement l'application en vérifiant chaques cas d'utilisations possibles 
des divers fonctionnalités de Floppa.

### Combien de temps passiez-vous à faire ces vérifications après l'implantation du CI?

Après l'implantation du CI, nous avons passé aucun temps à tester et vérifier le code car nos tests automatisés s'occupaient de cela pour nous.
Nous avons mis beaucoup de temps à implémenter les tests de notre application en s'assurant que ceux-ci couvraient l'entièreté de la logique de notre application.
Nous avons donc en conséquence une grande confiance envers ceux-ci et croyons que si notre CI passe, la totalité de notre code fonctionne sans problème.

### Quels sont les points positifs que le CI a apporté à votre processus? Donnez-en au moins 3.

1. Vérification du lint de notre code pour s'assurer d'avoir un code formatter de la même manière partout dans l'application et avoir un meilleur respect global du clean code.
2. Vérification des tests unitaires à l'intérieur de notre code pour s'assurer qu'un changement ne brisait pas le bon fonctionnement d'une composante de notre application.
3. Vérification des tests End to End. On pouvait facilement voir si nos modifications brisaient le bon fonctionnement d'une fonctionnalité particulière de Floppa.

### Le pipeline CI amène-t-il un élément qui pourrait devenir négatif ou dangeureux pour le processus, le produit et/ou l'équipe? Justifiez.

Pour notre équipe, le pipeline CI n'apporte aucun point négatif. Cependant, nous pensons que le pipeline CI peut vite devenir dangereux si une équipe de développement 
écrit des tests incomplets et que cette même équipe se fit totalement à son pipeline CI pour la vérification de son code. Dans ce cas, le pipeline CI ne pourra pas s'assurer le 
bon fonctionnement complet de l'application et de ses composantes car les tests ne couvrent pas tous les use case de l'application.
Il peut donc devenir dangereux que l'application comporte plusieurs bugs sans que l'équipe 
de développement soit au courant.


## Tests

### Quel proportion de temps passez-vous à faire l'implémentation du code fonctionnel versus celui des tests? Est-ce que cette proportion évolue au fil du temps? Pourquoi?

Nous essayons le plus possible d'écrire nos tests unitaires en même temps que notre code. Après quelques issues à écrire ses deux en même temps, on a pu 
facilement comprendre que nous passons presque autant de temps à développer
nos tests unitaires que de développer nos fonctionnalités, car nous testons tous les use cases possibles de notre logique ainsi que les interactions entre les differentes classes pour s'assurer du comportement de notre application. 
Cependant depuis le début des tests End to End, nous pouvons affirmer que nous passons plus de temps à implémenter nos tests que d'implémenter nos 
fonctionnalités.

### L'implémentation de tests augmente naturellement la charge de travail. Comment cela a-t-il affecté votre processus? (ex : taille des issues/PRs, temps d'implémentation, planification, etc.)

L'augmentation de la charge de travail a affecté notre processus courant en augmentant le temps d'implémentation de chaque 
issue et en prolongeant la durée des PRs étant donnée les commentaires sur les tests. 
Cependant, nous nous sommes adaptés en mieux divisant les issues en de plus petites pour réduire la taille de ses issues et 
leurs temps d'implémentation. Également, les tests End to End était implémenté dans une autre issue que les fonctionnalités, 
ce qui fait que les issues des fonctionalités n'étaient pas affectés par les tests End to End. 

### Avez-vous plus confiance en votre code maintenant que vous avez des tests? Justifiez.

Comme mentionnées plus haut, nous avons mis énormement de temps et d'importance dans nos tests dès le début du projet. On a donc entièrement confiance en les tests
implémentés dans notre application depuis le début du projet. On croit que si nos tests passent, chaque fonctionnalité respecte ses critères de succès.

### Que pouvez-vous faire pour améliorer l'état actuel (début TP2) de vos tests? Donnez au moins 3 solutions.

Voici 3 solutions qui pourraient aider l'état actuel ( début TP2 ) des tests de notre équipe de développement.

1. Premièrement, il faut regrouper les tests qui testent des fonctionnalités similaires ensemble pour que nos tests soient plus facile à maintenir et à lire.

2. Ensuite, il faut mieux renommer le nom de nos tests pour que ceux-ci offrent une meilleure documentation aux développeurs sur ce que le test doit testé. 

3. Dans chaque nouvelle pull-request, nous devons nous assurer que chaque nouvelle composante est bien testé unitairement pour s'assurer le bon fonctionnement de sa logique ainsi que d'éviter des bugs lors de futurs changement



# Stories

# Story 1
## Obtenir les trois plus grands acheteurs

## Description

En tant qu'utilisateur, je veux effectuer une recherche pour savoir qui sont les trois plus grands vendeurs de Floppa

## Critères de succès

1. Toutes les informations doivent être présentes et non-vides.
2. On doit afficher les vendeurs qui ont le plus de produits en vente.
3. Pour chaque vendeur affiché, on doit afficher le montant total de ses produits combinées

## Détails techniques

### Requête

`GET /sellers/top`

#### Réponse

```ts
[
    {
        "name": string,
        "id": string,
        "products": int,
        "totalAmount": Amount,
    },
   {
    "name": string,
        "id": string,
        "products": int,
        "totalAmount": Amount,
},
   {
    "name": string,
        "id": string,
        "products": int,
        "totalAmount": Amount,
}
]
```

#### Exemples

**Valide**

```json
[
    "first": {
      "name": "John Doe",
      "id": "5380911f-0399-4721-bdd7-382c9a500d8b",
      "products": 32,
      "totalAmount": 580.23
    },
    "second": {
      "name": "John Deez",
      "id": "f122ba54-91f5-4dde-ba99-6aca40dd4d19",
      "products": 27,
      "totalAmount": 540.50
    },
    "third": {
      "name": "John Deer",
      "id": "f122ba54-91f5-4dde-ba99-6aca40dd4d19",
      "products": 24,
      "totalAmount": 480.83
    }
]
```

### Réponse

#### Status

- `200 OK`


# Story 2
## Signalement de vendeurs fraudeux

## Description

En tant que vendeur, je veux signaler les vendeurs qui fraudent les acheteurs ou qui ont des comportements qui manquent de respects 
envers les autres vendeurs.

## Critères de succès


1. Un vendeur ne peut pas signaler le même vendeur plus qu'une fois.
2. Si un vendeur obtient 10 signalements de vendeurs différents dans le même mois, il ne peut plus vendre de produits.
3. Les signalements de vendeurs sont sauvegardée dans l'application.
4. Les acheteurs ne peuvent pas signalés les vendeurs, seulement les vendeurs peuvent se signaler.
5. Au début de chaque mois, les signalements d'un vendeur sont remis à 0 si celui-ci n'a pas obtenu 10 signalements dans le mois passée


## Détails techniques

### Requête

`POST /sellers/report/{sellerId}`

#### Headers

- `X-Seller-Id` : `string`
    - ID du vendeur.
    

### Réponse

#### Status

- `200 OK`

### Exceptions

- `ITEM_NOT_FOUND` si le vendeur n'existe pas.


# Story 3
## Obtention moyenne du rendement des produits d'un vendeur

## Description

En tant qu'utilisateur, je veux voir le pourcentage de la moyenne du rendement d'un vendeur en particulier, soit en comparant le prix de base 
avec l'offre la plus élevée de chacun de ses produits et en faire un pourcentage moyen.

## Critères de succès

1. Toutes les informations doivent être présentes et non-vides.
2. Le montant indiqué doit prendre en considération chaque produits du vendeurs avec au moins une offre
3. On vérifie que le vendeur existe bien
4. Rien ne confirme que la requête est fait par le vendeur

## Détails techniques

### Requête

`GET /sellers/yield/{sellerId}`

#### Réponse

```ts
{
  "name": string,
  "yield": Percentage | null    
}
```

#### Exemples

**Valide**

```json
{
  "name": "John Doe",
  "yield": 145
}
```

**Si le vendeur n'a pas d'offres ou de produits** 
```json
{
  "name": "John Doe",
  "yield": null
}
```

#### Status

- `200 OK`

### Exceptions

- `ITEM_NOT_FOUND` si le vendeur n'existe pas.
- `INVALID_PARAMETER` si un des champs est invalide.
- `MISSING_PARAMETER` si un des champs est manquant (`null`).

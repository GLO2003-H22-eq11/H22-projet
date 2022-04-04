# TP3

## Planification du travail sur Github

### 1 pour le Project comprenant les colonnes et les issues associées

![image](https://user-images.githubusercontent.com/47373969/161582151-bafa69e3-4514-4828-ae8b-6b319ef95176.png)


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
![image](https://user-images.githubusercontent.com/47373969/161584656-d75cb48b-b3eb-4f61-a74a-09d373b0b6ba.png)
![image](https://user-images.githubusercontent.com/47373969/161584761-75cb1bcc-d5b5-4ce9-ace7-f8ed60881887.png)

3. Get health (v2)
![image](https://user-images.githubusercontent.com/47373969/161584152-d3ef5e61-3386-4c4f-8b82-b957c78ad4e9.png)



### 1 pour votre arbre de commits et de branches (au moins 3 branches et/ou 10 commits visibles)

![image](https://user-images.githubusercontent.com/47373969/161583351-a12ae56f-0e1a-4c92-9d68-10f4fd5fc1b1.png)


## Pipeline CI

### Combien de temps passiez-vous à vérifier et tester manuellement le code lors des intégrations et des remises avant l'implantation du pipeline de tests automatisés?

Nous avons implementé le pipeline de tests automatisés dès la première remise. Nous ne pouvons donc pas 
précisé combien de temps nous avons passez à vérifiez et tester manuellement le code. Cependant, nous n'avions pas les tests End to End avant.
On passait donc en conséquence toujours une journée avant la remise à tester manuellement l'application en vérifiant chaques cas d'utilisations possibles.

### Combien de temps passiez-vous à faire ces vérifications après l'implantation du CI?

Après l'implantation du CI, nous avons passée aucun de temps à tester et vérifier le code car nos tests automatisés s'occupait de cela pour nous.

### Quels sont les points positifs que le CI a apporté à votre processus? Donnez-en au moins 3.

1. Vérification du lint de notre code pour s'assurer d'avoir un code formatter de la même manière partout dans l'application.
2. Vérification des tests unitaires à l'intérieur de notre code pour s'assurer qu'un changement ne brisait pas le bon fonctionnement de chaque composantes de notre application.
3.  Vérification des tests End to End. On pouvait facilement si nos modifications brisaient le bon fonctionnement d'une fonctionnalité particulière.

### Le pipeline CI amène-t-il un élément qui pourrait devenir négatif ou dangeureux pour le processus, le produit et/ou l'équipe? Justifiez.


## Tests

### Quel proportion de temps passez-vous à faire l'implémentation du code fonctionnel versus celui des tests? Est-ce que cette proportion évolue au fil du temps? Pourquoi?

### L'implémentation de tests augmente naturellement la charge de travail. Comment cela a-t-il affecté votre processus? (ex : taille des issues/PRs, temps d'implémentation, planification, etc.)

### Avez-vous plus confiance en votre code maintenant que vous avez des tests? Justifiez.

### Que pouvez-vous faire pour améliorer l'état actuel (début TP2) de vos tests? Donnez au moins 3 solutions.



# Stories

# Story #1
## Obtenir les trois plus grands acheteurs

## Description

En tant qu'utilisateur, je veux effectuer une recherche pour savoir qui sont les trois plus grands acheteurs de Floppa

## Critères de succès

1. L'offre est sauvegardée dans l'application.
2. Les nouvelles offres aparaissent dans les informations du produit associé (tel que décrit dans [get product](https://github.com/glo2003/H22-Iteration2/blob/master/features/2.product-get.md) et [filter products](https://github.com/glo2003/H22-Iteration2/blob/master/features/3.products-get.md)).
3. Les formats de email et de numéro de téléphones sont validés.
4. Le montant de l'offre doit être supérieur ou égal au montant suggéré du produit.
5. Un même client peut faire plusieurs offres. Celles-ci n'ont pas besoin d'être successivements plus élevées.
6. Le message doit être d'au moins 100 caractères.
7. Toutes les informations doivent être présentes et non-vides.
8. Rien ne confirme que l'offre n'a pas été crée par le vendeur (il n'y a pas d'authentification pour la personne qui fait une offre).

## Détails techniques

### Requête

`GET /sellers/top3`

#### Réponse

```ts
[
    "first": {
        "name": string,
        "id": string,
        "totalAmount": Amount,
    },
   "second": {
        "name": string,
        "id": string,
        "totalAmount": Amount,
   },
   "third": {
        "name": string,
        "id": string,
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
      "totalAmount": 580.23
    },
    "second": {
      "name": "John Deez",
      "id": "f122ba54-91f5-4dde-ba99-6aca40dd4d19",
      "totalAmount": 540.50
    },
    "third": {
      "name": "John Deer",
      "id": "f122ba54-91f5-4dde-ba99-6aca40dd4d19",
      "totalAmount": 480.83
    }
]
```

### Réponse

#### Status

- `200 OK`


# Story #2
## Création d'offre sur un produit

## Description

En tant qu'acheteur, je veux effectuer une offre sur un produit afin de signaler mon intérêt d'achat.

## Critères de succès

1. L'offre est sauvegardée dans l'application.
2. Les nouvelles offres aparaissent dans les informations du produit associé (tel que décrit dans [get product](https://github.com/glo2003/H22-Iteration2/blob/master/features/2.product-get.md) et [filter products](https://github.com/glo2003/H22-Iteration2/blob/master/features/3.products-get.md)).
3. Les formats de email et de numéro de téléphones sont validés.
4. Le montant de l'offre doit être supérieur ou égal au montant suggéré du produit.
5. Un même client peut faire plusieurs offres. Celles-ci n'ont pas besoin d'être successivements plus élevées.
6. Le message doit être d'au moins 100 caractères.
7. Toutes les informations doivent être présentes et non-vides.
8. Rien ne confirme que l'offre n'a pas été crée par le vendeur (il n'y a pas d'authentification pour la personne qui fait une offre).

## Détails techniques

### Requête

`POST /products/{productId}/offers`

#### Payload

```ts
{
  name: string,
  email: Email,
  phoneNumber: PhoneNumber,
  amount: Amount,
  message: string
}
```

#### Exemples

**Valide**

```json
{
  "name": "John Doe",
  "email": "john.doe@email.com",
  "phoneNumber": "18191234567",
  "amount": 48.23,
  "message": "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet."
}
```

### Réponse

#### Status

- `200 OK`

### Exceptions

- `ITEM_NOT_FOUND` si le produit n'existe pas.
- `INVALID_PARAMETER` si un des champs est invalide.
- `MISSING_PARAMETER` si un des champs est manquant (`null`).



# Story #3
## Création d'offre sur un produit

## Description

En tant qu'acheteur, je veux effectuer une offre sur un produit afin de signaler mon intérêt d'achat.

## Critères de succès

1. L'offre est sauvegardée dans l'application.
2. Les nouvelles offres aparaissent dans les informations du produit associé (tel que décrit dans [get product](https://github.com/glo2003/H22-Iteration2/blob/master/features/2.product-get.md) et [filter products](https://github.com/glo2003/H22-Iteration2/blob/master/features/3.products-get.md)).
3. Les formats de email et de numéro de téléphones sont validés.
4. Le montant de l'offre doit être supérieur ou égal au montant suggéré du produit.
5. Un même client peut faire plusieurs offres. Celles-ci n'ont pas besoin d'être successivements plus élevées.
6. Le message doit être d'au moins 100 caractères.
7. Toutes les informations doivent être présentes et non-vides.
8. Rien ne confirme que l'offre n'a pas été crée par le vendeur (il n'y a pas d'authentification pour la personne qui fait une offre).

## Détails techniques

### Requête

`POST /products/{productId}/offers`

#### Payload

```ts
{
  name: string,
  email: Email,
  phoneNumber: PhoneNumber,
  amount: Amount,
  message: string
}
```

#### Exemples

**Valide**

```json
{
  "name": "John Doe",
  "email": "john.doe@email.com",
  "phoneNumber": "18191234567",
  "amount": 48.23,
  "message": "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet."
}
```

### Réponse

#### Status

- `200 OK`

### Exceptions

- `ITEM_NOT_FOUND` si le produit n'existe pas.
- `INVALID_PARAMETER` si un des champs est invalide.
- `MISSING_PARAMETER` si un des champs est manquant (`null`).
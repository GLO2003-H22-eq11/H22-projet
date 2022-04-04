# TP3

<img width="1172" alt="image" src="https://user-images.githubusercontent.com/47373969/160435193-d5c55061-9c80-4b12-a42d-3c01c1ac7da0.png">

![image](https://user-images.githubusercontent.com/47373969/160616395-01c76573-410c-429f-aba4-07b1ce1d4c41.png)

<img width="1366" alt="image" src="https://user-images.githubusercontent.com/47373969/161575316-03289a4d-ada2-4b61-b0ca-419833ecca53.png">


# Stories

# Story #1
## Obtenir les trois plus grands acheteurs 

## Description

En tant qu'utilisateur, je veux effectuer une recherche pour savoir qui sont les trois plus grands acheteurs de Floppa

## Critères de succès

1. La réponse contient en ordre les acheteurs qui ont dépensé le plus d'argent sur Floppa.

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
      "id": "c51f5c4f-73e9-4ab4-9432-6f9ae64d8bf5",
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
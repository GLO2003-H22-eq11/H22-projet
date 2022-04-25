# Floppa

The best anonymous sales site in Quebec city ! 🇨🇦

## 🕹 Required to run the app

- Java 11
- Maven
- MongoDB

## 🛠 Setup MongoDB

### Install MongoDb on your machine 

### Mac

```
brew install mongodb-community@5.0
```


### 💻 Other OS

Please go look at the documentation [here](https://www.mongodb.com/docs/manual/installation/)

## 📱 Technology Information

<details>
<summary> Test </summary>
<p>END to END testing : Rest assured 5.0.1</p>
<p>Unit test : Junit 5.7.2 and Mockito 2.28.2</p>
</details>

<details>
<summary> CI  </summary>
<p> Github action Java CI with Maven </p>
</details>
 <br />
<hr />

### 👭 Our team rules 👬

* Our main branch is the principal branch of our project, it is strictly forbidden to push directly on this branch
* Each pull request must be reviewed and accepted by at least 2 team members before being transferred to main
* Each member of the team is committed to writing quality code respecting the SOLID-T principles as well as the guidelines of the Clean code book
* Each functionality of the application must be unit tested on each of its behaviors
* Branch convention name
  1. For features: feat-(issue number)-(short description)
  2. For fixes: bugfix-(description)

  <br />
<hr />


## 🧩 Components

Below is a list of our app with link to their live versions.

| Component | Environment | Live URL                                                                    |
| --------- | ----------- | --------------------------------------------------------------------------- |
| API       | Staging     | https://processus-equipe-11-staging.herokuapp.com/                                       |
| API       | Prod        | https://processus-equipe-11-production.herokuapp.com/    |


## ⚡️ Quickstart

### Using maven


```bash
mvn clean install 

mvn exec:java
```

### 🌐 Access the app

After running the app, the API will be served at `http://localhost:8000`.

## 📆 Releases

Every release will have its own documentation where we will list completed features and any other information related to the given release.

| Release   | Documentation file                  |  tags        |
| --------- | ----------------------------------- | -------------|
| Release 1 | [RELEASE_1.md](./exercices/tp1.md)  | remise1      |
| Release 2 | [RELEASE_2.md](./exercices/tp2.md)  | remise2      |
| Release 3 | [RELEASE_3.md](./exercices/tp3.md)  | remise3      |
| Release 4 | [RELEASE_3.md](./exercices/tp4.md)  | remise4      |


## ⭐️ Contributors
- [Maxime Miville-Deschênes](https://github.com/maximemvd)
- [Philippe Vincent](https://github.com/Philrobots)
- [Marin Beauchesne](https://github.com/marinoboi)
- [Charles-Émile Robitaille](https://github.com/Charlem33)
- [Vincent fournier](https://github.com/GoalCaufield)




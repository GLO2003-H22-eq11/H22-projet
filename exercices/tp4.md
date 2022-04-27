# TP4

## Planification du travail sur github

### 1 pour le Project comprenant les colonnes et les issues associées

![image](https://user-images.githubusercontent.com/47373969/165154181-77f5bae8-2938-4eab-9166-c48b71143dff.png)

### 1 pour le milestone comprenant le titre, la description et les issues associées

![image](https://user-images.githubusercontent.com/47373969/165154585-c16e2967-4dbb-421a-b8e2-7ad1c8e02da6.png)

### 3 pour les issues avec tous les éléments demandés visibles

#### Feature de compteur de vues sur les produits 

<img width="1355" alt="image" src="https://user-images.githubusercontent.com/47373969/165546721-54a65ccb-433a-4189-b140-8326e2a15151.png">

<img width="1407" alt="image" src="https://user-images.githubusercontent.com/47373969/165546832-f83394a4-0845-4384-a63d-8700ad48f507.png">


<img width="1375" alt="image" src="https://user-images.githubusercontent.com/47373969/165545624-810200a2-efa8-4b8e-a553-9e9b38839736.png">


#### Outils de métrique 

![image](https://user-images.githubusercontent.com/47373969/165155998-2e3fa344-fbc0-4947-951d-2fd8283d56af.png)

#### Open source 

![image](https://user-images.githubusercontent.com/47373969/165156122-d988dcc1-7ca4-4b77-81bd-21b20dd884e8.png)



### 3 pour les PR avec tous les éléments demandés visibles

#### Feature de compteur de vues sur les produits 

![image](https://user-images.githubusercontent.com/47373969/165154947-3f1e0693-fdb4-4053-b2ba-137b9e74f696.png)

![image](https://user-images.githubusercontent.com/47373969/165156316-2d93ad00-061b-4b00-bc92-16bb8ec7428f.png)


#### Outils de métrique 

![image](https://user-images.githubusercontent.com/47373969/165155015-3ea1ae83-0d06-4d34-b5b8-fd32dbbb21f2.png)

![image](https://user-images.githubusercontent.com/47373969/165156678-3630244e-5b29-44b2-a347-ed6152d08e28.png)


#### Open source

![image](https://user-images.githubusercontent.com/47373969/165155626-7dfb66e6-f6af-41f3-9778-0cbd35d99688.png)

![image](https://user-images.githubusercontent.com/47373969/165156928-abac6afe-6d9d-4702-a941-f9567fbcfacd.png)




### 1 pour votre arbre de commits et de branches (au moins 3 branches et/ou 10 commits visibles)

Commit sur la branche principale main : 

![image](https://user-images.githubusercontent.com/47373969/165154815-c2d57609-3817-4cec-b546-79403bf89912.png)

Branche : 

![image](https://user-images.githubusercontent.com/47373969/165155282-79cdf7a4-28fd-4964-9acb-772a69e34256.png)



## Outils de métriques

### Outil d'analyse de la qualité du code

Pour tout ce qui est de l'analyse de la qualité du code, nous avons utilisé l'outil SonarCloud.
Celui-ci s'intègre directement avec notre CI et fait une vérification de sécurité, d'analyse du code sur chaque pull request.
Nous avons vraiment apprecié l'utilisation de SonarCloud pour nous faciliter dans notre développement.

#### Voici quelques screenshots montrant son utilisation :

#### Page principal de l'outil Sonarcloud
![image](https://user-images.githubusercontent.com/47373969/164241804-8f8c8a00-a704-49e3-9883-1726a9f633e0.png)

#### Page d'analyse de la qualité du code 
![image](https://user-images.githubusercontent.com/47373969/164241716-9594923e-fb12-4b95-b214-d7b009495c1a.png)

#### Page montrant les différentes informations de notre code
![image](https://user-images.githubusercontent.com/47373969/164242012-ae674c32-e295-40c1-996c-e9e2ca952b0f.png)

#### Analyse de Sonarcloud dans les pulls requests
![image](https://user-images.githubusercontent.com/47373969/165157528-55c449af-1203-4d64-a4c1-a0bd4532fbc6.png)


### Outil de détection des failles de sécurités 

Pour analyser la sécurité des dépendances externes, nous avons utilisé le DependacyBot de github qui s'intègre directement avec notre CI à l'aide du fichier `dependabot.yml`. 
Celui-ci ouvre des pull-request automatiquement pour mettre des dépendances à jours. Nous avons bien aimé l'outils.

Nous avons également encore fait utilisation de Sonarcloud pour détecter nos vulnérabilités et nos risques de sécurités dans notre code.
Sonarcloud nous a donnée la cote de A pour la sécurité, qui est la meilleure sécurité possible dans le système de sonarcloud.

### Dependancy bot :

![image](https://user-images.githubusercontent.com/47373969/164242316-8c51eee4-c7a2-4720-b354-61c3fd07c5b8.png)

![image](https://user-images.githubusercontent.com/47373969/164242364-298fca96-9d52-410d-8c52-6db822bdb6f5.png)

![image](https://user-images.githubusercontent.com/47373969/164242530-7ea13155-690e-482b-908f-abb19cc869f4.png)

#### Sonarcloud :

#### Résumé des analyses de sécurités
![image](https://user-images.githubusercontent.com/47373969/164267759-36466968-1fe7-4ae6-878f-df1b771cd969.png)

<img width="734" alt="image" src="https://user-images.githubusercontent.com/47373969/165554054-ee875265-d83d-4db5-86f1-c3f73695f28c.png">


![image](https://user-images.githubusercontent.com/47373969/164242714-07a4357c-20fb-4456-ab56-ba40e9befa76.png)




### Outil de mesure du test coverage

Pour l'outil de mesurage de notre couverture de tests, nous avons encore fait utilisation de l'outil Sonarcloud.

Celui-ci nous donnait tout ce que nous avions besoin pour faire une bonne couverture des tests, voici quelques exemples.

#### Page global pour la couverture de test, on apercoit qu'on a une couverte de 86.7%
<img width="718" alt="image" src="https://user-images.githubusercontent.com/47373969/165553895-3852c3b8-da45-4763-9753-1323c96ba435.png">


<img width="735" alt="image" src="https://user-images.githubusercontent.com/47373969/165554334-df7e8ef6-44a4-4157-b35d-85f26450d93a.png">


#### Ici, on peut voir que notre classe `ProductResource` a une couverture de test de 95.7%
<img width="718" alt="image" src="https://user-images.githubusercontent.com/47373969/165554517-a808c549-4812-47ca-9b29-83b107156e9c.png">

#### Ici, on peut voir que notre classe `SellerWithProducsDomainService` a une couverture de test de 100%
<img width="719" alt="image" src="https://user-images.githubusercontent.com/47373969/165554797-afbbe8af-adba-4b73-87bb-97b11f8805df.png">



## Open source


### Préparation 

#### 1.Nommez 3 avantages à contribuer à des projets open source en tant qu'entreprise et justifiez en quoi cela peut être bénéfique pour tous.

#### 3 avantages à contribuer à des projets open source en tant qu'entreprise 

Le premier avantage que nous avons discuté est qu'une entreprise faisant utilisation d'un logiciel open source a la possibilité 
d'embaucher un développeur logiciel pour qu'il puisse apporter des améliorations personnalisées au logiciel plutôt que de se fier aux décisions d'un
fournisseur d'une source privée où la contribution externe n'est pas possible.

Le deuxième avantage que nous avons discuté en équipe est qu'une entreprise qui contribue à des projets open source peut cibler des développeurs 
contribuant habilement au logiciel open source et ainsi potentiellement leur offrir un emploi dans la compagnie. Contribuez au projet open source peut
donc être une certaine manière de recruter des développeurs logiciels compétents.

Le troisième avantage est de réduire le coût total d'utilisation des produits logiciels de l'entreprise. En effet, ce qui rend les projets open source
unique est qu'une organisation peut utiliser le projet open source comme bon lui semble sans payez de frais d'utilisations ou de frais de propriété
intellectuelle. Une entreprise peut donc faire l'utilisation d'un projet open source plutôt de payer que de payer un système logiciel à frais élevé.

#### Justifiez en quoi cela peut être bénéfique pour tous
La contribution des compagnies est bénéfique pour tous étant donné qu'une même compagnie regroupe plusieurs développeurs, donc si une compagnie décide de contribuer aux projets open
 source, cela veut dire que plusieurs développeurs vont contribuer. Cela fait donc en sorte que la contribution des compagnies 
dans le domaine de l'open source agrandit grandement la communauté de l'open source.

Aussi, les grosses organisations technologiques ont énormément de pouvoir sur les différents
mouvements technologiques, donc si les grosses compagnies comme Microsoft et Adobe décident de se tourner au open source, cela va grandement contribuer à l'avancement
de l'open source ainsi qu'à ses développements. Il aura donc plus de logiciels open source disponible pour nous tous.

Aussi, si une compagnie contribue fortement à un projet open source, un développeur peut se faire remarquer par la compagnie en apportant des 
contributions importantes au projet. Cela peut donc devenir un potentiel futur emploie pour le développeur contribuant au projet open source.


#### 2. Décrivez 3 défis qu'impose la mise en place d'un projet open source et justifiez.

1. Un premier défi est d'avoir une bonne définition des objectifs du projet à l'aide des divers outils de documentation 
lors de la mise en place du produit pour que les futurs contributeurs comprennent bien la vision du projet 
et ainsi pour qu'ils puissent apporter des contributions en lien avec le but du projet.

2. Un autre défi lors de la mise en place d'un projet open source est de savoir dire non aux contributeurs apportant des contributions éloignant le projet
de sa raison d'être. Un projet open source va éventuellement avoir de nombreuses contributions externes, et certaines contributions ne correspondront pas aux critères du projet. 
Un défi sera alors de cibler ces contributions et ensuite poliment expliquer la situation au contributeur pour lui mentionner la raison qui amène le rejet de
sa contribution.

3. Le dernier défi lors de la mise en place d'un projet open source est l'acceptation d'une licence commune pour que les contributeurs sachent 
comment ils peuvent contribuer au projet et l'utiliser comme bon leur semble. En effet, chaque projet logiciel open source doit avoir une licence quelconque, car c'est 
cette licence qui définit les politiques de contributions et les méthodes d'utilisation du logiciel. On peut donc mentionner qu'un défi lors de la mise en place
d'un projet open source est de bien choisir la licence pour que les contributeurs aient les droits de contributions et d'utilisation selon les désirs 
du créateur.


#### 3. Quelle information vous a-t-elle le plus surprise à propos de l'open source?

Plusieurs informations nous ont grandement surpris lors de l'apprentissage de l'open source. Tout d'abord, nous avons été très surpris de voir que 
n'importe qui a les droits de contribuer au projet open source comme bon il le semble. Nous pensions que c'était seulement une certaine catégorie 
de développeur qui pouvait contribuer ou bien qu'il fallait avoir un "rôle" dans le projet open source en question pour y contribuer.

Sur un autre niveau, ce qui nous a le plus surpris est que certaines des plus grosses compagnies au monde comme IBM, Adobe, SAP participent activement dans la communauté de l'open source, nous pensions que c'était simplement
des développeurs autonomes qui contribuaient principalemetn aux projets open source.

Aussi, de voir que même des compagnies comme Microsoft, qui à la base s'opposaient contre les avancements de l'open source, sont rendues à mettre la majorité de leurs principaux logiciels  en open source nous a grandement étonné.

### Éxecution

#### Créer un fichier pour le "Code of conduct" en suivant les meilleures pratique. Si vous utilisez un template, vous devez en citer la source et expliquer pourquoi vous l'avez utilisé.

Pour notre "Code of conduct", nous avons utilisé le template de "Contributor Covenant'. Celui-ci est un des templates offerts par github pour le "Code of conduct".

Nous avons utilisé le "Code of conduct" de Contributor Covenant car nous trouvons que ce code de conduite prône une communauté agréable ouverte d'esprit qui n'accepte en 
aucun cas les commentaires rabaissants ou autre type d'attaques personnelles. En effet, le code de conduite que nous avons utilisé mentionne qu'il accepte tous les types
d'individus, peu importe la nationalité, la couleur de peau, la religion, etc., et qu'il prône le respect des individus sans accepter les commentaires discriminants entre contributeurs.
Le code de conduite cherche vraiment à établir une communauté ou le développement logiciel est agréable et c'est exactement ce que notre équipe cherche.
Nous avons également bien aimé le fait qu'il est mentionné que les propriétaires du projet 
peuvent prendre les mesures nécessaires pour retirer des personnes néfastes ou des commentaires haineux. 
Aussi, les directives d'applications sont extrêmement claires et bien détaillées. 

Bref, le code de conduite de "Contributor Convenant" encourage les comportements que l'on veut voir pour notre projet.


#### Créer un fichier pour la licence en suivant les meilleures pratiques. Indiquez les 3 raisons principales (dans le fichier d'exercice) pour lesquelles vous avez choisi cette licence par rapport aux autres.


Comme vous pouvez l'apercevoir, nous avons décidé de choisir la licence MIT. 

Comme nous développons un simple projet d'école, notre équipe était très ouverte à ce que les contributeurs externes puissent modifier et utiliser notre 
code comme bon leur semble dans le futur. Nous avons donc choisi la licence MIT pour ses raisons :


1. La licence MIT a une description extrêmement simple à comprendre, elle utilise moins de mots que "The Apache license" pour documenter ses règlementations.

2. La licence MIT est bien connue dans le monde de l'open source. Les futurs développeurs désirant contribuer à notre projet n'auront donc pas 
à se casser la tête à essayer de comprendre notre licence car, fort probablement, ils connaîtront déjà la licence MIT ainsi que ses règlementations.

3. Finalement, une autre raison de l'utilisation de celle-ci, comparativement à la licence GPL par exemple, est que plus de gens peuvent utiliser notre logiciel.
Comme plus de gens peuvent utiliser notre logiciel, on aura tendance à obtenir plus de contributions et donc d'obtenir une meilleure communauté pour notre projet open source.


#### Créer un fichier pour la contribution décrivant les meilleures pratiques de collaboration et de développement que vous voulez encourager au sein du projet. Si vous utilisez un template, vous devez en citer la source et expliquer pourquoi vous l'avez utilisé.

Pour le fichier de contribution, nous avons pris le [template suivant](https://github.com/auth0/open-source-template/blob/master/GENERAL-CONTRIBUTING.md). 
Cependant, nous avons apporté des modifications à ce fichier de contribution pour que celui-ci soit mieux adapté à notre projet.

Nous avons pris ce template pour plusieurs raisons. 

Premièrement, nous avons bien aimé le texte d'accueil au début du fichier de contribution. On trouve que cette petite description décrit 
un environnement de développement "user-friendly", ce qui va encourager les développeurs a contribué à notre projet davantage et ainsi avoir
une meilleur communauté dans notre projet.

Ensuite, on trouve que ce template est extrêmement bien réparti, on découpe bien la section Issue, la section Pull request, 
on indique bien comment un développeur peut demande de l'aide ainsi qu'on mentionne clairement que chaque contributeur doit accepter les critères de notre code de conduite.


Finalement, le processus des pull request et des issues est simple, bien défini et respecte bien les
techniques et normes que nous avons vus dans la matière du cours. Nous avons également modifié un peu ces parties pour que les critères 
que nous avons établis en équipe pour notre projet soient bien mentionnés dans la section pull-request et issue.


## Rétrospective finale

### Décrivez 2 problématiques que possèdent votre processus et développer 2 plans distincts afin de les résoudres. Soyez constructifs dans vos critiques et évitez de mettre la faute sur une ou un groupe de personne en particulier.

1. La première problématique principale de notre processus était que certaines pull-requests étaient beaucoup trop longues, ce qui faisait en sorte que la review était plus longue
que désirée et que c'était donc en conséquence moins efficace pour repérer les différentes anomalies ou modification à faire dans la pull-request. 
Une manière de résoudre ce problème serait en fait de faire une meilleure division des récits utilisateurs pour que ceux-ci soient plus petits, tout en restant indépendants les uns des autres.

2. Comme notre processus contient une règlementation très stricte pour nos noms de branches ainsi que nos noms de commits, il est arrivé  à quelques reprises
que nous ayons fait une erreur de syntaxe lors de l'écriture des différentes branches de notre projet. Pour résoudre cette problématique, il aurait été bien de 
mettre un prehook commit qui vérifie que le nom de la branche est conforme à nos critères de nom de branches avant de push la branche à notre base de code.

   
### Décrivez la démarche que vous aviez entrepris afin d'intégrer de nouveaux outils technologiques. Quelles étaient les étapes du processus? Comment avez-vous réagis aux différents bogues? Exploriez-vous à l'aide de tests unitaires ou manuels? Qu'avez-vous appris suite à cette démarche?

Voici la démarche utilisée pour l'intégration de nouveaux outils technologiques à l'intérieur de notre projet

1. En premier lieu, on s'assurait de bien comprendre qu'elle était le problème qu'on voulait résoudre avec l'outil ( Outils pour qualité de tests, sécurité, etc.. )

2. Ensuite, on faisait une analyse des différents outils disponibles pour combler nos besoins.

3. Après une lecture des différents outils, on faisait le choix d'un outil selon lui qui semblait le mieux adapté pour nos besoins tout en restant simple.

4. Après l'outil choisi, on lisait la documentation pour bien comprendre l'utilisation globale de l'outil en question. 

5. Lors d'une bonne compréhension de l'outil, on se créait une branche de "test" et on commençait l'installation ainsi que l'intégration de notre outil.

6. Pour s'assurer que notre outil marchait bien, on faisait des tests manuels pour voir si l'outil générait les bonnes choses.

7. Une fois l'outil intégré dans notre application, on intégrait celui-ci pour que l'outil fasse son analyse lors du déclenchement de notre CI, bref on s'assurait que l'outil
était bien integré avec notre CI.

8. Une fois l'outil intégré avec le CI, on créait la pull request avec une explication claire de l'outil et et des spécifications de son utilisation.

Cette technique nous a été très utile pour l'intégration des différents outils de notre projet. Nous n’avons rencontré aucun bugs lors de l'intégration des outils
et n’avons rencontré aucun problème grâce à cette technique qui nous encourageait à bien comprendre l'outil avant de faire son intégration dans notre code.

Nous avons donc appris qu'il est toujours mieux de bien comprendre un logiciel avant de faire son utilisation, cela permet d'éviter de passer du temps 
à "debugger" par manque de connaissances ou par mauvaise implémentation.

### Quels sont les bons coups de votre équipe? De quelles parties êtes-vous fiers? Nommez-en 3.

- Un premier bon coup de notre équipe est que nous avons vraiment bien détaillé les différents récits utilisateurs à faire pour chaque livrable.
Nous nous sommes assuré de suivre les recommandations de la matière du cours pour la création de nos récits utilisateurs, soit que celle-ci soit indépendantes, testables,
fermables ainsi que petits pour pas qu'elle ne soit pas trop longue à implémenté. Le fait de bien faire les récits utilisateurs à grandement faciliter la tâche des
développeurs de notre équipe, car chacun d'entre nous savait ce qu'il devait faire lorsqu'il commençait l'implémentation d'un récit utilisateur.

- Un deuxième bon coup de notre équipe est que les reviews dans nos pull-requests était très efficaces et instructifs. 
En effet, les reviewers de notre équipe n'essayaient pas simplement de corriger les erreurs de syntaxe du code, mais on essayait de 
voir au-delà des lignes de code et essayer de réfléchir sur l'architecture qui a été développée dans la pull request et ainsi s'assurer que 
le code respectait bien les principes Solid-t et les lignes directrices du Clean code.

- Finalement, notre troisième bon coup de notre équipe est que nous avons établi une architecture logicielle limitant les effets d'avalanches des changements
dès la première itération. Il était ainsi beaucoup plus facile d'implémenter les nouvelles fonctionnalités dans les livrables suivants.


### Quel conseil donneriez-vous aux prochains étudiants qui doivent faire ce projet?

On conseillerait aux prochains étudiants de faire une rencontre au début de chaque livrable avec toutes les membres de l'équipe
pour bien faire la planification de leur itération sur github. 
En effet, on leur conseille principalement de bien faire la séparation de tous les récits utilisateurs dès le début de l'itération
et de s'assurer que les récits utilisateurs soient implémentés selon les bons critères vus dans la matière du cours
pour que tout soit clair pour les développeurs et que les tests d'acceptions soient bien définis ainsi que les détails.

### Quels apprentissages, trucs ou techniques appris dans ce projet croyez-vous pouvoir utiliser plus tard? Décrivez-en au moins 2. Cela peut être des apprentissages techniques, pratiques, sur le travail d'équipe ou encore par rapport au processus.

1. La première technique que nous avons appris que nous croyons utiliser plus tard est toute la planification du travail 
à l'aide de github, soit à l'aide de création de projet, d'issue ainsi que de milestones. L'utilisation de cette technique nous a permis d'avoir
un meilleur suivi de l'avancement du projet et nous a permis de bien clarifier chaque tâche qui devait être implémentée lors des itérations.

2. Le deuxième truc que nous avons appris que nous croyons fortement utilisé plus tard est l'utilisation d'outils permettant l'automatisation
des déploiements et des tests, soit principalement l'utilisation d'un CI et d'un CD. En effet, le fait d'intégrer un CD dans notre code
nous a permis de déployer automatiquement lors de l'ajout d'une nouvelle fonctionnalité ou d'un autre changement, ce qui simplifie grandement le processus des déploiements.

Aussi, l'ajout d'un CI nous a permis de savoir si l'ajout d'un changement amenait des bris dans notre code à l'aide de l'automatisation 
des tests dans le CI. Cela était donc très utile pour savoir ou les nouveaux bugs se trouvaient. 
Le CI nous permettait aussi de vérifier que le formatage du nouveau code respectait bien les standards établis dans notre projet.

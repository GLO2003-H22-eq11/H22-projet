# TP4

## Outils de métriques

### Outil d'analyse de la qualité du code

Nous avons utilisé SonarCube qui s'intègre directement avec notre CI à l'aide de SonarCLoud.

![image](https://user-images.githubusercontent.com/47373969/164241804-8f8c8a00-a704-49e3-9883-1726a9f633e0.png)


![image](https://user-images.githubusercontent.com/47373969/164241716-9594923e-fb12-4b95-b214-d7b009495c1a.png)

![image](https://user-images.githubusercontent.com/47373969/164242012-ae674c32-e295-40c1-996c-e9e2ca952b0f.png)


### Outil de détection des failles de sécurités 

Pour analyser la sécurité des dépendances externes, nous avons utilisés le DependacyBot qui s'intègre directement avec notre CI à l'aide du fichier `dependabot.yml`. Nous avons également encore fait utilisation de Sonarcloud pour détecter nos vulnérabilités et nos risques de sécurités, ou nous avons eu une cote de sécurité avec la note de A.

Dependancy bot :

![image](https://user-images.githubusercontent.com/47373969/164242316-8c51eee4-c7a2-4720-b354-61c3fd07c5b8.png)

![image](https://user-images.githubusercontent.com/47373969/164242364-298fca96-9d52-410d-8c52-6db822bdb6f5.png)

![image](https://user-images.githubusercontent.com/47373969/164242530-7ea13155-690e-482b-908f-abb19cc869f4.png)

Sonarcloud :

![image](https://user-images.githubusercontent.com/47373969/164267759-36466968-1fe7-4ae6-878f-df1b771cd969.png)

![image](https://user-images.githubusercontent.com/47373969/164242714-07a4357c-20fb-4456-ab56-ba40e9befa76.png)




### Outil de mesure du test coverage

Encore une fois, l'outil Sonarcloud nous donne notre test coverage globale.

![image](https://user-images.githubusercontent.com/47373969/164243096-fd068acd-a7f1-4461-91c6-fe419633876f.png)


 Nous avons également utilisé la librarie Jacoco pour faire une meilleure analyse globale de nos tests qui lui génère un nouveau rapport avec le CI.


![image](https://user-images.githubusercontent.com/47373969/164244113-93c1cbf9-6ffd-492c-8137-4856e058e511.png)

![image](https://user-images.githubusercontent.com/47373969/164244326-43eba087-29b0-4bd0-a2af-c6b5fcc71852.png)

![image](https://user-images.githubusercontent.com/47373969/164244404-225a0408-20fd-48f8-a51a-79fff84b3ebc.png)


## Open source


### Préparation 

#### 1.Nommez 3 avantages à contribuer à des projets open source en tant qu'entreprise et justifiez en quoi cela peut être bénéfique pour tous.

#### 3 avantages à contribuer à des projets open source en tant qu'entreprise 

Le première avantage que nous avons discuté est qu'une entreprise fesant utilisation d'un logiciel open source a la possibilité 
d'embaucher un développeur logiciel pour qu'il puisse apporter des améliorations personnalisées au logiciel plutôt que de se fier aux décisions d'un 
fournissuer d'une source privée ou la contribution externe n'est pas possible.

Le deuxième avantage que nous avons discuté en équipe est qu'une entreprise qui contribue à des projets open source peut cibler des développeurs 
contribuant habilement au logiciel open source et ainsi potentiellement leur offrir un emploi dans la compagnie. Contribuez au projet open source peut
donc être une certaine manière de recruter des développeurs logiciels compétants.

Le troisième avantage est de réduire le coût total d'utilisation des produits logiciels de l'entreprise. En effet, ce qui rend les projets open source
unique est qu'un organisation peut utiliser le projet open source comme bon lui semble sans payez de frais d'utilisations ou de frais de propriété
intellectuelle. Une entreprise peut donc faire l'utilisation d'un projet open source plutôt de payer un produit à frais élevée.

#### Justifiez en quoi cela peut être bénéfique pour tous
La contribution des compagnies est bénéfique pour tous étant donnée qu'une même compagnie regroupe plusieurs développeurs, donc si une compagnie décide de contribuer aux projets open
 source, cela veut dire que plusieurs développeurs vont contribuer. Cela fait donc en sorte que la contribution des compagnies 
dans le domaine de l'open source aggrandie grandement la communauté de l'open source. Aussi, les grosses organisations technologiques ont énormement de pouvoir sur les différents
mouvements technologiques, donc si les grosses compagnies comme Microsoft, Adobe décident de se tourner au open source, cela va grandement contribuer à l'avance
de l'open source ainsi qu'à ses développements. Il aura donc plus de logiciels open source disponible pour nous tous.

Aussi, si c'est une compagnie contribue fortement à un projet open source, un développeur peut se faire remarquer par la compagnie en apportant des 
contributions importantes au projet. Cela peut donc devenir un potentiel futur emploie pour le développeur contribuant au projet open source.


#### 2. Décrivez 3 défis qu'impose la mise en place d'un projet open source et justifiez.

1. Un premier défi est d'avoir une bonne définition des objectifs du projet à l'aide des divers outils de documentation 
lors de la mise en place du produit pour que les futurs contributeurs comprennent bien le vision du projet 
et ainsi pour qu'ils puissent apporter des contributions en lien avec le but du projet.

2. Un autre défi lors de la mise en place d'un projet open source est de savoir dire non aux contribueurs apportant des contributions éloignant le projet
de sa raison d'être. Un projet open source va éventuellement avoir de nombreuses contributions externes, et certaines contributions ne correspondront pas aux critères du projet. 
Un défi sera alors de cibler ses contributions et ensuite politement expliquer la situation au contributeur pour lui mentionner la raison qui amène le rejet de
sa contribution.

3. Le dernier défi lors de la mise en place d'un projet open source est l'acceptation d'une licence commune pour que les contributeurs sachent 
comment ils peuvent contribuer au projet. En effet, chaque projet logiciel open source doit avoir une licence quelqconque car c'est 
cette licence qui définit les politques de contributions et les méthodes d'utilisation du logiciel. On peut donc mentionner qu'un défi lors de la mise en place
d'un projet open source est de bien choisir la licence pour que les contributeurs ont les droits de contributions et d'utilisation selon les désirs 
du créateur.


#### 3. Quelle information vous a-t-elle le plus surprise à propos de l'open source?

Plusieurs informations nous ont grandement surpris lors de l'apprentissage de l'open source. Tout d'abord, nous avons été très surpris de voir que 
n'importe qui a les droits de contribuer au projet open source comme bon il le semble. Nous pensions que c'était seulement une certaine catégorie 
de développeur qui pouvait contribuer ou bien qu'il fallait avoir un "rôle" dans le projet open source en question pour y contribuer.

Sur un autre niveau, ce qui nous a le plus surpris est que certaines des plus grosses compagnies au monde comme IBM, Adobe, SAP participe activement dans la communauté de l'open source. 
Aussi, de voir que même des compagnies comme Microsoft, qui a la base s'opposait contre les avancements de l'open source, sont rendu à mettre la majortié de leur principal 
logiciel en open source.

### Éxecution

#### Créer un fichier pour le "Code of conduct" en suivant les meilleures pratique. Si vous utilisez un template, vous devez en citer la source et expliquer pourquoi vous l'avez utilisé.

#### Créer un fichier pour la licence en suivant les meilleures pratiques. Indiquez les 3 raisons principales (dans le fichier d'exercice) pour lesquelles vous avez choisi cette licence par rapport aux autres.

Nous avons décidé de choisir la license MIT.



#### Créer un fichier pour la contribution décrivant les meilleures pratiques de collaboration et de développement que vous voulez encourager au sein du projet. Si vous utilisez un template, vous devez en citer la source et expliquer pourquoi vous l'avez utilisé.


#### Mettre à jour le README afin de décrire le projet, mettre en lien les différents fichiers pour l'open source, et ajouter les badges pour les pipelines CI (voir la documentation sur Github).



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




########################################################################################################################
IFT605 - TP3
########################################################################################################################

Membres de l'équipe:
    Michaël Beaulieu        -      13 048 132
    Bruno-Pier Touchette    -      13 045 732
    Benoit Jeunehomme       -      13 055 392

########################################################################################################################
Description
########################################################################################################################

Le projet en son entièreté utilise MAVEN, tant pour sa compilation que pour son exécution.
Le fichier pom.xml contient la description des pofils utilisés pour la compilation et l'exécution
de l'application.
Plusieurs fichiers de ressources sont utilisés, exprimant les paramètres d'exécution du programme.
Voici la liste des fichiers de propriétés utilisées et localisés dans le chemin d'accès:
 <projet-dir>\src\main\ressources\jade-agent-container.properties
                                  jade-genetic-agent-container.properties
                                  jade-main-container.properties
                                  jade-sinus-agent-container.properties

Le fichier jade-main-container décrit comment lancer la plateforme JADE.
Les autres fichiers contiennent la description des agents qui doivent être
lancés sur la plateforme main, dans un conteneur externe.

Le fichier jade-agent-container.properties permet de lancer la liste des agents utilisés pour la partie 2 du TP. Il
permet de lancer le client, l'agent de contrôle des messages et les agents de dérivations requis par le TP.

Le fichier jade-sinus-agent-container.properties contient la description d'un agent de dérivation d'équation
sinusoidale.

Finalement, le fichier jade-genetic-agent-container.properties permet de lancer les agents avec apprentissage de la
partie 3.

########################################################################################################################
Compilation et exécution
########################################################################################################################

Tel qui a été précisé dans la description du projet, l'utilisation de MAVEN est requis pour le fonctionnement de
l'application.
Voici les commandes utilisées pour lancer l'application et la plateforme JADE:

mvn compile -P jade-main

Pour les autres profile du fichier pom.xml, (jade-agent, jade-agent-sinus, jade-agent-genetic), vous n'avez qu'a
remplacer la valeur du parametre -P de la commande.

Avant d'être lancé, un profil doit être compiler. Donc, tous les profils doivent avoir été préalablement compilé.

Pour lancer le programme, le but "compile" doit être changé par "exec:java" tel que

mvn exec:java -P jade-main

De façon similaire, vous devez utilisé cette commande pour lancer les autres profils.
Avant de lancer les agents, le profil jade-main doit être lancé et en fonction. Les agents ne pourront autrement pas
être ajouté à la plateforme.


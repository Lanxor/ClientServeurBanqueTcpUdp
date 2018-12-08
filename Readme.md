# Présentation

Une application répartie permettant de gérer des comptes bancaires.

Le serveur de la banque implémante les requêtes suivante :
 * CREATE id number : Permet de demander la création d'un compte identifié par 'id' sur lequel sera placé la somme 'number'.
 * GET id : Permet d'obtenir la position courante du compte identifié par 'id'.
 * ADD id number : Ajoute une somme 'nombre' sur le compte identifié par 'id'.
 * RMEOVE id number : Retire une somme 'nombre' sur le compte identifié par 'id'.

Le client recevra les réponses du serveur sous la forme suivantes :
 * OK : Informe le client que la demande s'est correctement déroulée.
 * ERROR description : La raison de l'échec de la commande sous forme de chaîne de caractères.
 * SOLDE number lastOperation : Envoie au client le solde courant du compte et la date de la dernière opération.

# Utilisation

Compilation :

```
$ make					# Compile le projet en entier.
```

## Serveur

Le serveur exécute un serveur TCP et un serveur UDP.

Exécution :

```
$ java view.Server			# Exécute le programme serveur.
```

## Clients

### Clients TCP

Exécution :

```
$ java view.ClientTcp addr port		# Exécute le programme client.
```

### Clients UDP

Exécution :

```
$ java view.ClientUdp addr port		# Exécute le programme client.
```

# Exemple d'utilisation

Voici un exemple d'un client/serveur.

## Côté serveur :

```
$ java view.Server
main : New Thread Server TCP
main : New Thread Server UDP
Thread-0 is the thread of server TCP.
Thread-1 is the thread of server UDP.
Thread-0 : New Thread TCP
Thread-2 - QUERY - create myAccount 0
Thread-2 - RESPONSE - OK Sat Dec 08 21:48:20 CET 2018
Thread-2 - QUERY - get myAccount
Thread-2 - RESPONSE - SOLDE 0.0 Sat Dec 08 21:48:20 CET 2018
Thread-2 - QUERY - add myAccount 10
Thread-2 - RESPONSE - OK Sat Dec 08 21:48:36 CET 2018
Thread-2 - QUERY - get myAccount
Thread-2 - RESPONSE - SOLDE 10.0 Sat Dec 08 21:48:36 CET 2018
Thread-2 - QUERY - command error
Thread-2 - RESPONSE - ERROR Unknow command - Available command CREATE, GET, ADD, REMOVE

Thread-2 : Close tcp connexion

```

## Côté client :

```
$ java view.ClientTCP localhost 12234
Send an empty message to close connexion.
Your command : create myAccount 0
OK Sat Dec 08 21:48:20 CET 2018
Your command : get myAccount
SOLDE 0.0 Sat Dec 08 21:48:20 CET 2018
Your command : add myAccount 10
OK Sat Dec 08 21:48:36 CET 2018
Your command : get myAccount
SOLDE 10.0 Sat Dec 08 21:48:36 CET 2018
Your command : command error
ERROR Unknow command - Available command CREATE, GET, ADD, REMOVE
Your command :
```

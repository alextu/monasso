# Elements de conception
---
### Identités
Chaque personne référencée par le système, qu'elle soit physique ou morale, est représentée par une identité.
Une identité porte toujours des informations de base (nom, prénom, adresse principale, numéro de téléphone, etc...) ainsi qu'un indicateur pour différencier une personne physique d'une personne morale.

### Rôles
Afin de spécialiser une identité, on peut (doit?) lui associer un ou plusieurs rôles. Exemple de rôles qui pourraient exister : Adhérent, Responsable de section, Bénévole, Président, Trésorier, Secrétaire Général, Fournisseur, Contact...
Chaque rôle peut potentiellement enrichir l'identité associée avec des informations supplémentaires. Par exemple, un rôle Fournisseur peut apporter un numéro de compte sur lequel seront fait les payements.

### Utilisateurs
Un utilisateur représente une personne ayant le droit de se connecter à l'application. L'utilisateur est caractérisé par un identifiant, un mot de passe, des droits de base (superviseur ou non, par exemple), et est obligatoirement associé à une identité.
Il est possible d'appliquer des droits à un utilisateur.

### Groupes d'utilisateurs
Rassemble les utilisateurs dans des emsembles logiques, sur lesquels on peut appliquer des droits. Ceci permet de ne pas avoir à gérer les droits individuellement pour chaque utilisateur. Les droits effectifs d'un utilisateur sont déterminé par les droits de son ou ses groupes, plus les éventuels droits spécifiques à cet utilisateur.

### Modules
Un module est une partie de l'application, potentiellement soumise à licence. Un module n'est visible/actif que si une licence correspondante est présente dans le système.

### Droits
Les droits sont des ensembles de rêgles, applicables sur des utilisateurs ou des groupes d'utilisateurs, et qui définissent quelles actions (Création, Lecture, Mise à jour, Suppression, Impression?) sont autorisées sur les différents modules de l'application. En l'abscence de droit sur un module, un utilisateur n'y a pas accès, même si le module est soumis à licence et qu'une licence valide est présente.

### Licences
Les licences sont sont des fichiers contenant l'identifiant du module correspondant, ainsi que les dates de début et de fin de validité. Ces fichiers sont signés avec la clé privée de l'éditeur, et cryptés avec la clé publique du client. Les fichiers de licences sont importés dans l'applicationh, décryptés par le système, puis la signature est vérifiée.
Les versions cryptées/signées de ces fichiers sont stockées en base, et vérifiées à chaque fois que le système à besoin d'accéder à un module, par exemple pour déterminer si l'entrée de menu ou l'icône du module doit être affiché à l'écran.

### Clés
Le client pourra utiliser ses propres clés (générées avec ssh-keygen par exemple) ou laisser l'application générer une paire de clés. La clé publique de l'éditeur pourra être récupérée via un webservice, ou via importation (dans le cas ou il n'y pas de connexion internet par exemple, la clé peut être ammenée sur une clé USB).


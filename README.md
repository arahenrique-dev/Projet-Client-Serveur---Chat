# Projet Chat Client-Serveur (Java)

Application de chat en Java utilisant sockets TCP et un serveur multithread.

Chaque client se connecte, s'authentifie via un fichier users.txt, puis rejoint le canal de discussion.

C'est un système pour comprendre la communication réseau et le multithreading en Java, facilement testable avec netcat.

- Pour lancer le programe, dans un terminal:
    javac ChatServer.java
    java ChatServer

- Dans un nouveau terminal (ou plusieurs):
    nc localhost 1234
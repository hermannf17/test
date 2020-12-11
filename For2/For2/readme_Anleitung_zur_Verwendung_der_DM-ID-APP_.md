# Anleitung zur Verwendung der DM-ID-APP
Die DM-ID-App ist eine Client/Server Applikation zum Dokumentenmanagement, welche das Hoch- und Herunterladen, sowie das Auflisten dieser Dokumente von zwei Benutzern (Daniel und Irem) via HTTP-Kommunikation ermöglicht. Zur Benutzerfreundlichkeit läuft die App auf eine Gui-Oberfläche, um die Interaktionen durch Klicken zu ermöglichen.


## Requirements
Damit das Projekt auch funktioniert, wird eine Datenbank benötigt. In unserem Fall wurde eine MySQL Datenbank erstellt, der den JDBC Driver verwendet.

#### Schritt 1: Erzeugung der SQL Datenbank:
Der benötigte JDBC Driver für die Datenbank liegt im Ordner "Packages" des Programmes. Diese Jar-Datei muss nun zum Projek hinzugefügt werden. 
```shell=
(Rechtsklick auf ) DM-ID-APP -> Properties -> Reiter "Libraries" -> bei Classpath "Add External JARs" auswählen -> "mysql-connector-java-8.0.16.jar" auswählen (im Projekt unter "Packages")
```
Das Projekt verwendet einen MySQL der von XAMPP bereitgestellt wird. Somit muss im nächsten Schritt auf dem Server XAMPP heruntergeladne werden und installiert werden, damit auf dem Server eine Datenbank erstellt werden kann. Unter diesen Link kann man einfach XAMPP installieren und starten:
```shell=
https://vitux.com/how-to-install-xampp-on-your-ubuntu-18-04-lts-system/
```
Für den Start des MySQL Servers wird dieser Befehl benötigt:
(Wichtig!: "sudo apt install net-tools" muss durchgeführt werden, bevor der MySQL Server gestartet werden kann)
```shell=
sudo /opt/lampp/lampp start
```

#### Anleitung für den Server
Wurden alle Schritte wie oben beschrieben durchgeführt, so kann nun der Server ausgeführt werden. Dieser befindet sich unter:
```shell=
(Projekt) DM-ID-APP --> (Package) server --> (Klasse) httpserver.java
```
Wenn der Server gestartet wird, wird automatisch die Datenbank und die Tabellen "Documents" und "Users" erstellt. Des Weiteren werden die User "Daniel" und "Irem" erstellt, die später für das Anmelden benutzt werden (siehe unten).
**WICHTIG:** Für das Starten des Programmes wird die IP-Adresse des Servers benötigt. Deswegen muss die IP-Adresse des Computers bzw. der VM herausgefunden werden. Dies kann mittels "ifconfig" im Terminal erfolgen. 
**Werden zwei VMs verwendet so muss unter den Netzwerkeinstellungen der virtuellen Maschine bei beiden VMs unter "Netzwerk" die "Netzwerkbrücke" ausgewählt werden, damit die richtige IP-Adresse gewählt werden kann**


#### Anleitung für den Client
Auf der anderen VM muss nun die Klasse "httpclient.java" ausgeführt werden, damit das Programm gestartet werden kann.
```shell=
(Projekt) DM-ID-APP --> (Package) client --> (Klasse) httpclient.java
```
Wird die "httpclient.java" ausgeführt so öffnet sich nun ein Login-Fenster. Somit sind die grundlegenden Sachen eingestellt und einsatzbereit.


**Wie funktioniert die DM-ID-APP:**
```*Login*```
Die DM-ID-App hat zwei vordefinierte Benutzer, die sich über ein Fenster anmelden. Diese Benutzer werden automatisch erstellt wenn das Programm ausgeführt wird (wie oben beschrieben). Es gibt daher keine Registrierung.

**User1:**
```*Username: Daniel*```
```*Passwort: test99*```
**User2:**
```*Username: Irem*```
```*Passwort: test2*```

Für die Anmeldung wird die IP-Adresse des Servers benötigt. Im Feld "Server-IP" muss nun die herausgefundene IP des Servers eingetippt werden. Das Format lautet:
```shell=
http://<server-ip>:8000
```
**Hinweis:** Statt "<server-ip>" muss die Adresse eures Servers eingegeben werden (```*http://*``` und der Port ```*:8000*``` muss zu der IP hinzugefügt werden)

![](https://i.imgur.com/DnyKKGk.png)
Hier gibt der Benutzer (Daniel oder Irem) sein Server-Ip (in diesem Fall war es noch der Localhost), Benutzernamen und Kennwort ein. Und danach öffnet sich das eigentliche Fenster zum Dokumentenmanagement. 

```*Upload*```
Nun kann der Benutzer mittels Upload-Button eine Textdatei in seinen Fileserver hochladen. 
![](https://i.imgur.com/UlFkqZ9.png)
Nach erfolgreichem Upload ist diese Datei auch ersichtlich.
![](https://i.imgur.com/3F7RpWF.png)

```*Download*```
Die hochgeladenen Dateien können nun auch heruntergeladen werden. Dafür muss der Benutzer die gewollte Datei anklicken und den Pfad bestimmen und einen neuen Namen angeben. 
**WICHTIG:** Es sind nur txt-Dateien erlaubt. Die Endung des Files muss daher auf ```*.txt*``` enden
![](https://i.imgur.com/SRDUxYv.png)

```*Auflisten*```
Alle Dateien, die hochgeladen wurden, sind nun in Gui ersichtlich.
![](https://i.imgur.com/UlFkqZ9.png)

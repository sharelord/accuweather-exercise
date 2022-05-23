# Accuweather-app
Coding exercise given by **Testvagrant Technologies**


# Getting Started

## Set up the Test Environment

This application will work on any computer running Windows, macOS or Linux.

## Prerequisites

You will need to ensure the following prerequisites are satisfied before running this application.
Detailed instructions are offered here, but you do not need to follow them if you have satisfied the requirements in another way.

* [Download and install git](https://git-scm.com/downloads)
* Install JAVA
    * Download [JDK](https://www.oracle.com/java/technologies/javase-downloads.html) - any latest should be good
    * Install JDK and set the JAVA_HOME environment
        * Windows users should add it in [System variables](https://javatutorial.net/set-java-home-windows-10)
        * macOS for versions < 10.15 should set it in [bash_profile](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux#2-single-user---mac-os-x-older-versions) file
        * macOS for versions = 10.15 should set it in [bash_profile](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux#1-single-user---mac-os-x-105-or-newer) file
        * macOS for versions >= 11.00 should set it in [zshrc](https://mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/#java-home-and-macos-11-big-sur) file in the same way as for **bash_profile**
        * linux user should set it in [bashrc](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux#1-single-user) file

## Web app info
App URL - https://www.accuweather.com/

App Key - 7fe67bf08c80ded756e598d6f8fedaea
* API End Point Used-
  * Get City latitude and longitude: http://api.openweathermap.org/geo/1.0/direct?q=Pune,IN&limit=10&appid=7fe67bf08c80ded756e598d6f8fedaea
  * Get the Temperature - https://api.openweathermap.org/data/2.5/weather?lat=18.521428&lon=73.8544541&appid=7fe67bf08c80ded756e598d6f8fedaea

## Steps to run the test:
 * `git clone https://github.com/sharelord/accuweather-exercise.git`
 * Take a checkout of https://github.com/sharelord/accuweather-exercise/tree/test-branch
 * Go to src/test/java/scripts/HomeScript.java and Run the test OR
 * Open the terminal and type `mvn test`

## Reporting
* User can open the `ExtentReport.html` generated after the run

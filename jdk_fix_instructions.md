# JDK Version Mismatch Fix Instructions

## Problem Identified
The project is configured to use Java 17, but your system has Java 23.0.2 installed. This version mismatch is likely causing the red highlighting of annotations and imports in your IntelliJ IDEA project.

## Solution Steps

### Option 1: Install Java 17 and Configure IntelliJ IDEA to Use It

1. **Download and Install Java 17**:
   - Download Java 17 JDK from the Oracle website or use a package manager
   - Install it on your system
   - Make sure to remember the installation path

2. **Configure IntelliJ IDEA to Use Java 17**:
   - Open IntelliJ IDEA
   - Go to File → Project Structure (Ctrl+Alt+Shift+S)
   - Under Project Settings → Project:
     - Set Project SDK to the Java 17 installation
     - Set Project language level to "17 - Sealed types, always-strict floating-point semantics"
   - Click Apply and OK

3. **Reload Maven Project**:
   - Right-click on the pom.xml file in the Project Explorer
   - Select Maven → Reload Project
   - Wait for IntelliJ IDEA to reload the project and download dependencies

### Option 2: Update Project to Use Java 23

Alternatively, you can update the project to use Java 23 instead of Java 17:

1. **Update pom.xml**:
   - Open pom.xml
   - Change `<java.version>17</java.version>` to `<java.version>23</java.version>`

2. **Update IntelliJ IDEA Configuration**:
   - Open File → Project Structure (Ctrl+Alt+Shift+S)
   - Under Project Settings → Project:
     - Set Project language level to "23 - Text blocks"
   - Click Apply and OK

3. **Update compiler.xml**:
   - Open .idea/compiler.xml
   - Change `<module name="fullstack-backend" target="17" />` to `<module name="fullstack-backend" target="23" />`

4. **Update misc.xml**:
   - Open .idea/misc.xml
   - Change `<component name="ProjectRootManager" version="2" languageLevel="JDK_17" project-jdk-name="17" project-jdk-type="JavaSDK" />` to `<component name="ProjectRootManager" version="2" languageLevel="JDK_23" project-jdk-name="23" project-jdk-type="JavaSDK" />`

5. **Reload Maven Project**:
   - Right-click on the pom.xml file in the Project Explorer
   - Select Maven → Reload Project
   - Wait for IntelliJ IDEA to reload the project and download dependencies

### Option 3: Use the Maven Wrapper with Java 17

If you don't want to install Java 17 or update the project to use Java 23, you can use the Maven wrapper with Java 17:

1. **Configure Maven Wrapper to Use Java 17**:
   - Open a terminal in the project directory
   - Run: `./mvnw -Djava.version=17 clean install`
   - This will download and use Java 17 for the Maven build

2. **Reload Maven Project**:
   - Right-click on the pom.xml file in the Project Explorer
   - Select Maven → Reload Project
   - Wait for IntelliJ IDEA to reload the project and download dependencies

## Additional Troubleshooting

If the issue persists after trying the above solutions:

1. **Invalidate Caches and Restart**:
   - Go to File → Invalidate Caches / Restart
   - Select "Invalidate and Restart"
   - Wait for IntelliJ IDEA to restart and reindex the project

2. **Check for Missing Libraries**:
   - The .idea/libraries directory is missing, which is unusual for a Maven project
   - After reloading the Maven project, this directory should be created automatically
   - If it's still missing, try reimporting the project as a Maven project

3. **Build the Project**:
   - The target directory is missing, indicating that the project has not been built
   - Build the project using Maven: Run → Edit Configurations → Add New Configuration → Maven
   - Set the command line to: `clean install`
   - Run the configuration
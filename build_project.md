# Building the Maven Project in IntelliJ IDEA

## Problem
The project is experiencing an issue where all annotations and imports are showing as red in the IntelliJ IDEA editor. This is likely because the Maven project hasn't been built yet, and the dependencies haven't been properly downloaded and resolved.

## Solution

### 1. Build the Project Using IntelliJ IDEA's Maven Integration

1. **Open the Maven Tool Window**:
   - Click on the "Maven" tab on the right side of the IntelliJ IDEA window
   - If you don't see the Maven tab, go to View → Tool Windows → Maven

2. **Reload the Maven Project**:
   - Right-click on the project in the Maven tool window
   - Select "Reload Project"
   - Wait for IntelliJ IDEA to reload the project and download dependencies

3. **Clean and Install the Project**:
   - Expand the "Lifecycle" folder in the Maven tool window
   - Double-click on "clean" to clean the project
   - Double-click on "install" to build and install the project
   - Wait for the build to complete

### 2. Alternative: Build from Command Line

If the Maven tool window approach doesn't work, you can try building from the command line:

1. **Open a Command Prompt**:
   - Press Win+R, type "cmd", and press Enter
   - Navigate to your project directory:
     ```
     cd "C:\Users\DT User\Desktop\Coefficients-backend"
     ```

2. **Run Maven Commands**:
   - If you have Maven installed globally:
     ```
     mvn clean install
     ```
   - If you don't have Maven installed globally, use the wrapper (with quotes to handle spaces in the path):
     ```
     ".\mvnw" clean install
     ```

### 3. Invalidate Caches and Restart IntelliJ IDEA

If the issue persists after building the project:

1. Go to File → Invalidate Caches / Restart
2. Select "Invalidate and Restart"
3. Wait for IntelliJ IDEA to restart and reindex the project

## Why This Fixes the Issue

Building the Maven project:
1. Downloads all required dependencies
2. Creates the necessary class files and resources
3. Generates any required source code
4. Creates the target directory with compiled classes

Once the project is built, IntelliJ IDEA will be able to properly resolve all dependencies, and the red highlighting on imports and annotations should disappear.

## Additional Troubleshooting

If the issue persists after trying all the above solutions:

1. **Check Java Installation**:
   - Make sure Java 23 is properly installed and configured
   - Run `java -version` in a command prompt to verify

2. **Check Maven Settings**:
   - Go to File → Settings → Build, Execution, Deployment → Build Tools → Maven
   - Make sure Maven home directory is correctly set
   - Check that JDK for importer is set to Java 23

3. **Check Project Structure**:
   - Go to File → Project Structure
   - Under Project Settings → Project:
     - Make sure Project SDK is set to Java 23
     - Make sure Project language level is set to "23 - Text blocks"
   - Under Project Settings → Modules:
     - Make sure the Language level is set to "23 - Text blocks"
     - Make sure the Module SDK is set to Java 23
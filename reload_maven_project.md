# Reload Maven Project in IntelliJ IDEA

Follow these steps to properly reload the Maven project in IntelliJ IDEA:

1. Open IntelliJ IDEA and load the Coefficients-backend project
2. Right-click on the `pom.xml` file in the Project Explorer
3. Select "Add as Maven Project" from the context menu
4. Wait for IntelliJ IDEA to import the Maven project and download dependencies
5. If prompted, select "Load Maven Changes" or "Import Changes"
6. After the import is complete, check if the red highlighting on annotations is gone

## Alternative Method

If the above method doesn't work, try the following:

1. Open IntelliJ IDEA and load the Coefficients-backend project
2. Go to File → Settings → Build, Execution, Deployment → Build Tools → Maven
3. Make sure "Import Maven projects automatically" is checked
4. Click "Apply" and "OK"
5. Right-click on the project in the Project Explorer
6. Select "Maven" → "Reload Project"
7. Wait for IntelliJ IDEA to reload the project and download dependencies
8. After the reload is complete, check if the red highlighting on annotations is gone

## If Issues Persist

If the issues persist after trying both methods, try the following:

1. Go to File → Invalidate Caches / Restart
2. Select "Invalidate and Restart"
3. After IntelliJ IDEA restarts, wait for it to reindex the project
4. Check if the red highlighting on annotations is gone
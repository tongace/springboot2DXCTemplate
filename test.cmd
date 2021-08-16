.\mvnw clean install -DskipTests & ^
cd webDemo & ^
.\mvnw test & ^
.\mvnw site -DgenerateReports=false & ^
.\mvnw jacoco:report & ^
.\mvnw surefire-report:report-only & ^
cd ..
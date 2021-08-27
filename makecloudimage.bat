call .\gradlew clean build -PenvironmentName=cloud -x test
call docker build -t tongace/dxc-web-demo:cloud .
call docker push tongace/dxc-web-demo:cloud
exit 0
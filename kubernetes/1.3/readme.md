# Commands for building acme app on kubernetes
docker build -t gcr.io/hackathoncouchbase/viride-acme-app .
docker images
docker tag 5196f78b68ec gcr.io/hackathoncouchbase/viride-acme-app
docker push gcr.io/hackathoncouchbase/viride-acme-app

kubectl create -f spring-boot-app-secret.yaml
kubectl create -f spring-boot-app.yaml
kubectl create -f spring-boot-load-balancer.yaml
kubectl describe service spring-boot-load-balancer
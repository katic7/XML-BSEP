kreiranje slike sa nazivom (-t) i source code(.) build context-om
```
docker image build -t eureka-service .
```
provera slika
```
docker images
```
pokretanje kontejnera
``` 
docker run --detach -p 8761:8761 eureka-service:latest

```


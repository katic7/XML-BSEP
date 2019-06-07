kreiranje slike sa nazivom (-t) i source code(.) build context-om 
```
docker image build -t auth-service .
```
provera slika
```
docker images
```
pokretanje kontejnera
``` 
docker run --detach -p 8085:8085 auth-service:latest

```


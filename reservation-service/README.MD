kreiranje slike sa nazivom (-t) i source code(.) build context-om 
```
docker image build -t reservation-service .
```
provera slika
```
docker images
```
pokretanje kontejnera
``` 
docker run --detach -p 8083:8083 reservation-service:latest

```


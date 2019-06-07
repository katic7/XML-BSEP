kreiranje slike sa nazivom (-t) i source code(.) build context-om 
```
docker image build -t accommodation-service .
```
provera slika
```
docker images
```
pokretanje kontejnera
``` 
docker run --detach -p 8082:8028 accommodation-service:latest

```


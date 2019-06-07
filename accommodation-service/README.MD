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
docker run --detach -p 8091:8091 accommodation-service:latest

```


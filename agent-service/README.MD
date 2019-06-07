kreiranje slike sa nazivom (-t) i source code(.) build context-om 
```
docker image build -t agent-service .
```
provera slika
```
docker images
```
pokretanje kontejnera
``` 
docker run --detach -p 8090:8090 agent-service:latest

```


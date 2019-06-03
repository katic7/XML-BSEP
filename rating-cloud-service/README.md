Setting up cloud service

```
npm install @google/cloud-debug --save
npm install -g @google-cloud/functions-emulator
functions config set projectId reservation-cloud-service
functions start
functions deploy {{method-name}} --trigger-http

```

methods: newRating
         getAllRatings
         getSpecificRating/{id}

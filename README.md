# Dublin hackathon

During this hackathon, we have worked on the implementation of a google cloud function which will call Gemini AI to generate Logistic objet by interprating the business needs.

## Install google cloud function



### Setup the GCP function to translate text into LO




# One record server on GCP

In parallel we have started a documentation the launch one record server on a cloud function by building a docker image and pushing it in the google cloud artifact registry.

## Setup One Record server on GCP using Google run

#create a gcp project
https://console.cloud.google.com/projectcreate

#open shell 
https://console.cloud.google.com/cloudshelleditor

#check docker-compose version [we need v2+]
```bash
docker-composer -v
```

#if the result is under 2, please follow the next block, otherwise please go to 'get one record server'

#update docker compose 
```bash
sudo rm /usr/local/bin/docker-compose
sudo curl -L "https://github.com/docker/compose/releases/download/v2.33.1/docker-compose-linux-x86_64" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

#get one record server
```bash
git clone https://gitlab.com/iata-cargo/one-record-server-first-steps.git
cd /one-record-server-first-steps/docker-compose
```

#create the docker image
```bash
docker-compose build
docker tag docker-compose-ne-one-play gcr.io/iata-hackathon-dublin/docker-compose-ne-one-play:latest
gcloud auth configure-docker
docker push gcr.io/iata-hackathon-dublin/docker-compose-ne-one-play:latest
```

#instanciate google run instance
https://console.cloud.google.com/run/create

#select Artifact Registry
gcr.io/iata-hackathon-dublin/docker-compose-ne-one-play:latest

The configuration is done, it should works ! 
## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)

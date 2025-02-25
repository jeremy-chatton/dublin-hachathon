## Daft of process to generate docker image and use it as gcp run application ##

#create a gcp project
https://console.cloud.google.com/projectcreate

#open shell 
https://console.cloud.google.com/cloudshelleditor

#check docker-compose version [we need v2+]
docker-composer -v

#if the result is under 2, please follow the next block, otherwise please go to 'get one record server'

#update docker compose 
sudo rm /usr/local/bin/docker-compose
sudo curl -L "https://github.com/docker/compose/releases/download/v2.33.1/docker-compose-linux-x86_64" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

#get one record server
git clone https://gitlab.com/iata-cargo/one-record-server-first-steps.git
cd /one-record-server-first-steps/docker-compose

#create the docker image
docker-compose build
docker tag docker-compose-ne-one-play gcr.io/iata-hackathon-dublin/docker-compose-ne-one-play:latest
gcloud auth configure-docker
docker push gcr.io/iata-hackathon-dublin/docker-compose-ne-one-play:latest

#instanciate google run instance
https://console.cloud.google.com/run/create

#select Artifact Registry
gcr.io/iata-hackathon-dublin/docker-compose-ne-one-play:latest

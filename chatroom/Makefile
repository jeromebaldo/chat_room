# Variables
DOCKER_COMPOSE = docker-compose

# rebuild jar 
j_build:
	cd chat-application && mvn clean package

# Efface les conteneurs, les images, les volumes et les réseaux
d_clean:
	$(DOCKER_COMPOSE) down --rmi all --volumes --remove-orphans

# Rebuild les images
d_build:
	$(DOCKER_COMPOSE) build

# Lancer les services
d_up:
	$(DOCKER_COMPOSE) up -d

# Stopper les services
d_down:
	$(DOCKER_COMPOSE) down

# start docker compose after rebuild
sta: j_build d_build d_up

# stop docker compose and all be cleaned
sto: d_down d_clean
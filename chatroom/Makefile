# Variables
DOCKER_COMPOSE = docker-compose

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

# Nettoyage, reconstruction et démarrage des services
d_rebuild: d_clean d_build d_up

# rebuild jar 
j_build:
	cd chat-application && mvn clean package
	

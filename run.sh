#!/bin/bash

fuser -k 8080/tcp
mvn clean wildfly-swarm:run
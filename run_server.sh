#!/bin/bash
set -e
./gradlew jar
java -jar costaWeb/build/libs/costaWeb.jar db migrate conf.yaml
java -jar costaWeb/build/libs/costaWeb.jar server conf.yaml

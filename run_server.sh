#!/bin/bash
set -e
./gradlew stage
foreman start

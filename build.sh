#!/usr/bin/env bash

set -e

mvn clean
mvn package

rm -rf dist
mkdir dist
cp target/mybatis-generater-1.0.0-jar-with-dependencies.jar dist/mybatis-generater-1.0.0-jar-with-dependencies.jar
cp src/main/resources/config.properties dist/config.properties

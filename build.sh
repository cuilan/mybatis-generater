#!/usr/bin/env bash

set -e

mvn clean
mvn package

rm -rf dist
mkdir dist
cp target/mybatis-generater-1.0.0.jar dist/mybatis-generater-1.0.0.jar
cp -r target/libs dist/libs
cp src/main/resources/config.properties dist/config.properties

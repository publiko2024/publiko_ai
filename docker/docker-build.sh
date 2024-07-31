#!/bin/sh

#Setting Versions
VERSION='1.0.0'

cd ..
./gradlew clean build -x test

ROOT_PATH=$(pwd)
echo "$ROOT_PATH"

echo 'api1 docker image build... Start'
cd "$ROOT_PATH" && docker build -t ai-server:$VERSION .
echo 'api1 docker image build... Finish'
#!/bin/sh
source ~/.bashrc

pushd .
cd app
npm i
ls -larth
cd  app/main-module
npm i
popd

gradle clean
gradle build
gradle run -DDEBUG=true -t --args="-mortadelo -filemon"


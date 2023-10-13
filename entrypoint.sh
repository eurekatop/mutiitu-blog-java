#!/bin/sh
source ~/.bashrc

gradle clean
gradle build
gradle run -DDEBUG=true -t --args="-mortadelo -filemon"


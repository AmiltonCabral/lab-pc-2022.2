#!/bin/sh

mvn clean compile assembly:single

if [ $? -eq 0 ]
then
    echo "compile worked!"
fi
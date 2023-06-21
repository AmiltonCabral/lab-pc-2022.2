#!/bin/sh

mvn clean package

if [ $? -eq 0 ]
then
    echo "compile worked!"
fi
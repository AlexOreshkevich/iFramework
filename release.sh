#!/bin/bash
git tag -d $1
git push origin :refs/tags/$1
git pull origin development
mvn --batch-mode -e -X release:clean release:prepare -DreleaseVersion=$1 -DdevelopmentVersion=$2
mvn -e release:perform
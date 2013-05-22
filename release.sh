#!/bin/bash
git reset
git pull origin development
mvn --batch-mode -e release:clean release:prepare -DreleaseVersion=$1 -DdevelopmentVersion=$2
mvn --batch-mode -e release:perform
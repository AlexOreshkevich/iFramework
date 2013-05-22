#!/bin/bash
git tag -d iFramework-$1
git push origin :refs/tags/iFramework-$1
mvn --batch-mode -e -X release:prepare -DreleaseVersion=$1 -DdevelopmentVersion=$2
mvn -e release:perform
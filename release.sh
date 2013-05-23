#!/bin/bash
mvn --batch-mode -e release:prepare -Dgithub.username=$1 -Dgithub.password=$2
mvn --batch-mode -e release:perform
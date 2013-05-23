#!/bin/bash
mvn --batch-mode -e release:prepare -Dusername=$1 -Dpassword=$2
mvn --batch-mode -e release:perform -Dusername=$1 -Dpassword=$2
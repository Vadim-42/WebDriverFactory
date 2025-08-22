#!/bin/bash

REMOTE_URL=""
BROWSER_NAME=""
BROWSER_VERSION=""

while [[ $# -gt 0 ]];
do
  case $1 in
    --remote.url)
      REMOTE_URL=$2
      shift
      ;;
    --browser.name)
      BROWSER_NAME=$2
      shift
      ;;
    --browser.version)
      BROWSER_VERSION=$2
      shift
      ;;
    *)
      echo "Argument $1 not supported"
      exit 1
  esac
  shift
done

echo "Running UI tests on selenoid via maven"
mvn clean test -Dremote.url=$REMOTE_URL -Dbrowser.name=$BROWSER_NAME -Dbrowser.version=$BROWSER_VERSION
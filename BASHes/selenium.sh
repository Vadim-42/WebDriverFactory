#!/bin/bash

if [ "$#" -ne 3 ]; then
  echo "Usage: $0 <stand_url> <browser_name> <browser_version>"
  exit 1
fi

STAND_URL=$1
BROWSER_NAME=$2
BROWSER_VERSION=$3

SELENIUM_HUB_URL="http://localhost:4444/wd/hub"

echo "Запуск тестов с параметрами:"
echo "URL стенда: $STAND_URL"
echo "Браузер: $BROWSER_NAME"
echo "Версия браузера: $BROWSER_VERSION"
echo "Selenium Grid URL: $SELENIUM_HUB_URL"
echo


export STAND_URL
export BROWSER_NAME
export BROWSER_VERSION
export SELENIUM_HUB_URL


mvn clean test
  -Dstand.url=$STAND_URL
  -Dbrowser.name=$BROWSER_NAME
  -Dbrowser.version=$BROWSER_VERSION
  -Dselenium.hub.url=$SELENIUM_HUB_URL

# Вывод результатов (Maven по умолчанию выводит результаты в консоль)


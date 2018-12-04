#!/bin/bash
echo $(curl -i -s -H "Accept: image/svg+xml" "https://sonarcloud.io/api/project_badges/measure?project=brunocarvalho7_app-manutencao20182&metric=alert_status") >> sonarCloudStatus

#cat sonarCloudStatus

if grep "success" sonarCloudStatus; then
    echo "sonarSucess"
else
    echo "sonarFailed"
fi
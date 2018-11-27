touch ~/.netrc
echo "machine api.heroku.com
    login $HEROKU_EMAIL
    password $HEROKU_PASSWORD" >> ~/.netrc
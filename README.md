# iris-crypto-tracker
Tracking Crypto currency levels and alerts about reises and falls.
Written in Java with DEX to retrieve data in IRIS and to send messages on alerts.

## Installation 

### Docker
The repo is dockerised so you can  clone/git pull the repo into any local directory
```
$ git clone https://github.com/jakcpto/iris-crypto-tracker.git
```
Open the terminal in this directory and run:
```
$ docker-compose up -d
```
and open then http://localhost:32792/csp/user/EnsPortal.ProductionConfig.zen

## Setup
In the Business Rules you should write email where to send alerts about changes.
![image](https://user-images.githubusercontent.com/41373877/138560188-0bc9007f-1c50-419b-b4c1-e748948d69c4.png)

User can subscribe to Telegram bot @iris_crypto_tracker_bot to recieve alerts.

## Usage
At the Main page you should Start Production http://localhost:32792/csp/user/EnsPortal.ProductionConfig.zen
![image (64)](https://user-images.githubusercontent.com/41373877/137479173-9d6c17ec-d0c0-467a-95d3-f0baa137875a.png)

In the Message Viewer http://localhost:32792/csp/user/EnsPortal.MessageViewer.zen?SOURCEORTARGET=CryptoService shown every completed request and results.
![image](https://user-images.githubusercontent.com/47400570/138618632-c708e57e-0064-46bf-bd4b-257ec12c7001.png)

CryptoService sends request to api.coinpaprika.com every minute to gain actual data about crypto currences.

Reply on request body:
![image](https://user-images.githubusercontent.com/47400570/138618664-e6a77cb1-49e8-46b3-93b8-3bb71052a239.png)

Emails sent to email from the Business Rules.

Alerts also sends to Telegram from bot @iris_crypto_tracker_bot
TelegramBotOperation - telegram bot which sends alerts and info about current levels of currency.
Bot commands:
![image](https://user-images.githubusercontent.com/41373877/138560291-8d7cca3a-9684-455c-93fe-117d37603f30.png)


# Description
![image](https://user-images.githubusercontent.com/47400570/138618556-c5bf8e15-a795-40a8-8da6-2dcec184be50.png)


# Online Examples
You can watch how it works on http://atscale.teccod.ru:32795/csp/user/EnsPortal.ProductionConfig.zen?PRODUCTION=Crypto.CryptoProduction
Also you can view ruleset on http://atscale.teccod.ru:32795/csp/user/EnsPortal.RuleEditor.zen?RULE=Crypto.RequestRoutingRule
Login "_SYSTEM"
Password "SYS"

Telegram Bot message example:
![image](https://user-images.githubusercontent.com/41373877/138560312-9c2173c9-44f6-4c8f-a434-2fb40f0d7ce7.png)

Email message example:
![image](https://user-images.githubusercontent.com/41373877/138560370-9df9c331-1dcc-47c8-a5f0-bcfac6788355.png)

CSV file example:
![image](https://user-images.githubusercontent.com/47400570/138618731-d985c9ab-3b5d-4ea6-aa0b-8c282dd50430.png)


# Framework
We used PEX framework to develop Services which sends requests.
Also we used PEX to develop Operations, which sends emails with processed information.

# Team
On this project I worked with https://github.com/NjekTt

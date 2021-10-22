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

## Usage

At the Main page you should Start Production http://localhost:32792/csp/user/EnsPortal.ProductionConfig.zen
![image (64)](https://user-images.githubusercontent.com/41373877/137479173-9d6c17ec-d0c0-467a-95d3-f0baa137875a.png)

In the Message Viewer http://localhost:32792/csp/user/EnsPortal.MessageViewer.zen?SOURCEORTARGET=CryptoService shown every completed request and results.
![image (65)](https://user-images.githubusercontent.com/41373877/137479394-f4f53d9d-9d95-436d-b992-67407301f393.png)

CryptoService sends request to api.coinpaprika.com every minute to gain actual data about crypto currences.

Reply on request body:
![image (66)](https://user-images.githubusercontent.com/41373877/137479523-0bb61993-524a-4bc1-a123-921a0bade5c5.png)

## Framework

We used PEX framework to develop Services which sends requests.
Also we used PEX to develop Operations, which sends emails with processed information.

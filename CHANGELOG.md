# Changelog

All notable changes to this project are documented in this file.

## [1.2.0] - 2026-07-16

### Added
- `App.PEXSetup.cls` to configure `%Java Server` for remote `jgw` and register PEX components
- Runtime PEX setup via `pex-runtime.script` and `docker-iris-start.sh` on IRIS container start
- Telegram bot credentials via `.env` (`TELEGRAM_BOT_TOKEN`, `TELEGRAM_BOT_USERNAME`)
- `.env.example` template for local configuration
- Published Docker images on Docker Hub: `jackpto/iris-crypto-tracker-iris` and `jackpto/iris-crypto-tracker-jgw` (tags `1.2.0`, `latest`)

### Changed
- Java Gateway runtime upgraded from Java 8 to Temurin **11** (Cloudflare/CoinPaprika TLS compatibility)
- IRIS JDBC/utils JARs aligned with IRIS 2026 (`intersystems-jdbc-3.10.5`, `intersystems-utils-4.2.2`)
- `CryptoProduction` uses `%gatewayName=%Java Server` instead of host/port settings
- `CryptoService` HTTP client: explicit User-Agent, Accept header, timeouts, and HTTP status handling
- Docker Compose: IRIS hostname `iris`, `depends_on: jgw`, `jgw` loads `.env`
- Base Java build image switched to `eclipse-temurin:8-jdk`

### Fixed
- PEX / Java Gateway connection to `jgw:55555` (`NOTOPEN`, gateway name mismatch)
- CoinPaprika API calls failing with `SSLHandshakeException` / blocked Java 8 User-Agent
- Missing `OnTearDown()` and invalid `super.OnMessage()` / `super.OnProcessInput()` calls in PEX Java classes
- Installer namespace/path for `/opt/irisapp/`
- Empty CSV header not flushed on first create

### Security
- Removed hardcoded Telegram bot token from source; secrets stay in `.env` (gitignored)

## [1.1.0] - previous

- Baseline package version before Docker/PEX modernization

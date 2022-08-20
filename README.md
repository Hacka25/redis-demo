# Redis Demo

## Setup

Start a docker redis instance with:
```bash
docker run -d -p 6379:6379 --name myredis redis
```

Connect to the server with:
```bash
redis-cli
```
or:

```bash
redis-cli -h localhost -p 6379
```

```bash
set a1 100
```

```bash
HSET "user:4" "first_name" "Bartholomew" "last_name" "Pardi" "email" "bpardi3@g.co" "gender" "male" "ip_address" "150.99.179.97" "country" "China" "country_code" "CN" "city" "Yanwo" "longitude" "106.5767662" "latitude" "29.5582227" "last_login" "1588933560"
```

## Notes

* https://collabnix.com/how-to-setup-and-run-redis-in-a-docker-container/
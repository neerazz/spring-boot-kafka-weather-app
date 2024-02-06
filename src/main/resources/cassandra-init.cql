-- init-cassandra.cql
CREATE KEYSPACE IF NOT EXISTS weatherDTO_space WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}
                                                AND durable_writes = true;

drop table if exists weatherentity_cass;

CREATE TABLE IF NOT EXISTS weatherDTO_space.weatherentity_cass
(
    city             TEXT,
    capital          TEXT,
    country          TEXT,
    id               TEXT,
    longitude        DOUBLE,
    latitude         DOUBLE,
    population       DOUBLE,
    description      TEXT,
    weatherInCelsius DOUBLE,
    timestamp        TIMESTAMP,
    PRIMARY KEY ((city, capital, country), id)
) WITH CLUSTERING ORDER BY (id DESC);

CREATE INDEX IF NOT EXISTS ON weatherDTO_space.weatherentity_cass (city);
CREATE INDEX IF NOT EXISTS ON weatherDTO_space.weatherentity_cass (country);
CREATE INDEX IF NOT EXISTS ON weatherDTO_space.weatherentity_cass (capital);

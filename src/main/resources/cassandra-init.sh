#!/bin/bash

# Wait for Cassandra to be fully up and running
until cqlsh -u cassandra_user -p cassandra_password -e "describe keyspaces;" > /dev/null 2>&1; do
  echo "Waiting for Cassandra to start..."
  sleep 5
done

# Create the keyspace if it doesn't exist
cqlsh -u cassandra_user -p cassandra_password -e "CREATE KEYSPACE IF NOT EXISTS weatherDTO_space WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };"

# Create the table within the keyspace
cqlsh -u cassandra_user -p cassandra_password -e "CREATE TABLE IF NOT EXISTS weatherDTO_space.weatherentity_cass (
    id TEXT PRIMARY KEY,
    longitude DOUBLE,
    latitude DOUBLE,
    city TEXT,
    capital TEXT,
    country TEXT,
    population DOUBLE,
    description TEXT,
    weatherInCelsius DOUBLE,
    timestamp TIMESTAMP
);"

echo "Keyspace and table created successfully!"

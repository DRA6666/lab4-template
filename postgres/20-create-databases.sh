#!/usr/bin/env bash
set -e

export PGPASSWORD=postgres
for database in reservations libraries ratings; do
  createdb --username postgres "$database" 2>/dev/null || true
  psql --username postgres --dbname postgres \
    --command="GRANT ALL PRIVILEGES ON DATABASE \"$database\" TO program"
done

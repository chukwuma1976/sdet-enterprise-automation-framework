#!/bin/bash

mkdir -p performance-tests/results

echo "Running k6 smoke test..."
k6 run performance-tests/k6/smoke/users-smoke.js

echo "Running k6 load test..."
k6 run performance-tests/k6/load/users-load.js

echo "Running k6 spike test - failures expected under heavy burst load..."
k6 run performance-tests/k6/spike/users-spike.js || true
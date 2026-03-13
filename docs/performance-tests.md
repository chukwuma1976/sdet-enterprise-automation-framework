# Performance Tests

Run smoke test:
```
k6 run performance-tests/k6/smoke/users-smoke.js
```
Run load test:
```
k6 run performance-tests/k6/load/users-load.js
```
Run spike test:
```
k6 run performance-tests/k6/spike/users-spike.js
```
Run all performance tests
```
./scripts/run-performance-tests.sh
```
## Performance Test Reports

Performance tests are executed using **k6** as part of the CI pipeline.
The pipeline automatically generates HTML performance reports for the following test types:

* Smoke Test
* Load Test
* Spike Test

These reports include:

* Request latency metrics
* Throughput statistics
* Error rates
* Percentile response times

### Accessing Reports from the CI Pipeline

After a workflow run completes:

1. Navigate to the **Actions** tab in this repository.
2. Select the latest workflow run.
3. Scroll to the **Artifacts** section.
4. Download the artifact named:

```
k6-performance-reports
```

5. Extract the downloaded archive.

Inside the folder you will find:

```
performance-tests/results/

smoke-report.html
load-report.html
spike-report.html
```

Open any of the `.html` files in a browser to view the interactive performance dashboards.

### Running Performance Tests Locally

You can execute the full performance test suite locally using:

```
./scripts/run-performance-tests.sh
```

Reports will be generated automatically in:

```
performance-tests/results/
```
## Sample Performance Test Reports
![Smoke Test Report](docs/images/performance-smoke-test.gif)
![Smoke Load Report](docs/images/performance-load-test.gif)
![Smoke Spike Report](docs/images/performance-spike-test.gif)

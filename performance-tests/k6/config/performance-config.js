export const TEST_URL = "https://jsonplaceholder.typicode.com/users";

export const THRESHOLDS = {
    http_req_duration: ['p(95)<500'],
    http_req_failed: ['rate<0.01']
};

export const SMOKE_OPTIONS = {
    vus: 5,
    duration: '10s',
    thresholds: THRESHOLDS
};

export const LOAD_OPTIONS = {
    vus: 50,
    duration: '30s',
    thresholds: THRESHOLDS
};

export const SPIKE_OPTIONS = {
    stages: [
        { duration: '10s', target: 10 },
        { duration: '5s', target: 200 },
        { duration: '10s', target: 10 }
    ],
    thresholds: THRESHOLDS
};
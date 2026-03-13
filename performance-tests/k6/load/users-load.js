import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 50,
    duration: '30s'
};

export default function () {

    const res = http.get('https://jsonplaceholder.typicode.com/users');

    check(res, {
        'status 200': (r) => r.status === 200,
        'response < 800ms': (r) => r.timings.duration < 800
    });

    sleep(1);

}
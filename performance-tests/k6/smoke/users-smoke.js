import http from 'k6/http';
import { check } from 'k6';

export const options = {
    vus: 5,
    duration: '10s'
};

export default function () {

    const res = http.get('https://jsonplaceholder.typicode.com/users');

    check(res, {
        'status is 200': (r) => r.status === 200,
        'response time < 500ms': (r) => r.timings.duration < 500,
        '10 users returned': (r) => JSON.parse(r.body).length === 10
    });

}
import http from 'k6/http';
import { check } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 10 },
        { duration: '5s', target: 200 },
        { duration: '10s', target: 10 }
    ]
};

export default function () {

    const res = http.get('https://jsonplaceholder.typicode.com/users');

    check(res, {
        'status OK': (r) => r.status === 200
    });

}
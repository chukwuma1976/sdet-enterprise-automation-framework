import http from 'k6/http';
import { check } from 'k6';
import { sleep } from 'k6';

export function getUsers(url) {

    const res = http.get(url);

    check(res, {
        'status is 200': (r) => r.status === 200
    });

    sleep(Math.random() * 3); //Simulate user think time

    return res;
}
import http from 'k6/http';
import { check } from 'k6';

export function getUsers(url) {

    const res = http.get(url);

    check(res, {
        'status is 200': (r) => r.status === 200
    });

    return res;
}
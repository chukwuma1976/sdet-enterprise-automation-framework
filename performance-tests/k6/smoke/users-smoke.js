import { SMOKE_OPTIONS, TEST_URL } from '../config/performance-config.js';
import { getUsers } from '../utils/api-helper.js';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

export const options = SMOKE_OPTIONS;

export default () => getUsers(TEST_URL);

export function handleSummary(data) {
    return {
        "performance-tests/results/smoke-report.html": htmlReport(data),
    };
}
import { SMOKE_OPTIONS, TEST_URL } from '../config/performance-config.js';
import { getUsers } from '../utils/api-helper.js';

export const options = SMOKE_OPTIONS;

export default () => getUsers(TEST_URL);
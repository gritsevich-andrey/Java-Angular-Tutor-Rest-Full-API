// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

import {Constrants} from './constrants';

export const environment: Constrants = {
  production: false,
  USER_API: '/users',
  AUTH_API: '/login',
  REG_API: '/register',
  LESSON_ORDER_API: '/lessons/create',
  LESSONS_API: '/lessons',
  CATEGORY_API: '/categories',
  INSTR_API: '/instructors',
  PROGRAM_API: '/programs',
  BUYING_API: '/buying'
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.

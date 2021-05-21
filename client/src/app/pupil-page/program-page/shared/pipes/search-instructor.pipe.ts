import { Pipe, PipeTransform } from '@angular/core';
import {Program} from '../../../../shared/interfaces';

@Pipe({
  name: 'searchInstructor'
})
export class SearchInstructorPipe implements PipeTransform {

  transform(programs: any[], searchInstructor: string = ''): any[] {
    if (!searchInstructor.trim()) {
      return programs;
    }
    return programs.filter(program => {
      return program.instructorName?.includes(searchInstructor);
    });
  }

}

import {Pipe, PipeTransform} from '@angular/core';
import {Program} from '../../../../shared/interfaces';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(programs: any[], programName: string = ''): any[] {
    if (!programName.trim()) {
      return programs;
    }
    return programs.filter(program => {
      return program.name.toLowerCase().includes(programName.toLowerCase());
    });
  }

}

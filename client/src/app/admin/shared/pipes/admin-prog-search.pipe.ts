import { Pipe, PipeTransform } from '@angular/core';
import {Program} from '../../../shared/interfaces';

@Pipe({
  name: 'adminProgSearch'
})
export class AdminProgSearchPipe implements PipeTransform {

  transform(programs: Program[], programName: string = ''): Program[] {
    if (!programName.trim()) {
      return programs;
    }
    return programs.filter(program => {
      return program.name.toLowerCase().includes(programName.toLowerCase());
    });
  }

}

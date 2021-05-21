import {Pipe, PipeTransform} from '@angular/core';
import {Program} from '../../../../shared/interfaces';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(programs: any[], searchCategory: string = ''): any[] {
    if (!searchCategory.trim()) {
      return programs;
    }
    return programs.filter(program => {
      return program.category.includes(searchCategory);
    });
  }

}

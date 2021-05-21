import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'removeduplicate'
})
export class RemoveDuplicatePipe implements PipeTransform {

  transform(value: any, property: string): any {
    const filtered = value
      .map((item: { [propertyName: string]: any; }) => {
        if (item[property] && typeof item[property] === 'string') {
          return [item[property]];
        }
        return item[property] ? item[property] : null;
      })
      .reduce((acc: any, elem: any) => {
        if (acc.indexOf(elem[0]) != -1) {
          return acc;
        }
        acc = acc.concat(elem);
        return acc;
      })
      .filter((x: any) => {
        if (x) {
          return x;
        }
      });
    return new Set(filtered);
  }

}

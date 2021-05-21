import {Pipe, PipeTransform} from '@angular/core';
import {Lesson} from '../../../../../../../shared/interfaces';

@Pipe({
  name: 'searchName'
})
export class SearchNamePipe implements PipeTransform {

  transform(lessons: Lesson[], searchInstructor: string = ''): any {
    if (!searchInstructor.trim()) {
      return lessons;
    }
    return lessons.filter(lesson => {
      return lesson.instructorName.toLowerCase().includes(searchInstructor.toLowerCase());
    });
  }

}

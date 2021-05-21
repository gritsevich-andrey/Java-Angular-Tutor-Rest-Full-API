import { Pipe, PipeTransform } from '@angular/core';
import {Lesson} from '../../../shared/interfaces';

@Pipe({
  name: 'adminLessonSearch'
})
export class AdminLessonSearchPipe implements PipeTransform {

  transform(lessons: Lesson[], lessonDate: string = ''): any {
    if (!lessonDate.trim()) {
      return lessons;
    }
    return lessons.filter( lesson => {
      return lesson.date.toLowerCase().includes(lessonDate.toLowerCase());
    });
  }

}

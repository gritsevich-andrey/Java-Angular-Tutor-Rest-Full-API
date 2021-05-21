import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {BuyingProgram, CategoryProgram, Program} from '../../../../shared/interfaces';
import {FormControl, Validators} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {
  cartProducts: Program[] = [];

  constructor(private http: HttpClient) {
  }

  public getPrograms(): Observable<Program[]> {
    return this.http.get<Program[]>(environment.PROGRAM_API);
  }


  public getProgramById(id: string): Observable<Program> {
    return this.http.get<Program>(environment.PROGRAM_API + '/' + id);
  }

  public getCategory(): Observable<CategoryProgram[]> {
    return this.http.get<CategoryProgram[]>(environment.CATEGORY_API);
  }

  public getProgramsByCategory(category: string): Observable<Program[]> {
    return this.http.get<Program[]>(environment.PROGRAM_API + '/find/category?category=' + category);
  }

  addProduct(program: Program): void {
    this.cartProducts.push(program);
  }

  buyProgram(customerId: string, program: Program): Observable<any> {
    return this.http.post(environment.BUYING_API + '/create', {
      customerId,
      name: program.name,
      imageSrc: program.imageSrc,
      description: program.description,
      instructorId: program.instructorId,
      instructorName: program.instructorName,
      category: program.category,
      cost: program.cost,
      payment: true
    });
  }

  public getBuyingProgram(): Observable<BuyingProgram[]> {
    return this.http.get<BuyingProgram[]>(environment.BUYING_API);
  }

  getBuyingProgramByPupilId(id: string): Observable<BuyingProgram[]> {
    return this.http.get<BuyingProgram[]>(environment.BUYING_API + '/pupil/' + id);
  }

  deleteProgram(id: string): Observable<Program[]> {
    return this.http.delete<Program[]>(environment.PROGRAM_API + '/' + id);
  }

  createProgram(program: any): Observable<any> {
    return this.http.post(environment.PROGRAM_API + '/create', {
      name: program.name,
      category: [program.category],
      imageSrc: program.imageSrc,
      description: program.description,
      instructorName: program.instructorName,
      cost: program.cost,
      payment: false
    });
  }
}

import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { LoaderComponent } from './components/loader/loader.component';
import { RemoveDuplicatePipe } from './pipes/remove-duplicate.pipe';

@NgModule({
  imports: [HttpClientModule],
    exports: [HttpClientModule, LoaderComponent, RemoveDuplicatePipe],
  declarations: [LoaderComponent, RemoveDuplicatePipe]
})
export class SharedModule {

}

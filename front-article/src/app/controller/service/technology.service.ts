import {Injectable} from '@angular/core';
import {Technology} from '../model/technology';
import {HttpClient} from '@angular/common/http';
import {CATEGORY} from '../model/category';
import {Observable} from 'rxjs/internal/Observable';
import {environment} from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class TechnologyService {
    private url = environment.urlBackEnd + 'technologie';
    frontend: Array<Technology>;
    backend: Array<Technology>;

    constructor(private http: HttpClient) {
    }

    getTechnologyByCategory(category: CATEGORY): Observable<Array<Technology>> {
        return this.http.get<Array<Technology>>(this.url + '/category/' + category);
    }
}

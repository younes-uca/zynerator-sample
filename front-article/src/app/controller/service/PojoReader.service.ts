import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class PojoReaderService {
    url = environment.urlBackEnd + 'pojo/uploadFile';

    constructor(private http: HttpClient) {
    }
}

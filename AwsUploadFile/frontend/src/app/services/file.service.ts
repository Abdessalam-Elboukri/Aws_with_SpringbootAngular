import { HttpClient} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";


@Injectable({
  providedIn :'root'
})

export class FileService {

  constructor(private http: HttpClient) {
  }

  private BASE_URL = environment.base_url;

  getAllFiles(): Observable<any> {
    return this.http.get(this.BASE_URL + "all_files")
  }

  uploadFile(file:FormData) : Observable<any>{
    return this.http.post(this.BASE_URL+"upload",file)
  }
}

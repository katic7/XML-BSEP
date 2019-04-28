import { Injectable } from "@angular/core";
import { TokenStorageService } from "./token-storage/token-storage.service";

@Injectable()
export class FrontInterceptor  {

        private roles : Array<any>;

        constructor(private token : TokenStorageService) {}

        checkAuth(role) : boolean{
            return false;
        }
}